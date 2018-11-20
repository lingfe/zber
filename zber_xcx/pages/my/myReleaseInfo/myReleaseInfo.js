// pages/my/myReleaseInfo/myReleaseInfo.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //菜单tbs
    activeIndex:0,
    tabs_list:[
      "全部", "显示中", "编辑中","审核中","审核不通过"
    ],

    //指令代码,通过该代码可以显示、执行、查看某些隐藏模块
    Instruction_code:{
      display_update:true,//是否显示修改
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    //得到个人发布的所有项目
    that.getWhereOpenid(that);
  },

  //modelOperation
  modelOperation:function(e){
    
  },

  //得到个人发布的所有项目
  getWhereOpenid:function(that){
    wx.request({
      url: app.config.zberPath_web+'zber_sys/release_info/getWhereOpenid',
      method:"get",
      data:{
        openid:wx.getStorageSync("openid")
      },
      success:function(res){
        console.log(res);
        var data=res.data;
        if(data.state == 200){
          that.setData({
            lists:data.data
          });
        }else if(data.state == 401){
          app.btnLogin(data);
        }
      }
    })
  },

  //tab切换
  tabClick: function (e) {
    var that = this;
    var name = e.currentTarget.dataset.name;
    var state = null;

    //判断模块
    if (name == "全部") {
      //得到个人发布的所有项目
      that.getWhereOpenid(that);
    } else if(name == "显示中"){
      state = 0;
    }
     else {
      app.showToast("正在努力开发中!..", "none");
    }

    //验证非空
    var st = [];
    if(state!=null){
      var lists=that.data.lists;
      lists.forEach(function(item){
        if(item.state == state){
          st.push(item);
        }
      });
    }

    that.setData({
      activeIndex: e.currentTarget.id,
      //数据,初始化
      lists: st,
      //分页参数，初始化
      pageIndex: 1,
      pageNum: 3,
    });
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