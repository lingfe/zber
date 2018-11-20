// pages/indexTo/restaurantFood/restaurantFood.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeIndex: -1,         //tab切换下标
    activeIndex_to: -1,
    ax:0,

    //商铺商品
    commodity_list: [{
      img: "https://p0.meituan.net/deal/3f47cdea4688224ddc30a1047e87472b93015.jpg",
      commodityName: "点击选购分类菜单得到商品信息!",
    }],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    that.setData({
      id:options.id,
    });
    that.data.id=options.id;
    //根据商铺id得到商铺详情
    that.getWhereId_detail(that);
    //获取用户是否喜欢该项目
    that.getUserLike(that);
  },

  //获取用户是否喜欢该项目
  getUserLike: function (that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_like/get',
      method: "Post",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        project_id: that.data.id,
        openid: wx.getStorageSync("openid")
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({
            ax: res.data.data.state
          });
        } else {
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //设定该项目是否喜欢
  bindtabAX: function (e) {
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_like/get_or_save',
      method: "Post",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        project_id: that.data.id,
        openid: wx.getStorageSync("openid")
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({
            ax: res.data.data.state
          });
        } else {
          app.showModal(res.data.msg);
        }
      }
    });
  },

  //点击(关注/取消关注)
  btnSetFollow: function (e) {
    var that = this;
    that.data.user_follow.user_id = e.currentTarget.dataset.userid;
    that.data.user_follow.state = e.currentTarget.dataset.state;
    that.data.user_follow.creator = wx.getStorageSync("openid");

    //关注数据,获取或保存或修改
    that.get_or_save_or_update(that);
  },

  //关注数据,获取或保存或修改
  get_or_save_or_update: function (that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_follow/get_or_save_or_update',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: that.data.user_follow,
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({
            user_follow: res.data.data,
          });
        } else {
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //根据商铺id得到商铺详情
  getWhereId_detail:function(that){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/apply_shops/getWhereId_detail',
      method: "get",
      data:{
        id:that.data.id,
        openid:wx.getStorageSync("openid")
      },
      success:function(res){
        if(res.data.state=200){
          var basicInfo=res.data.data;
          //匹配是否包含http://
          if (basicInfo.user_info.avatar.indexOf("http") == -1) {
            basicInfo.user_info.avatar = app.config.getImage + basicInfo.user_info.avatar;
          }
          that.setData({
            //推荐商品
            commodity_list:res.data.data.commodity_list,
            //基本信息
            basicInfo: basicInfo
          });

          //关注数据,获取
          that.data.user_follow = {
            creator: wx.getStorageSync("openid"),
            state: 0,
            user_id: res.data.data.user_info.id,
            modify: "get",
          };
          that.get_or_save_or_update(that);
        }
      }
    })
  },
  
  //拨打电话号码
  bodaPhone: function (e) {
    wx.makePhoneCall({
      phoneNumber: e.currentTarget.id,
    });
  },

  //tabs菜单导航,点击切换
  tabClick: function (e) {
    var that = this;
    var name = e.currentTarget.dataset.name;

    //设置
    that.setData({
      activeIndex: e.currentTarget.id
    });
  },

  //根据根据shopsChooseType_tabs_id得到商品集合
  getWhereShopsChooseType_tabs_id: function (id) {
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/shops_commodity/getWhereShopsChooseType_tabs_id',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        shopsChooseType_tabs_id: id,
      },
      success: function (res) {
        console.log(res);
        that.setData({
          commodity_list: res.data.data
        });
      }
    })
  },

  //选购tabs分类菜单，点击切换
  switchRightTab(e) {
    var that = this;

    //根据根据shopsChooseType_tabs_id得到商品集合
    var id = e.currentTarget.id;
    that.getWhereShopsChooseType_tabs_id(id);

    //设置
    that.setData({
      shopsChooseType_tabs_id: id,
      activeIndex_to: e.currentTarget.dataset.index
    });
  },

  //分享次数+1
  setUserShare(that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_share/save',
      method: "Post",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        project_id: that.data.id,
        openid: wx.getStorageSync("openid")
      },
      success: function (res) {
        console.log(res);
        app.showModal(res.data.msg);
      }
    })
  },

  /**
 * 用户点击右上角分享
 */
  onShareAppMessage: function (res) {
    var that = this;
    return {
      title: '餐饮食品',
      desc: "" + that.data.basicInfo.shopsName,
      path: '/pages/indexTo/restaurant_food_list/restaurantFood/restaurantFood?id=' + that.data.id,
      success: (res) => {
        console.log("转发成功", res);
        that.setUserShare(that);
      },
    }
  }
})