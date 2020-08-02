// pages/zber/layoutModel/layoutModel.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    layoutModel: app.localData.layoutModel
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 选择布局模板
   */
  layouModel: function (e) {
    var that=this;

    //得到打开的页面
    var pages = getCurrentPages();
    var currPage = pages[pages.length - 1];  //当前页面
    var prevPage = pages[pages.length - 2]; //上一个页面

    //直接调用上一个页面的setData()方法，把数据存到上一个页面中去
    prevPage.setData({
      "form.model": e.currentTarget.id,
    });
    
    //返回上一页
    wx.navigateBack();
    return false;
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