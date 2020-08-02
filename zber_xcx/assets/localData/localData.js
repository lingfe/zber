
export default{
  //模块值说明
  model:[
    0,//闪租。钓鱼竿
    1,//野货。八月瓜
    2,//预约。预约剪发
  ],

  //布局模板
  layoutModel:[
    {
      model:0
    },
    {
      model:1,
      //图片
      images:[{
        imgUrl:"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1519751787,2797767522&fm=27&gp=0.jpg"
      }],
      //标题
      title:"新货3m钓鱼竿",
      //价格参数
      price:{
        present_price:5,//现价
        price_company: "天",//价格单位：月,株,盒,斤，人等
        original_price: 80,//原件
      }, 
      //描述
      describe_info:"全新3m钓鱼竿,鱼饵。租满三个月免费送，全新3m钓鱼竿,鱼饵。租满三个月免费送",
    },
    {
      model:2,
      //图片
      images: [{
        imgUrl: "http://108108byg.com/uploads/allimg/170831/1-1FS1222647.jpg"
      }],
      //标题
      title: "贵州贵阳互联网项目“周边儿”小程序融资3w-300w",
      //价格参数
      price:{
        surplusNum: 68,//剩余数量%
        present_price: 5,//现价
        price_company: "天",//价格单位：月,株,盒,斤，人等
      },
      //销售量
      sell_num:12,
      //转发量
      share_num:224,
      //用户信息
      user_info:{
        avatar: "http://108108byg.com/uploads/allimg/170831/1-1FS1222647.jpg",//头像
        username:"零风",//用户名称
      },
      //喜欢人数
      like_num:12
    },
    {
      model: 3,
      //图片
      images: [{
        imgUrl: "https://gss0.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/bainuo/crop%3D0%2C253%2C802%2C485%3Bw%3D720%3Bq%3D99/sign=98f69c2091504fc2b610ea45d8edcb23/e7cd7b899e510fb3b8fc67bfd333c895d1430c34.jpg"
      }],
      //标题
      title: "雾都美容美发店烫染368",
      //描述
      describe_info: "仅售368元，价值1888元美发设计师亲自染烫2选1！免费WiFi，需预约！ ",
      //价格参数
      price: {
        per_capita_price: 68,//人均消费
      },
      //用户信息
      user_info:{
        mobile:"08412342423",//联系电话
      },
      //更新时间
      mdate:"2019-10-12 14:33",
    },
    {
      model:4,
      //是否可以预约
      is_subscribe:1,
      logo:"https://p0.meituan.net/deal/3f47cdea4688224ddc30a1047e87472b93015.jpg",//商铺logo
      shopsName:"A谯家大头炮特色烤鱼",//商户名称
      //价格参数
      price: {
        per_capita_price:45,//人均价
        charging_fee:30,//起送价
        distribution_fee:4,//配送费
      },
      //商品信息
      commodity_list:[{
        id:"",//商品id
        //商品图片
        img:"https://p0.meituan.net/shaitu/59178c00c2bea2254344374ce67447512265801.jpg@55w_55h_1e_1c",
        commodityName:"烤鱼江团一条3斤左右",//商品名称
        share_num:33,//转发量
        like_num:44,//喜欢人数
        //价格参数
        price:{
          present_price:88//价格
        }
      }],
      //用户信息
      user_info:{
        is_follow:1,
        //用户关注信息
        user_follow:{
          state:1//是否关注
        }
      }
    },
    {
      model:5,
      //图片
      images:[{
        imgUrl:"http://www.qnong.com.cn/uploadfile/2018/0910/20180910111719388.jpg",
      }, 
      {
          imgUrl: "http://www.qnong.com.cn/uploadfile/2018/0910/20180910111719388.jpg",
        }, {
          imgUrl: "http://www.qnong.com.cn/uploadfile/2018/0910/20180910111719388.jpg",
        }],
      //标题
      title:"",
      //用户信息
      user_info:{
        avatar: "http://f.hiphotos.baidu.com/baike/s%3D220/sign=9467fc4add54564ee165e33b83df9cde/d53f8794a4c27d1e0f28fb9d1bd5ad6eddc43859.jpg",//头像
        username: "土元养殖场",//用户名称
        is_authentication:1,//是否实名
        //用户关注信息
        is_follow:1,
        user_follow: {
          state: 1//是否关注
        }
      }
    }
  ],

  //布局说明
  bujusming_list: [{
    "title": "title表示标题,这是标题部分",
    "content": "content表示内容,如:八月瓜产地分布于山西、湖南、河南、陕西、安徽、浙江、江西、福建、湖北、广东、广西、四川、重庆、云南、贵州、西藏等地，其中以贵州铜仁、湖南张家界天子山景区居多，农历八月瓜熟开口，索溪峪、杨家界等山麓谷地、林缘灌木丛中野生资源丰富，为上乘野果。",
    "img": "http://108108byg.com/uploads/allimg/180202/3-1P202210558.jpg",
    "text": "img图片,这是图片说明",
    "content_Bold": "这里的内容加粗了，content_Bold",
  }, {
    "content_Bold": "这里另外一个item，这里的内容也加粗了，content_Bold",
  }],

  //价格单位
  price_company: ["月", "株", "盒", "斤","人", "份", "件"],


  throwInTheCityData: [{        //投放城市数据   
    name: "华东地区",
    index: 0,
    content: [{
      name: "山东",
      checked: false,
    }, {
      name: "江苏",
      checked: false,
    }, {
      name: "安徽",
      checked: false,
    }, {
      name: "浙江",
      checked: false,
    }, {
      name: "福建",
      checked: false,
    }, {
      name: "上海",
      checked: false,
    }]

  }, {
    name: '华南地区',
    index: 1,
    content: [{
      name: "广东",
      checked: false,
    }, {
      name: "广西",
      checked: false,
    }, {
      name: '海南',
      checked: false,
    }]
  }, {
    name: "华中地区",
    index: 2,
    content: [{
      name: "湖北",
      checked: false,
    }, {
      name: "湖南",
      checked: false
    }, {
      name: "河南",
      checked: false
    }, {
      name: "江西",
      checked: false
    }]
  }, {
    name: '华北地区',
    index: 3,
    content: [{
      name: "北京",
      checked: false,
    }, {
      name: "天津",
      checked: false
    }, {
      name: "河北",
      checked: false
    }, {
      name: "山西",
      checked: false
    }, {
      name: "内蒙古",
      checked: false
    }]
  }, {
    name: "西北地区",
    index: 4,
    content: [{
      name: "宁夏",
      checked: false
    }, {
      name: "新疆",
      checked: false
    }, {
      name: "青海",
      checked: false
    }, {
      name: "陕西",
      checked: false
    }, {
      name: "甘肃",
      checked: false
    }]
  }, {
    name: "西南地区",
    index: 5,
    content: [{
      name: "四川",
      checked: false
    }, {
      name: "云南",
      checked: false
    }, {
      name: "贵州",
      checked: false
    }, {
      name: "西藏",
      checked: false
    }, {
      name: "重庆",
      checked: false
    }]
  }, {
    name: "东北地区",
    index: 6,
    content: [{
      name: "辽宁",
      checked: false
    }, {
      name: "吉林",
      checked: false
    }, {
      name: "黑龙江",
      checked: false,
    }]
  }, {
    name: "台港澳地区",
    index: 7,
    content: [{
      name: "台湾",
      checked: false,
    }, {
      name: "香港",
      checked: false
    }, {
      name: "澳门",
      checked: false
    }]
  }],
  sxData: [{          //筛选数据
    name: "金额",
    content: [{
      minThreshold: '全部',
      maxThreshold: null,
      value: '0',
      checked: true,
    }, {
      minThreshold: 0,
      maxThreshold: 1,
      value: '1',
      checked: false,
    }, {
      minThreshold: 1,
      maxThreshold: 5,
      value: '2',
      checked: false,
    }, {
      minThreshold: 5,
      maxThreshold: 50,
      value: '3',
      checked: false,
    }, {
      minThreshold: 50,
      maxThreshold: null,
      value: '4',
      checked: false,
    }],
  },
  {
    name: "类型",
    content: [{
      name: '全部',
      value: '0',
      checked: false,
    }, {
      name: '合伙创业',
      value: '1001',
      checked: false,
    }, {
      name: '干股纳才 ',
      value: '1002',
      checked: false,
    }, {
      name: '加盟代理',
      value: '1003',
      checked: false,
    }, {
      name: '股权交易',
      value: '1004',
      checked: false,
    }, {
      name: '生意转让',
      value: '1005',
      checked: false,
      notype: '非搭伙类型',
    }, {
      name: '金融理财',
      value: '1006',
      checked: false,
      notype: '非搭伙类型',
    }, {
      name: '房产投资',
      value: '1007',
      checked: false,
      notype: '非搭伙类型',
    }, {
      name: '其他',
      value: '1008',
      checked: false,
      notype: '非搭伙类型',
    }],
  },
  {
    name: "行业",
    content: [{
      name: '全部',
      value: '0',
      checked: false,
    }, {
      name: '餐饮',
      value: '1',
      checked: false,
    }, {
      name: '休闲娱乐',
      value: '2001',
      checked: false,
    }, {
      name: '互联网',
      value: '2',
      checked: false,
    }, {
      name: '传媒',
      value: '3',
      checked: false,
    }, {
      name: "教育",
      value: '30001',
      checked: false,
    }, {
      name: '装修',
      value: '4',
      checked: false,
    }, {
      name: "生活服务",
      value: '40001',
      checked: false,
    }, {
      name: "婚庆",
      value: '40002',
      checked: false,
    }, {
      name: '百货',
      value: '5',
      checked: false,
    }, {
      name: '医疗保健',
      value: '6',
      checked: false,
    }, {
      name: "美容美发",
      value: '7',
      checked: false
    }, {
      name: '汽车',
      value: '8',
      checked: false
    }, {
      name: '地产',
      value: '9',
      checked: false
    }, {
      name: '金融',
      value: '10',
      checked: false,
    }, {
      name: '其他',
      value: '11',
      checked: false
    }],
  }]
}