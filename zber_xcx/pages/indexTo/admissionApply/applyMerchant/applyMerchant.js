// pages/indexTo/admissionApply/applyMerchant/applyMerchant.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    city: wx.getStorageSync("city"),
    form:{
      sfz:null,//身份证
      realName:null,//真实姓名
      contactNumber:null,//联系电话
      address:null,//详细地址
      position_info:null,//位置信息
      images:[],//营业执照、身份证正反面照片
      creator:wx.getStorageSync("openid"),//申请者
    },
    arr: [],                              //选择图片的数组，预留。不包含编辑之前的数据，用于组装
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  //表单提交
  bindFormSubmit:function(e){
    var that=this;
    var form = that.data.form;
    //验证非空
    if(app.checkInput(e.detail.value.sfz)){
      app.showToast("身份证不能为空!","none");
      return;
    }else if(app.checkInput(e.detail.value.realName)){
      app.showToast("真实姓名不能为空!","none");
      return;
    }else if(app.checkInput(e.detail.value.address)){
      app.showToast("信息地址不能为空!","none");
      return;
    }else{
      form.sfz=e.detail.value.sfz;
      form.realName=e.detail.value.realName;
      form.contactNumber=e.detail.value.contactNumber;
      form.address=e.detail.value.address;
    }
    
    //多张图片上传
    function uploadimg(path, pathArr, dataArr,id) {
      //验证id是否为空
      if(!app.checkInput(id)){
        wx.uploadFile({
          url: app.config.zber_domain + "zber_sys/images/imageUpload",//单张图片上传
          filePath: path[0] + "",                 //要上传文件资源的路径
          name: 'file',     //文件对应的 key , 开发者在服务器端通过这个 key 可以获取到文件二进制内容
          header: {                                   //HTTP 请求 Header , header 中不能设置 Referer
            "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
          },
          formData: {
            openid: wx.getStorageSync("openid"),
            setid: id
          },                             //参数(HTTP 请求中其他额外的 form data)
          success: (resp) => {                         //接口调用成功的回调函数
            if (dataArr.length > 0) {
              //递归
              uploadimg(dataArr.splice(0, 1), pathArr, dataArr,id);
            } else {
              that.setData({
                arr: []
              });
            }
          }
        });
      }
    }

    //发起请求
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/user_apply_merchant/save',
      method:"POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data:form,
      success:function(res){
        console.log(res);
      
        if(res.data.state == 200){
          var data=res.data.data;
          if(!app.checkInput(data)){
            //后上传图片，保存
            var arr = that.data.arr;
            if (arr.length >= 1) {
              //上传图片数组
              uploadimg(arr.splice(0, 1), [], arr, data.id);
            }
          }else{
            wx.showModal({
              title: '提示',
              content: res.data.msg,
              success: function (res) {
                if (res.confirm) {
                  //返回上一页
                  wx.navigateBack(2);
                }
              }
            });
          }
        }else if(res.data.state == 401){
          app.btnLogin(res.data);
        }
        else{
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //得到地址，位置信息
  getAddress:function(e){
    var that=this;
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {
        console.log(res);
        var latitude = res.latitude
        var longitude = res.longitude

        wx.chooseLocation({
          success: function (res) {
            console.log(res);
            //处理地址信息
            var address=res.address;

            //省
            var province_index=address.indexOf("省") + 1
            var province = address.substring(0, province_index);

            //市
            var city_index = address.indexOf("市") + 1;
            var city = address.substring(province_index, city_index);

            //区
            var area_index = address.indexOf("区") + 1;
            var area = address.substring(city_index, area_index);

            that.setData({
              "form.address":address,
              "form.position_info":res.name,
              city: city,
            });
          }
        })
      }
    })
  },

  //删除图片
  bindtapImageDelete: function (e) {
    var img = e.currentTarget.dataset.img;
    var that = this;
    var arr = that.data.arr;

    for (var j = 0; j < arr.length; j++) {
      if (arr[j] == img) {
        arr.splice(j, 1);
      }
    }

    that.setData({
      arr: arr
    });
  },

  //获取 图片
  chooseImage: function (e) {
    var that = this;
    if (that.data.arr.length >= 6) {
      //弹出提示
      wx.showModal({
        content: '最多只能上传6张图片！',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定');
          }
        }
      });
      return;
    }
    wx.chooseImage({
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        var imglength = res.tempFilePaths.length + that.data.arr.length;
        if (imglength > 6) {
          //弹出提示
          wx.showModal({
            content: '总共只能上传6张图片！',
            showCancel: false,
            success: function (res) {
              if (res.confirm) {
                console.log('用户点击确定');
              }
            }
          });
          return;
        }
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        that.setData({
          arr: that.data.arr.concat(res.tempFilePaths)
        });
      }
    })
  },

  //图片预览
  previewImage: function (e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.arr // 需要预览的图片http链接列表
    })
  },
})