package com.yyf.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
  * 文件名：Tab_apply_shops.java
  * 描述： 商户的商铺信息表
  * 修改人： lingfe
  * 修改时间：2018年10月7日 下午5:11:17
  * 修改内容：
 */
public class Tab_apply_shops {
	
	private String id;//id VARCHAR(64) NOT NULL COMMENT '商户的商铺信息表id标识',
	private String shopsName;//	shopsName VARCHAR(64) COMMENT '商铺名称',
	private String businessHours;//	businessHours VARCHAR(64) COMMENT '营业时间',
	private String logo;//	logo VARCHAR(255) COMMENT '商铺logo或门面照片',
	private String city;//	city VARCHAR(20) COMMENT '城市',
	private String contactNumber;//	`contactNumber` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
	private String address;//	`address` VARCHAR(255) DEFAULT NULL COMMENT '地址',
	private String position_info;//	`position_info` VARCHAR(255) DEFAULT NULL COMMENT '位置信息，地点名称,位置名称',
	private String images;//	`images` VARCHAR(1024) DEFAULT NULL COMMENT '门店内外周边环境照片',
	private int state;//  `state` INT(11) DEFAULT '0' COMMENT '状态,0=未提交审核,1=审核中,2=审核通过,3=审核不通过',
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator="admin";//	  `creator`创建人。在这里表示商户，商铺用户的id，或openid',
	private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	//add   ===>>表示该字段是后来添加，会同步到数据库
	private String type_menu_id;//分类菜单id,表示类别，表面该商铺隶属于该分类菜单下
	private int model=0;//布局model，0=默认
	private int is_subscribe=0;//是否可以预约?0=不可以，1=可以
	
	//param ===>>表示该字段是参数需要,不会同步到数据
	private List<Tab_images> images_list; //照片集合，商铺内外环境照片
	public List<String> lable_list; //标签
	public List<Tab_tabs> tabs_list;//tabs导航菜单
	public int share_num;//分享次数
	public int browse_num;//浏览次数
	public List<Tab_shops_commodity> commodity_list;//用于储存该商铺的推荐商品
	public int like_num;//喜欢人数
	public Tab_user_info user_info;//用户信息
	public List<Tab_shopsChooseType_tabs> shopsChooseType_tabs_list;//商铺选购tabs分类菜单
	public Tab_price shops_price;//商铺价格参数
	public Tab_user_like user_like; //喜欢信息
	
	
	public int getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(int is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public String getType_menu_id() {
		return type_menu_id;
	}
	public void setType_menu_id(String type_menu_id) {
		this.type_menu_id = type_menu_id;
	}
	public List<Tab_images> getImages_list() {
		return images_list;
	}
	public void setImages_list(List<Tab_images> images_list) {
		this.images_list = images_list;
	}
	public String getId() {
		return id;
	}
	public String getShopsName() {
		return shopsName;
	}
	public String getBusinessHours() {
		return businessHours;
	}
	public String getLogo() {
		return logo;
	}
	public String getCity() {
		return city;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public String getPosition_info() {
		return position_info;
	}
	public String getImages() {
		return images;
	}
	public int getState() {
		return state;
	}
	public Date getCdate() {
		return cdate;
	}
	public Date getMdate() {
		return mdate;
	}
	public String getCreator() {
		return creator;
	}
	public String getModify() {
		return modify;
	}
	public String getVersion() {
		return version;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPosition_info(String position_info) {
		this.position_info = position_info;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
