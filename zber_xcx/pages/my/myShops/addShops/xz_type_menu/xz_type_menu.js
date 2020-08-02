// pages/my/myShops/addShops/xz_type_menu/xz_type_menu.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    that.setData({
      id: options.id,
      type: options.type
    });
    //get分类菜单
    that.getWhereSuperiorId('');
  },

  //点击选择分类菜单
  xzTypeMenu:function(e){
    var that=this;
    var id = e.currentTarget.id;
    var name=e.currentTarget.dataset.name;
    //get分类菜单
    that.getWhereSuperiorId(id,name);
  },

  //get分类菜单
  getWhereSuperiorId: function (superiorId,name) {
    var that=this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/type_menu/getWhereSuperiorId',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        superiorId:superiorId,//上级id，根据此id得到菜单
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          var type_menu=res.data.data;
          if(!app.checkInput(type_menu)){
            that.setData({ type_menu: res.data.data });
          }else{
            //验证跳转
            if (that.data.type == 'release_info'){
              //跳转到个人发布页面
              wx.navigateTo({
                url: '/pages/flashRent/userRelease/userRelease?type_menu_id=' + superiorId,
              })
            }else{
              //得到打开的页面
              var pages = getCurrentPages();
              var currPage = pages[pages.length - 1];  //当前页面
              var prevPage = pages[pages.length - 2]; //上一个页面

              //直接调用上一个页面的setData()方法，把数据存到上一个页面中去
              prevPage.setData({
                "basicInfo.type_menu_id": superiorId,
                "form.typeMenu_id":superiorId,
                "form.typeMenu_name": name,
                type_menu_id: superiorId
              });
              //返回上一页
              wx.navigateBack();
            }
          }
        }
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