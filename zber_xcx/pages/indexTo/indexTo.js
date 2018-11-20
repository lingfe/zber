//index.js
//获取应用实例
 var app = getApp();

Page({
  data: {
    tt:1,
    indexmenu: [],
    imgUrls: []
  },

  //加载
  onLoad: function (options) {
    var that=this;
    //get分类菜单
    that.getWhereSuperiorId(that);
    //get轮播图图片和属性zber_sys/lbt_attribute/getLbtAttributeInfo?state=1
    that.getLbtAttributeInfo(that);
  },

  //跳转,提示
  btn_navigate(e) {
    app.showToast("正在努力开发中!..", "none");
  },

  //get轮播图图片和属性
  getLbtAttributeInfo:function(that){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/lbt_attribute/getLbtAttributeInfo',
      method: "POST",
      header:{
        "Content-Type": "application/x-www-form-urlencoded"
      },
      data:{
        setId: 0,//分类菜单id,项目id，其他id。如果=0，则表示最顶级，也就是服务菜单的轮播图属性
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({ lbt_attribute: res.data.data.lbt_attribute });
        }else{
          app.showModel(res.data.msg);
        }
      }
    })
  },

  //get分类菜单
  getWhereSuperiorId:function(that){
    wx.request({
      url: app.config.zberPath_web +'zber_sys/type_menu/getWhereSuperiorId',
      method:"POST",
      success:function(res){
        console.log(res);
        if(res.data.state == 200){
          that.setData({type_menu:res.data.data});
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
  onPullDownRefresh: function (e) {
    var that=this;
    //get分类菜单
    that.getWhereSuperiorId(that);

    //下拉完成后执行回退
    wx.stopPullDownRefresh();
  },

  //分享
  onShareAppMessage: function (e) {
    return {
      title: '周边儿~',
      desc: '关注您的生活!',
      path: '/pages/indexTo/indexTo'
    }
  },
})
