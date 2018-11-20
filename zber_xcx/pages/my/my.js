//index.js
//获取应用实例
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    is_bank: 0,//1,0。是否已绑定银行卡。
  },

  /**
   * 页面加载
   */
  onLoad: function (options) {
    var that = this;
    //获取当前用户信息，比如:余额
    that.getUserInfo(that);
    //获取用户是否已绑定银行卡
    //that.getis_bank(that);
  },

  //页面显示
  onShow:function(){
    var that = this;
    //获取当前用户信息，比如:余额
    that.getUserInfo(that);
  },

  //获取用户是否已绑定银行卡
  getis_bank: function (that) {
    wx.request({
      url: app.config.dszjPath_web + 'api/User/hasBank',
      method: "GET",
      header: {
        Token: wx.getStorageSync("token")
      },
      success: function (res) {
        that.setData({
          is_bank: res.data.data
        });
      }
    })
  },

  //获取当前用户信息，比如:余额
  getUserInfo: function (that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/userinfo/getUserInfo',
      method: "GET",
      data:{
        openid:wx.getStorageSync("openid")
      },
      success: function (res) {
        console.log(res);
        if(res.data.state == 200){
          var user=res.data.data;
          //匹配是否包含http://
          if (user.avatar.indexOf("http") == -1) {
            user.avatar = app.config.getImage + user.avatar;
          }
          that.setData({
            user: user
          });
          wx.setStorageSync("userInfo", user);
        }else{
          ////提示信息，或跳转到登陆
          app.btnLogin(res.data);
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
    })
  },

  //用户下拉动作
  onPullDownRefresh: function () {
    var that = this;

    //获取当前用户信息，比如:余额
    that.getUserInfo(that);

    //下拉完成后执行回退
    wx.stopPullDownRefresh();
  },

  //分享
  onShareAppMessage: function (e) {
    return {
      title: '周边儿~个人中心',
      desc: '关注您的生活!',
      path: '/pages/my/my'
    }
  },
})