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
      "全部", "显示中", "已下架","审核中","审核不通过"
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

  //跳转到详情
  navigator_url(e) {
    wx.navigateTo({
      url: e.currentTarget.dataset.url,
    })
  },

  //删除项目
  deleteWhereId:function(e){
    var that=this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/release_info/deleteWhereId',
      method:"get",
      data: { 
        id: e.target.id
      },
      success:function(res){
        console.log(res);
        var data = res.data;
        if(data.state=200){
          //得到个人发布的所有项目
          that.getWhereOpenid(that);
        }
        app.showModal(data.msg);
      }
    });
  },

  //下架,上架
  updateWhereId_state:function(e){
    var that=this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/release_info/updateWhereOpenid_state',
      method:"post",
      header:{
        "Content-Type" : "application/x-www-form-urlencoded;charset=UTF-8"
      },
      data:{
        id:e.currentTarget.id,
        state: e.currentTarget.dataset.state
      },
      success(res){
        //得到个人发布的所有项目
        that.getWhereOpenid(that);
      }
    })
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
            lists:data.data,
            lists_bf:data.data
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
    var state = e.currentTarget.id;

    //判断模块
    if (name == "全部") {
      //得到个人发布的所有项目
      that.getWhereOpenid(that);
    } else {
      state=state-1;
      app.showToast("正在努力开发中!..", "none");
    }

    //验证非空
    var st = [];
    if(state!=null){
      var lists=that.data.lists_bf;
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
})