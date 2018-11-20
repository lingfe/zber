/**  
 *   path:  pages/indexTo/yeHuo/yeHuo.wxss   
 *   作者:  lingfe 
 *   时间:  2018-09-26
 *   描述:  野货.
 * 
 * */
//获取应用实例
var app = getApp()

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
    var that = this;
    that.setData({
      superiorId: options.typeMenu_id
    });

    //get分类菜单
    that.getWhereSuperiorId(that);
    //get轮播图图片和属性
    that.getLbtAttributeInfo(that);
  },

  //get轮播图图片和属性
  getLbtAttributeInfo: function (that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/lbt_attribute/getLbtAttributeInfo',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        setId: that.data.superiorId,//分类菜单id,项目id，其他id。如果=0，则表示最顶级，也就是服务菜单的轮播图属性
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({ lbt_attribute: res.data.data.lbt_attribute });
        } else {
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //get分类菜单
  getWhereSuperiorId: function (that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/type_menu/getWhereSuperiorId',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        superiorId: that.data.superiorId,//上级id，根据此id得到菜单
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({ type_menu: res.data.data });
        }
      }
    })
  },

})