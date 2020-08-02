// pages/flashRent/userRelease/userRelease.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ax:1,
    tabs: ["预览", "基本信息", "其他导航菜单内容说明"],
    tab_index: 1,
    activeIndex: -1,         //tab切换下标

    //图片数组
    arr:[],

    //表单
    form:{
      model:2,
      typeMenu_name:"未选择",
    },

    //布局说明
    bujusming_list: app.localData.bujusming_list,

    //tabs导航菜单内容
    tabs_content:[],
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;

    var user = wx.getStorageSync("userInfo");
    if(!app.checkInput(user)){
      //匹配是否包含http://
      if (user.avatar.indexOf("http") == -1) {
        user.avatar = app.config.getImage + user.avatar;
      }
    }
    that.setData({
      'releaseInfo.user_info':user,
      'form.typeMenu_id': options.type_menu_id,
      id:options.id,
    });

    //根据项目id得到信息
    that.getWhereId(that);
  },

  //根据setId获取图片数据
  getWhereSetId:function(that){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/images/getWhereSetId',
      method:"get",
      data:{
        setId:that.data.id,
      },
      success:function(res){
        var data=res.data;
        if(data.state==200){
          that.setData({
            'form.images': data.data
          });
        }
      }
    })
  },

  //根据图片id标识删除图片信息
  bindtapImageDelete_images:function(e){
    var that=this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/images/deleteWhereId',
      method:"get",
      data:{
        id: e.currentTarget.id
      },
      success:function(res){
        var data=res.data;
        if(data.state==200){
          //重新get图片集合
          //根据setId获取图片数据
          that.getWhereSetId(that);
        }
        app.showModal(data.msg);
      }
    });
  },

  //跳转到tabs导航菜单内容编辑内容页面
  updateTabaContent: function (e) {
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/add_tabs_content/add_tabs_content?id=' + e.currentTarget.id,
    })
  },

  //根据tabs导航菜单内容id删除内容
  deleteTabaContent: function (e) {
    var that = this;
    var id = e.currentTarget.id;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/tabs_content/deleteWhereId',
      method: "get",
      data: {
        id: id,
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          //刷新
          //根据tabs导航菜单id得到该菜单内容
          that.getWhereGetID(that.data.tabs_id);
        }
        app.showModal(res.data.msg);
      }
    })
  },

  //点击跳转到添加tabs导航菜单内容
  add_tabs_content: function (e) {
    var that = this;
    //得到tabs导航菜单id
    var tabs_id = that.data.tabs_id;
    //验证非空
    if (!app.checkInput(tabs_id)) {
      wx.navigateTo({
        url: '/pages/my/myShops/addShops/add_tabs_content/add_tabs_content?tabs_id=' + tabs_id,
      });
    } else {
      app.showModal("未选择tabs导航菜单，如有问题请联系管理员:18585094270");
    }
  },

  //根据tabs导航菜单id得到内容
  getWhereGetID: function (id) {
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/tabs_content/getWhereGetID',
      method: "get",
      data: {
        get_id: id,
      },
      success: function (res) {
        var data = res.data;
        if (data.state == 200) {
          that.setData({
            tabs_content: data.data
          });
        } else {
          app.showModal(data.msg);
        }
      }
    })
  },

  //tabs导航菜单，点击切换
  tabClick: function (e) {
    var that = this;
    var id = e.currentTarget.id;

    if (!app.checkInput(id)) {
      //根据tabs导航菜单id得到该菜单内容
      that.getWhereGetID(id);
    } else {
      that.setData({
        tabs_content: null,
      });
    }

    //设置
    that.setData({
      //将该id放入data中
      tabs_id: id,
      activeIndex: e.currentTarget.dataset.index,
    });
  },

  //删除tabs导航菜单
  bindtapDeleteTabs: function (e) {
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/tabs/deleteWhereId',
      method: "get",
      data: {
        id: e.currentTarget.dataset.tabsid,
      },
      success: function (res) {
        console.log(res);
        app.showModal(res.data.msg);
        if (res.data.state == 200) {
          that.setData({
            'releaseInfo.tabs_list': res.data.data
          });
        }
      }
    })
  },

  //跳转到,编辑tabs导航菜单页面
  edit_tabs: function (e) {
    var param = 'tabs_id=' + e.currentTarget.dataset.tabsid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/edit_tabs/edit_tabs?' + param,
    });
  },

  //跳转到,添加tabs导航菜单页面
  add_tabs: function (e) {
    var param = 'shops_id=' + e.currentTarget.id;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/add_tabs/add_tabs?' + param,
    })
  },

  //跳转到选择分类菜单页面
  xz_type_menu: function (e) {
    var param = 'id=' + e.currentTarget.id;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/xz_type_menu/xz_type_menu?' + param,
    })
  },

  //根据项目id得到详情信息
  getWhereId: function (that) {
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/release_info/getWhereId',
      method: "get",
      data: {
        id: that.data.id,
        openid: wx.getStorageSync("openid")
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          var releaseInfo = res.data.data;
          
          //匹配是否包含http://
          if (releaseInfo.user_info.avatar.indexOf("http") == -1) {
            releaseInfo.user_info.avatar = app.config.getImage + releaseInfo.user_info.avatar;
          }

          var activeIndex=that.data.activeIndex;
          var tabs_content = null;
          if (releaseInfo.tabs_list.length!=0){
            activeIndex=0;
            tabs_content = releaseInfo.tabs_list[activeIndex].tabs_content_List;
          }

          var images_list = releaseInfo.images;
          releaseInfo.images_list = images_list;

          that.setData({
            activeIndex: activeIndex,
            tabs_content: tabs_content,
            form: releaseInfo,
            releaseInfo: releaseInfo,
          });
        }
      }
    })
  },

  //表单提交
  bindFormSubmit: function (e) {
    var that = this;
    var form = {};
    //验证id是否为空
    if(!app.checkInput(that.data.id)){
      form.id=that.data.id;
    }
    //验证非空
    if (app.checkInput(e.detail.value.typeMenu_id)){
      app.showToast("请选择分类菜单!","none");
      return;
    } else if (app.checkInput(e.detail.value.title)) {
      app.showToast("标题不能为空!", "none");
      return;
    } else if (app.checkInput(e.detail.value.address)) {
      app.showToast("信息地址不能为空!", "none");
      return;
    } else {
      form.title = e.detail.value.title;
      form.lable = e.detail.value.lable;
      form.typeMenu_id = e.detail.value.typeMenu_id;
      form.describe_info = e.detail.value.describe_info;
      form.address = e.detail.value.address;
      form.creator= wx.getStorageSync("openid");
      form.model=e.detail.value.model;
    }

    //多张图片上传
    function uploadimg(path, pathArr, dataArr, id) {
      //验证id是否为空
      if (!app.checkInput(id)) {
        wx.uploadFile({
          url: app.config.zberPath_web + "zber_sys/images/imageUpload",//单张图片上传
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
              //根据项目id得到信息
              that.getWhereId(that);
            }
          }
        });
      }
    }

    //发起请求
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/release_info/save_or_update',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: form,
      success: function (res) {
        console.log(res);
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
                }
              }
            });
          }

          that.setData({
            id:data.id,
            tab_index:0,
          });

          app.showToast(res.data.msg, "none");
          //根据项目id得到信息
          that.getWhereId(that);
        } else if (res.data.state == 401) {
          app.btnLogin(res.data);
        }
        else {
          app.showModal(res.data.msg);
        }
      }
    })
  },

  //得到地址，位置信息
  getAddress: function (e) {
    var that = this;
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
            var address = res.address;

            //省
            var province_index = address.indexOf("省") + 1
            var province = address.substring(0, province_index);

            //市
            var city_index = address.indexOf("市") + 1;
            var city = address.substring(province_index, city_index);

            //区
            var area_index = address.indexOf("区") + 1;
            var area = address.substring(city_index, area_index);

            that.setData({
              "form.address": address,
              "form.position_info": res.name,
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

  //导航，点击切换
  tabClick_to: function (e) {
    var that = this;
    var name = e.currentTarget.dataset.name;

    if (name == "预览") {
      //刷新
      //根据项目id得到信息
      that.getWhereId(that);
    }

    //设置
    that.setData({
      tab_index: e.currentTarget.id,
    });
  },
})