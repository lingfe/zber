// pages/indexTo/yeHuo/yeHuoList/yeHuoList.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },

  //跳转到详情
  navigator_url(e) {
    wx.navigateTo({
      url: e.currentTarget.dataset.url,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    that.setData({
      typeMenu_id: options.typeMenu_id
    }) ;
    //根据分类菜单id得到项目信息zber_sys/release_info/getWhere_TypeMenu_id?typeMenu_id=kjhkdfghsd
    that.getWhere_TypeMenu_id(that);
  },

  //set关注数据,获取或保存或修改
  btnSetFollow: function (e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    that.data.user_follow = {};
    that.data.user_follow.id = e.currentTarget.id;
    that.data.user_follow.user_id = e.currentTarget.dataset.userid;
    that.data.user_follow.state = e.currentTarget.dataset.state;
    that.data.user_follow.creator = wx.getStorageSync("openid");

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
          //赋值
          var lists = that.data.lists;
          if (!app.checkInput(res.data.data.state)) {
            lists[index].user_info.user_follow.state = res.data.data.state;
          }else{
            lists[index].user_info.user_follow.state = 0;
          }

          that.setData({
            lists: lists
          });
        } else {
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //根据分类菜单id得到项目信息zber_sys
  getWhere_TypeMenu_id:function(that){
    wx.request({
      url: app.config.zberPath_web +'zber_sys/release_info/getWhere_TypeMenu_id',
      method:"get",
      data:{
        typeMenu_id: that.data.typeMenu_id,
        openid:wx.getStorageSync("openid")
      },
      success:function(res){
        console.log(res);
        if(res.data.state == 200){
          that.setData({
            lists: res.data.data.lists
          });
        }else{
          app.showModel(res.data.msg);
        }
      }
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    return {
      title: '八月瓜列表',
      desc: "你知道八月瓜除了好吃还有那些用处吗？",
      path: '/pages/indexTo/yeHuo/yeHuoList/yeHuoList?varieties=' + this.data.varieties
    }
  }
})