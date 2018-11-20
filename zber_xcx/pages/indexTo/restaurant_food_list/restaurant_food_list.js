// pages/indexTo/restaurant_food_list/restaurant_food_list.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     var that=this;
     that.setData({
       type_menu_id: options.typeMenu_id,
     });

     //根据分类菜单id查询，all商铺
    that.getWhere_type_menu_id(that);
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
            lists[index].user_info.user_follow.state =0;
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

  //根据分类菜单id查询，all商铺
  getWhere_type_menu_id:function(that){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/apply_shops/getWhere_type_menu_id',
      method:"get",
      data:{
        type_menu_id: that.data.type_menu_id,
        openid:wx.getStorageSync("openid")
      },
      success:function(res){
        console.log(res);
        that.setData({
          lists:res.data.data
        });
      }
    })
  },



  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})