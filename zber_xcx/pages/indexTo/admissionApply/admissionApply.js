// pages/indexTo/admissionApply/admissionApply.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    lbt_images: [
      {
        img_url: "/assets/images/ico/ico_menu/icon_05.png", name: "我有商铺",
        navigator: "/pages/indexTo/admissionApply/applyMerchant/applyMerchant"
        ,bl:true
      },
      {
        img_url: "/assets/images/ico/ico_menu/icon_03.png", name: "我有公司",
        navigator: "/pages/indexTo/yeHuo/yeHuoList/yeHuoList?category=6"
      },
      {
        img_url: "/assets/images/ico/ico_menu/icon_07.png", name: "我有个人资源",
        navigator: "/pages/indexTo/yeHuo/yeHuoList/yeHuoList?category=7"
      },
      {
        img_url: "http://img2.imgtn.bdimg.com/it/u=2784462959,72305985&fm=26&gp=0.jpg", name: "我有养值场",
        navigator: "/pages/indexTo/yeHuo/yeHuoList/yeHuoList?category=7"
      },
      {
        img_url: "http://img2.imgtn.bdimg.com/it/u=2784462959,72305985&fm=26&gp=0.jpg", name: "我是种植户",
        navigator: "/pages/indexTo/yeHuo/yeHuoList/yeHuoList?category=7"
      }
    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  //跳转,提示
  btn_navigate(e) {
    var bl=JSON.stringify(e.currentTarget.dataset.item.bl);
    if (bl){
      wx.navigateTo({
        url: e.currentTarget.dataset.item.navigator,
      });
      return;
    }
    app.showToast("正在努力开发中!..", "none");
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