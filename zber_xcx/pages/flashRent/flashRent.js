var Util = require("../../utils/util");

//获取应用实例
var app = getApp();

Page({
  data: {
    model:0,
    activeIndex:-1,
    isPrices:false,
    priceIndex:0,
    PriceRange: ['全部', '最新发布', '浏览最多', '转发最多', '喜欢最多'],

    //城市
    city:wx.getStorageSync("city"),

    //数据,初始化
    lists:[],
    //分页参数，初始化
    pageIndex:1,
    pageNum:3,
  },


  //加载
  onLoad:function(options){
    var that=this;
    var city=wx.getStorageSync("city");
    if(app.checkInput(city)){
      city="贵阳";
    }
    that.setData({
      city:city
    });

    //get推荐数据
    that.getRecommend(that);
    //get分类菜单
    that.getWhereSuperiorId(that);
  },

  //跳转到详情
  navigator_url(e){
    wx.navigateTo({
      url: e.currentTarget.dataset.url,
    })
  },

  //跳转到个人资源信息发布页面
  bindtapFabu:function(e){
    wx.navigateTo({
      url: '/pages/flashRent/userRelease/userRelease',
    });
    app.showToast("正在努力开发中!..", "none");
  },

  //get分类菜单
  getWhereSuperiorId: function (that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/type_menu/getWhereSuperiorId',
      method: "POST",
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({ type_menu: res.data.data });
        }
      }
    })
  },

  //get我的关注
  getMyFollow:function(that){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_follow/getMyFollow',
      method: "get",
      data: {
        openid:wx.getStorageSync("openid"),
      },
      success:function(res){
        if(res.data.state==200){
          var lists=res.data.data;
          lists.forEach(function(item){
            //匹配是否包含http://
            if (item.user_info.avatar.indexOf("http") == -1) {
              item.user_info.avatar = app.config.getImage + item.user_info.avatar;
            }
            //图片路径
            var images = item.release_info.images;
            if (!app.checkInput(images)) {
              images.forEach(function (img) {
                if (img.imgUrl.indexOf("http") == -1) {
                  img.imgUrl = app.config.getImage + img.imgUrl;
                }
              });
              item.release_info.images = images;
            }
            
          });

          that.setData({
            lists: res.data.data
          });
        }else if(res.data.state == 401){
          app.btnLogin(res.data);
        }
        else{
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //set关注数据,获取或保存或修改
  btnSetFollow: function (e) {
    var that = this;
    var index=e.currentTarget.dataset.index;
    that.data.user_follow={};
    that.data.user_follow.id=e.currentTarget.id;
    that.data.user_follow.user_id = e.currentTarget.dataset.userid;
    that.data.user_follow.state = e.currentTarget.dataset.state;
    that.data.user_follow.creator = wx.getStorageSync("openid");

    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_follow/get_or_save_or_update',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      data: that.data.user_follow,
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          //赋值
          var lists=that.data.lists;
          if (!app.checkInput(res.data.data.state)) {
            lists[index].user_info.user_follow.state = res.data.data.state;
          } else {
            lists[index].user_info.user_follow.state = 0;
          }
          
          that.setData({
            lists:lists
          });
        } else {
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //get推荐数据
  getRecommend:function(that){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/recommend/get',
      method: "get",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data:{
        pageIndex:that.data.pageIndex,
        pageNum:that.data.pageNum,
        address: that.data.city,
        openid:wx.getStorageSync("openid")
      },
      success:function(res){
        console.log(res);
        if (res.data.state == 200) {
          var lists = res.data.data.list;
          lists.forEach(function (item) {
            //匹配是否包含http://
            if (item.user_info.avatar.indexOf("http") == -1) {
              item.user_info.avatar = app.config.getImage + item.user_info.avatar;
            }

            //图片路径
            var images = item.images;
            if(!app.checkInput(images)){
              images.forEach(function (img) {
                if (img.imgUrl.indexOf("http") == -1) {
                  img.imgUrl = app.config.getImage + img.imgUrl;
                }
              });
            }
            item.images=images;
            
          });

          that.setData({
            lists: that.data.lists.concat(lists)
          });
        }
      },
      fail: function (res) {
        wx.showModal({
          title: '返回主页',
          content: '很抱歉！小程序还没有发布上线，小编正在努力开发!然后提示您："点击右上角，打开调试!就可以使用拉~“',
          success: function (res) {
            if (res.confirm) {
              wx.switchTab({
                url: '/pages/indexTo/indexTo',
              })
            }
          }
        })
      }
    });
  },

  //初始化设置
  onReady: function () {
    wx.setNavigationBarTitle({
      title: '来自周边儿推荐'
    })
  },

  //得到地址，位置信息
  getAddress: function (e) {
    var that = this;
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {
        console.log(res);
        var latitude = res.latitude
        var longitude = res.longitude

        wx.chooseLocation({
          success: function (res) {
            console.log(res);
            //处理地址信息
            var address = res.address;

            //省
            var province_index = address.indexOf("省") + 1
            var province = address.substring(0, province_index);

            //市
            var city_index = address.indexOf("市") + 1;
            var city = address.substring(province_index, city_index-1);

            //区
            var area_index = address.indexOf("区") + 1;
            var area = address.substring(city_index, area_index);

            wx.setStorageSync("city", city);
            that.setData({
              city: city,
            });

            //get推荐数据
            that.data.pageIndex=1;
            that.data.lists=[];
            that.getRecommend(that);
          }
        })
      }
    })
  },

  //是否显示查询参数
  bindtapButton: function (e) {
    this.setData({
      isPrices: this.data.isPrices == false ? true : false
    });
  },

  //选择参数
  bindtapPrices(e){
    this.setData({ priceIndex: e.currentTarget.id });
    app.showToast("正在努力开发中!..","none");
  },

  //tab切换
  tabClick: function (e) {
    var that=this;
    var name=e.currentTarget.dataset.name;

    //判断模块
    if(name == "推荐"){
      //get推荐数据
      that.getRecommend(that);
    }else if(name == "关注"){
      //get我的关注
      that.getMyFollow(that);
    }else{
      app.showToast("正在努力开发中!..", "none");
    }

    that.setData({ 
      activeIndex: e.currentTarget.id,
      //数据,初始化
      lists: [],
      //分页参数，初始化
      pageIndex: 1,
      pageNum: 3,
    });
  },

  //分享
  onShareAppMessage: function (e) {
    return {
      title: '周边儿~',
      desc: '关注您的生活!',
      path: '/pages/flashRent/flashRent'
    }
  },

  //用户下拉动作
  onPullDownRefresh: function (e) {
    var that = this;
    that.setData({
      //数据,初始化
      lists: [],
      //分页参数，初始化
      pageIndex: 1,
      pageNum: 3,  
    });

    //判断模块
    if (that.data.activeIndex == -1) {
      //get推荐数据
      that.getRecommend(that);
    } else if (that.data.activeIndex == -2) {
      //get我的关注
      that.getMyFollow(that);
    }

    //get分类菜单
    that.getWhereSuperiorId(that);
    //下拉完成后执行回退
    wx.stopPullDownRefresh();
  },

  //页面上拉触底事件的处理函数
  onReachBottom: function (e) {
    var that = this;
    var num = that.data.pageIndex;
    num++;
    that.setData({
      pageIndex: num,
    });

    //判断模块
    if (that.data.activeIndex == -1) {
      //get推荐数据
      that.getRecommend(that);
    } else if (that.data.activeIndex == -2) {
      //get我的关注
      that.getMyFollow(that);
    }

    //提示
    wx.showToast({
      title: '正在加载..',
      icon: 'loading',
      duration: 2000,
    });
  },

})