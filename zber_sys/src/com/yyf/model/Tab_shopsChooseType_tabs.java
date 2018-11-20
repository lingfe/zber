package com.yyf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_shopsChooseType_tabs.java
  * 描述： 商铺选购分类tabs表
  * 修改人： lingfe
  * 修改时间：2018年10月7日 下午8:35:16
  * 修改内容：
 */
public class Tab_shopsChooseType_tabs {
	
	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '商铺选购分类tabs表id标识',
	private String shops_id;//	  `shops_id` VARCHAR(64) DEFAULT NULL COMMENT '商铺id',
	private String tabs_name;//	  `tabs_name` VARCHAR(10) DEFAULT NULL COMMENT '菜单名称',
	private String content;//	  `content` TEXT COMMENT '详情内容:json数据，默认图文布局',
	private String model;//	  `model` INT(11) DEFAULT '0' COMMENT '详情布局方式 0=其他，默认 ',
	private int state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0=正常，1=停用。。',
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator="admin";//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',==
	private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	
	public String getId() {
		return id;
	}
	public String getShops_id() {
		return shops_id;
	}
	public String getTabs_name() {
		return tabs_name;
	}
	public String getContent() {
		return content;
	}
	public String getModel() {
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
	public void setShops_id(String shops_id) {
		this.shops_id = shops_id;
	}
	public void setTabs_name(String tabs_name) {
		this.tabs_name = tabs_name;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setModel(String model) {
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
