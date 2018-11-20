import localData from './assets/localData/localData.js'
import config from './config/config.js'
import request from './utils/request.js'

App({
  config,//配置文件
  localData, //本地数据
  request,//请求工具

  //提示信息，或跳转到登陆
  btnLogin: function (res) {
    if (res.state == "401" || res.state == 401 || res.state == 0) {
      wx.showModal({
        title: '提示',
        content: res.msg+",请重新登陆!",
        success:function(res){
          if(res.confirm){
            wx.navigateTo({
              url: '/pages/wxUserinfoLogin/wxUserinfoLogin',
            });
          }
        }
      })
    }
  },

  //验证非空
  checkInput: function (data) {
    if (data == null || data == undefined || data == "" || data == 'null') {
      return true;
    }
    if (typeof data == "string") {
      var result = data.replace(/(^\s*)|(\s*$)/g, "");
      return result.length == 0 ? true : false;
    } else {
      return false;
    }
  },

  //当前时间
  getDateTime: function () {
    var dateTime = new Date().toLocaleString();
    return dateTime;
  },

  //提示框，有按钮
  showModal: function (msg) {
    wx.showModal({
      title: "提示",
      content:msg,
      showCancel: false,
    });
  },

  //提示，无按钮
  showToast: function (msg, icon) {
    wx.showToast({
      title: msg,
      icon: icon,
      duration: 2000
    })
  },

  // 对数据进行提取
  dataRead: function (data) {
    // 用于存放提取后的数据
    var dataArr = [];
    data.forEach(function (item) {
      // 用于存放单条团数据
      var groupData = {};
      groupData.groupId = item.groupId;
      groupData.goodsImg = item.goodsImg;
      groupData.goodsTitle = item.goodsTitle;
      groupData.groupSize = item.groupSize;
      groupData.groupPrice = item.groupViewPrice;
      groupData.lessNum = item.lessNum;
      groupData.groupEndTime = (item.restTime) / 1000;
      groupData.displayTime = "";
      groupData.userLst = item.userLst;

      dataArr.push(groupData);
    });
    return dataArr;
  },

  // 倒计时方法
  countDown: function (listData, time, i) {
    var self = this, interval = "cleartime" + i;
    if (time != 0) {
      interval = setInterval(function () {
        listData[i].groupEndTime = time - 1;
        listData[i].displayTime = Util.formatTime(listData[i].groupEndTime);
        self.setData({
          lists: listData
        })
        if (time <= 0) {
          clearInterval(interval);
        }
      }, 1000)
    }
  },

  //常用
  globalData: {
    openid:null,//
    appid: 'wx2c5b4fc4466c3e4e',//   //小程序appid
    secret: '955755f5b32225c397c9a61a044b5db6',// //小程序密钥    
    userInfo: null
  }
})