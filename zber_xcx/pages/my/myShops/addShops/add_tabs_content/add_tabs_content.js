// pages/my/myShops/addShops/add_tabs_content/add_tabs_content.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //图片数据
    arr:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    that.setData({
      tabs_id:options.tabs_id,
    });
  },

  //表单提交
  bindFormSubmit: function (e) {
    var that = this;
    var form = [];
    form.get_id = that.data.tabs_id; //tabs导航菜单id
    form.model = 0; //布局model
    form.title = e.detail.value.title; //标题
    //内容加粗，放在内容首
    form.content_bold_first = e.detail.value.content_bold_first;
    //内容
    form.content = e.detail.value.content;
    //内容加粗，放在内容尾
    form.content_bold_tail = e.detail.value.content_bold_tail;
    //内容2,当加粗内容在需要放在中间时使用。
    form.content_to = e.detail.value.content_to;
    //图片说明文本
    form.img_txt = e.detail.value.img_txt;
    form.creator = wx.getStorageSync("openid");//创建者

    //多张图片上传
    function uploadimg(path, pathArr, dataArr, id) {
      //验证id是否为空
      if (!app.checkInput(id)) {
        wx.uploadFile({
          url: app.config.getImage + "images/imageUpload",//单张图片上传
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
              uploadimg(dataArr.splice(0, 1), pathArr, dataArr, id);
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
      url: app.config.zberPath_web + 'zber_sys/tabs_content/save_or_update',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: form,
      success: function (res) {
        console.log(res);
        //验证是否保存成功
        if (res.data.state == 200) {
          var data = res.data.data;
          if (!app.checkInput(data)) {
            //后上传图片，保存
            var arr = that.data.arr;
            if (arr.length >= 1) {
              //上传图片数组
              uploadimg(arr.splice(0, 1), [], arr, data.id);
            }
          } else {
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
        } else if (res.data.state == 401) {
          app.btnLogin(res.data);
        }
        else {
          app.showModal(res.data.msg);
        }
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
        var arr = that.data.arr;
        var images = res.tempFilePaths;
        images.forEach(function (item) {
          arr.push(item);
        });

        that.setData({
          arr: arr
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