// pages/my/myShops/addShops/addShops.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabs: ["预览","基本信息","其他导航菜单内容说明"],
    tab_index: 1,
    activeIndex: -1,         //tab切换下标   
    activeIndex_to:-1,

    arr: [],  //选择图片的数组，预留。不包含编辑之前的数据，用于组装
    //布局说明
    bujusming_list:app.localData.bujusming_list,
  }, 
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if(!app.checkInput(options.id)){
      //根据商铺id得到商铺详情
      that.getWhereId_detail(options.id);
    }else{
      //得到本地数据
      that.setData({
        commodity_list:[],
      });
    }

    //得到用户信息
    that.setData({
      id:options.id,
      user_info: wx.getStorageSync("userInfo")
    })
  },
  
  //跳转到设置价格参数页面
  add_shops_price:function(e){
    var param = 'shops_id=' + e.currentTarget.dataset.shopsid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/add_shops_price/add_shops_price?' + param,
    })
  },

  //跳转到选择分类菜单页面
  xz_type_menu:function(e){
    var param ='id='+ e.currentTarget.dataset.tmid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/xz_type_menu/xz_type_menu?' + param,
    })
  },

  //添加商铺商品
  add_commodity:function(e){
    var param = 'sctt='+ e.currentTarget.dataset.sctt+
      '&shops_id='+ e.currentTarget.dataset.shopsid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/add_commodity/add_commodity?' + param,
    })
  },

  //删除商铺商品信息
  delete_commodity: function (e) {
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/shops_commodity/deleteWhereId',
      method: "get",
      data: {
        id: e.currentTarget.id,
      },
      success: function (res) {
        app.showModal(res.data.msg);
        if (res.data.state = 200) {
          //根据根据shopsChooseType_tabs_id得到商品集合
          that.getWhereShopsChooseType_tabs_id(that.data.shopsChooseType_tabs_id);
        }
      }
    })
  },

  //编辑商铺商品
  commodity_edit:function(e){
    var param = 'id='+e.currentTarget.id+
      '&shops_id='+ e.currentTarget.dataset.shopsid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/commodity_edit/commodity_edit?' + param,
    })
  },

  //删除商铺导航菜单-->选购分类tabs菜单
  bindtapDeleteShopChooseTypeTabs: function (e) {
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/shopsChooseType_tabs/deleteWhereId',
      method: "get",
      data: {
        id: e.currentTarget.dataset.sctt,
      },
      success: function (res) {
        console.log(res);
        app.showModal(res.data.msg);
        if (res.data.state == 200) {
          that.setData({
            'basicInfo.shopsChooseType_tabs_list': res.data.data
          });
        }
      }
    })
  },

  //跳转到,编辑商铺导航菜单-->选购分类tabs菜单页面
  edit_shopsChooseType_tabs: function (e) {
    var param = 'sctt='+ e.currentTarget.dataset.sctt;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/edit_shopsChooseType_tabs/edit_shopsChooseType_tabs?' +                  param,
    })
  },

  //跳转到,添加商铺导航菜单-->选购分类tabs菜单页面
  add_shopsChooseType_tabs:function(e){
    var param = 'shops_id='+e.currentTarget.dataset.shopsid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/add_shopsChooseType_tabs/add_shopsChooseType_tabs?' +param,
    })
  },

  //删除商铺tabs导航菜单
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
            'basicInfo.tabs_list':res.data.data
          });
        }
      }
    })
  },

  //跳转到,编辑商铺tabs导航菜单页面
  edit_tabs:function (e) {
    var param = 'tabs_id='+ e.currentTarget.dataset.tabsid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/edit_tabs/edit_tabs?' + param,
    });
  },

  //跳转到,添加商铺tabs导航菜单页面
  add_tabs:function(e){
    var param='shops_id='+e.currentTarget.dataset.shopsid;
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/add_tabs/add_tabs?'+param,
    })
  },

  //跳转到tabs导航菜单内容编辑内容页面
  updateTabaContent:function(e){
    wx.navigateTo({
      url: '/pages/my/myShops/addShops/add_tabs_content/add_tabs_content?id='+e.currentTarget.id,
    })
  },

  //根据tabs导航菜单内容id删除内容
  deleteTabaContent:function(e){
    var that=this;
    var id=e.currentTarget.id;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/tabs_content/deleteWhereId',
      method:"get",
      data:{
        id:id,
      },
      success:function(res){
        console.log(res);
        if(res.data.state==200){
          //刷新
          //根据tabs导航菜单id得到该菜单内容
          that.getWhereGetID(that.data.tabs_id);
        }
        app.showModal(res.data.msg);
      }
    })
  },

  //点击跳转到添加tabs导航菜单内容
  add_tabs_content:function(e){
    var that=this;
    //得到tabs导航菜单id
    var tabs_id = that.data.tabs_id;
    //验证非空
    if(!app.checkInput(tabs_id)){
      wx.navigateTo({
        url: '/pages/my/myShops/addShops/add_tabs_content/add_tabs_content?tabs_id='+tabs_id,
      });
    }else{
      app.showModal("未选择tabs导航菜单，如有问题请联系管理员:18585094270");
    }
  },

  //根据tabs导航菜单id得到内容
  getWhereGetID:function(id){
    var that=this;
    wx.request({
      url: app.config.zberPath_web +'zber_sys/tabs_content/getWhereGetID',
      method:"get",
      data:{
        get_id:id,
      },
      success:function(res){
        var data=res.data;
        if(data.state==200){
          that.setData({
            tabs_content:data.data
          });
        }else{
          app.showModal(data.msg);
        }
      }
    })
  },

  //根据图片id删除图片信息
  setDelImages:function(id){
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/images/deleteWhereId',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data:{id:id},
      success:function(res){
        console.log(res);
      }
    })
  },

  //根据商铺id得到商铺详情
  getWhereId_detail: function (id) {
    var that=this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/apply_shops/getWhereId_detail',
      method: "get",
      data: {
        id: id,
        openid: wx.getStorageSync("openid")
      },
      success: function (res) {
        if (res.data.state = 200) {
          that.setData({
            //基本信息
            basicInfo: res.data.data,
            address:res.data.data.address,
            logo:res.data.data.logo,
          });
        }
      }
    })
  },

  //点击商品
  commodityBtn:function(e){
    var that=this;
    that.setData({
      commodity_id:e.currentTarget.id
    });
  },

  //根据shops_id得到商铺选购分类tabs集合
  getWhereShopsId:function(id){
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/shopsChooseType_tabs/getWhereShopsId',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        shops_id: id
      },
      success: function (res) {
        console.log(res);
        if (res.data.state == 200) {
          that.setData({
            shopsChooseType_tabs_list: res.data.data
          });
        }else{
          app.showModal(res.data.msg);
        }
      }
    });
  },

  //根据商铺id得到tabs导航菜单
  getWhereShopsId_tabsList: function (id) {
    var that = this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/tabs/getWhereShops_id',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        shops_id: id
      },
      success: function (res) {
        console.log(res);
        
        if (res.data.state == 200) {
          that.setData({
            info: res.data.data
          });
        }else{
          app.showModal(res.data.msg);
        }
      }
    });
  },

  //表单提交
  bindFormSubmit: function (e) {
    var that = this;
    var form = that.data.form;
    var id=that.data.id;

    //验证id是否为空，不为空就编辑
    if(!app.checkInput(id)){
      form = {};
      form.id=id;
      //修改人
      form.modify=wx.getStorageSync("openid");
      form.city=wx.getStorageSync("city");
      form.position_info=that.data.basicInfo.position_info;
      form.bl=true;
    }else{
      form.bl=false;
    }

    //验证非空
    if(app.checkInput(e.detail.value.logo)){
      app.showToast("请上传logo!", "none");
      return;
    } else if (app.checkInput(e.detail.value.type_menu_id)){
      app.showToast("请选择分类菜单!", "none");
      return;
    }
    if (app.checkInput(e.detail.value.shopsName)) {
      app.showToast("商铺名称不能为空!", "none");
      return;
    } else if (app.checkInput(e.detail.value.businessHours)) {
      app.showToast("营业时间不能为空!", "none");
      return;
    } else if (app.checkInput(e.detail.value.address)) {
      app.showToast("详细地址不能为空!", "none");
      return;
    } else {
      form.logo=e.detail.value.logo;
      form.shopsName = e.detail.value.shopsName;
      form.businessHours = e.detail.value.businessHours;
      form.contactNumber = e.detail.value.contactNumber;
      form.address = e.detail.value.address;
      form.type_menu_id=e.detail.value.type_menu_id;
    }


    if(form.bl){
      //先上传图片，修改
      var arr = that.data.arr;
      if (arr.length >= 1) {
        //上传图片数组
        uploadimg(arr.splice(0, 1), [], arr);
      }else{
        setReq();
      }
    }else{
      setReq();
    }

    //发起请求
    function setReq(){
      wx.request({
        url: app.config.zberPath_web + 'zber_sys/apply_shops/save_or_update',
        method: "POST",
        header: {
          "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        },
        data: form,
        success: function (res) {
          console.log(res);
          app.showModal(res.data.msg);
          if (res.data.state == 200) {

            //保存数据
            that.setData({
              activeIndex: -2,
              tab_index: 0,
              basicInfo: res.data.data,
              id: res.data.data.id
            });


            if (!form.bl) {
              //后上传图片，保存
              var arr = that.data.arr;
              if (arr.length >= 1) {
                //上传图片数组
                uploadimg(arr.splice(0, 1), [], arr);
              }
            }

            //刷新
            //根据商铺id得到商铺详情
            that.getWhereId_detail(that.data.id);
          }
        }
      })
    }

    //多张图片上传
    function uploadimg(path, pathArr, dataArr) {
      wx.uploadFile({
        url: app.config.getImage + "images/imageUpload",//上传大病救助图片 开发者服务器 url
        filePath: path[0]+"",                 //要上传文件资源的路径
        name: 'file',     //文件对应的 key , 开发者在服务器端通过这个 key 可以获取到文件二进制内容
        header: {                                   //HTTP 请求 Header , header 中不能设置 Referer
          "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        },
        formData: {
          openid: wx.getStorageSync("openid"),
          setid: that.data.id
        },                             //参数(HTTP 请求中其他额外的 form data)
        success: (resp) => {                         //接口调用成功的回调函数
          var json = JSON.parse(resp.data);
          pathArr.push(json.data);
          if (dataArr.length > 0) {
            //递归
            uploadimg(dataArr.splice(0, 1), pathArr, dataArr);
          } else {
            // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片\
            var images_list = that.data.basicInfo.images_list;
            pathArr.forEach(function (item) {
              images_list.push(item);
            });
            that.setData({
              "basicInfo.images_list": images_list
            });
            //发送请求
            setReq();
          }
        }
      });
    }
  },

  //导航，点击切换
  tabClick_to: function (e) {
    var that = this;
    var name=e.currentTarget.dataset.name;

    if(name == "预览"){
      //刷新
      //根据商铺id得到商铺详情
      that.getWhereId_detail(that.data.id);
    }

    //设置
    that.setData({
      tab_index: e.currentTarget.id,
    });
  },

  //tabs导航菜单，点击切换
  tabClick: function (e) {
    var that = this;
    var id=e.currentTarget.id;

    if (!app.checkInput(id)) {
      //根据tabs导航菜单id得到该菜单内容
      that.getWhereGetID(id);
    }else{
      that.setData({
        tabs_content:null,
      });
    }

    //设置
    that.setData({
      //将该id放入data中
      tabs_id:id,
      activeIndex: e.currentTarget.dataset.index,
    });
  },

  //根据根据shopsChooseType_tabs_id得到商品集合
  getWhereShopsChooseType_tabs_id:function(id){
    var that=this;
    wx.request({
      url: app.config.zberPath_web + 'zber_sys/shops_commodity/getWhereShopsChooseType_tabs_id',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      data: {
        shopsChooseType_tabs_id:id,
      },
      success:function(res){
        console.log(res);
        that.setData({
          'basicInfo.commodity_list':res.data.data
        });
      }
    })
  },

  //选购tabs分类菜单，点击切换
  switchRightTab(e) {
    var that = this;
    
    //根据根据shopsChooseType_tabs_id得到商品集合
    var id=e.currentTarget.id;
    that.getWhereShopsChooseType_tabs_id(id);

    //设置
    that.setData({
      shopsChooseType_tabs_id:id,
      activeIndex_to: e.currentTarget.dataset.index
    });
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
              "address": address,
              "form.position_info": res.name,
              city: city,
            });
          }
        })
      }
    })
  },

  //删除图片
  bindtapImageDelete_images: function (e) {
    var img = e.currentTarget.dataset.img;
    var that = this;
    var images_list = that.data.basicInfo.images_list;

    for (var j = 0; j < images_list.length; j++) {
      if (images_list[j].imgUrl == img) {
        //根据图片id删除图片
        that.setDelImages(images_list[j].id);
        images_list.splice(j, 1);
      }
    }

    that.setData({
      'basicInfo.images_list': images_list
    });
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

  //获取图片,logo
  chooseImage_logo: function (e) {
    var that = this;
    wx.chooseImage({
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        console.log(res);
        if (res.tempFilePaths.length > 1) {
          wx.showToast({ title: '只能选择一张照片', icon: 'error', duration: 3000, });
          return;
        }
        //上传图片到服务器
        uploadimg(res.tempFilePaths);
      }
    })

    //多张图片上传
    function uploadimg(imgPath) {
      wx.uploadFile({
        url: app.config.zberPath_web + 'zber_sys/images/imageUpload',   //开发者服务器 url
        filePath: imgPath[0],                          //要上传文件资源的路径
        name: 'file',        //文件对应的 key , 开发者在服务器端通过这个 key 可以获取到文件二进制内容
        header: {                                   //HTTP 请求 Header , header 中不能设置 Referer
          "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        },
        formData: {
          openid: wx.getStorageSync("openid")
        },                             //参数(HTTP 请求中其他额外的 form data)
        success: function (res) {                   //接口调用成功的回调函数
          console.log(res);
          var json = JSON.parse(res.data);
          that.setData({
            "logo": app.config.getImage + json.data.imgUrl
          });
          //上传成功，执行修改()

        },
        fail: function (res) {                      //接口调用失败的回调函数
          //提示
          wx.showToast({
            title: '上传文件失败',
            icon: 'loading',
            duration: 3000,
          });
          return;
        },
        complete: function () {                     //接口调用结束的回调函数（调用成功、失败都会执行）

        }
      });
    }
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
        var arr=that.data.arr;
        var images = res.tempFilePaths;
        images.forEach(function(item){
          arr.push(item);
        });
        
        that.setData({
          arr:arr
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