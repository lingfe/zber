package com.yyf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_shops_commodity.java
  * 描述： 商铺商品表
  * 修改人： lingfe
  * 修改时间：2018年10月7日 下午9:25:45
  * 修改内容：
 */
public class Tab_shops_commodity {
	
	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '商铺商品表id标识',
	private String shopsChooseType_tabs_id;//	  `shopsChooseType_tabs_id` VARCHAR(64) DEFAULT NULL COMMENT '商铺选购分类tabs表id',
	private String commodityName;//	  `commodityName` VARCHAR(10) DEFAULT NULL COMMENT '商品名称',
	private String img;//	  img VARCHAR(255) COMMENT '商品图片(单张。1张)',
	private String content;//	 	  `content` TEXT COMMENT '详情内容:json数据，默认图文布局',
	private int model;//	  `model` INT(11) DEFAULT '0' COMMENT '详情布局方式 0=其他，默认 ',
	private int state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态，0=审核中,1=审核通过，2=不通过',
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator="admin";//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',==
	private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	//add   ===>>表示该字段是后来添加，会同步到数据库
	private String shops_recommend;//商铺推荐(商铺id),表示用于商铺推荐的商品，最多推荐3件商品。
	
	//param ===>>表示该字段是参数需要,不会同步到数据
	public Tab_price price;//价格参数
	public int like_num;//喜欢人数
	public int share_num;//转发次数
	
	
	public String getId() {
		return id;
	}
	public String getShops_recommend() {
		return shops_recommend;
	}
	public void setShops_recommend(String shops_recommend) {
		this.shops_recommend = shops_recommend;
	}
	public String getShopsChooseType_tabs_id() {
		return shopsChooseType_tabs_id;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public String getImg() {
		return img;
	}
	public String getContent() {
		return content;
	}
	public int getModel() {
		return model;
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
	public void setShopsChooseType_tabs_id(String shopsChooseType_tabs_id) {
		this.shopsChooseType_tabs_id = shopsChooseType_tabs_id;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setModel(int model) {
		this.model = model;
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
