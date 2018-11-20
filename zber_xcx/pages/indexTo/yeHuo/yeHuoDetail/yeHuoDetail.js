// pages/indexTo/yeHuo/yeHuoDetail/yeHuoDetail.js
/**  
 * 作者: lingfe
  * 时间: 2017 - 12 - 7
  * 描述: 众筹项目详情_合伙创业
  * 
 **/
//获取应用实例
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    ax:0,//0=不喜欢,1=喜欢
    activeIndex: 0,         //tab切换下标
    sliderOffset: 0,        //坐标x
    sliderLeft: 0,          //坐标y
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    that.setData({id:options.id});

    //根据项目id得到信息
    that.getWhereId(that);
    //获取用户是否喜欢该项目
    that.getUserLike(that);
    
  },

  //跳转,提示
  btn_navigate(e){
    app.showToast("正在努力开发中!..","none");
  },

  //点击(关注/取消关注)
  btnSetFollow:function(e){
    var that=this;
    that.data.user_follow.user_id= e.currentTarget.dataset.userid;
    that.data.user_follow.state = e.currentTarget.dataset.state;
    that.data.user_follow.creator= wx.getStorageSync("openid");

    //关注数据,获取或保存或修改
    that.get_or_save_or_update(that);
  },

  //关注数据,获取或保存或修改
  get_or_save_or_update:function(that){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_follow/get_or_save_or_update',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data:that.data.user_follow,
      success:function(res){
        console.log(res);
        if(res.data.state==200){
          that.setData({
            user_follow:res.data.data,
          });
        }
      }
    })
  },

  //根据项目id得到信息
  getWhereId:function(that){
    wx.request({
      url: app.config.zberPath_web +'zber_sys/release_info/getWhereId',
      method:"get",
      data:{
        id:that.data.id,
        openid: wx.getStorageSync("openid")
      },
      success:function(res){
        console.log(res);
        if(res.data.state==200){
          var releaseInfo = res.data.data;

          //匹配是否包含http://
          if (releaseInfo.user_info.avatar.indexOf("http") == -1) {
            releaseInfo.user_info.avatar = app.config.getImage + releaseInfo.user_info.avatar;
          }

          var images_list = releaseInfo.lbt_attribute.images_list;
          images_list.forEach(function (item) {
            //匹配是否包含http://
            if (item.imgUrl.indexOf("http") == -1) {
              item.imgUrl = app.config.getImage + item.imgUrl;
            }
          });
          releaseInfo.lbt_attribute.images_list = images_list;
          
          //tabs内容处理
          var info = releaseInfo.tabs_list;
          for(var i=0;i<info.length;i++){
            var content = info[i].content;
            if (!app.checkInput(content)){
              content= JSON.parse(content);
            }
            info[i].content =content;
          }

          //设置tab
          var sliderWidth = 50;
          wx.getSystemInfo({
            success: function (res) {
              that.setData({
                releaseInfo: releaseInfo,
                info: info,
                sliderLeft: (res.windowWidth / releaseInfo.tabs_list.length - sliderWidth) / 2,
                sliderOffset: res.windowWidth / releaseInfo.tabs_list.length * that.data.activeIndex
              });
            }
          });

          //关注数据,获取
          that.data.user_follow={
            creator: wx.getStorageSync("openid"),
            state: 0,
            user_id: releaseInfo.user_info.id,
            modify: "get",
          };
          that.get_or_save_or_update(that);
        }
        else{
          app.showModal(res.data.msg);
        }
      },
      fail:function(res){
        wx.showModal({
          title: '返回主页',
          content: '很抱歉！小程序还没有发布上线，小编正在努力开发!然后提示您："点击右上角，打开调试!就可以使用拉~“',
          success:function(res){
            if(res.confirm){
              wx.switchTab({
                url: '/pages/indexTo/indexTo',
              })
            }
          }
        })
      }
    })
  },

  //获取用户是否喜欢该项目
  getUserLike:function(that){
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
        }
      }
    })
  },

  //设定该项目是否喜欢
  bindtabAX:function(e){
    var that=this;
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

  //tab点击切换
  tabClick: function (e) {
    var that = this;
    var name = e.currentTarget.dataset.name;

    //设置
    that.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
  },

  //分享次数+1
  setUserShare(that){
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
      success:function(res){
        console.log(res);
        app.showModal(res.data.msg);
      }
    })
  },

  /**
 * 用户点击右上角分享
 */
  onShareAppMessage: function (res) {
    var that=this;
    if (res.from === 'button') { 
      console.log("来自页面内转发按钮"); 
      console.log(res.target); 
      //that.setUserShare(that);
    } else { 
      console.log("来自右上角转发菜单")
    }

    return {
      title: '详情',
      desc: "" + that.data.releaseInfo.tab_release_info.title,
      path: '/pages/indexTo/yeHuo/yeHuoDetail/yeHuoDetail?id=' + that.data.id,
      success: (res) => {
        console.log("转发成功", res);
        that.setUserShare(that);
      },
    }
  }
})