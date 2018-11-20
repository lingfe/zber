package com.yyf.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
  * 文件名：Tab_type_menu.java
  * 描述： 分类菜单
  * 修改人： lingfe
  * 修改时间：2018年10月2日 上午8:58:59
  * 修改内容：
 */
public class Tab_type_menu {
	
	/**
	 * 构造函数，用来设定初始值
	 */
	public Tab_type_menu(){
		this.setId(UUID.randomUUID().toString());
		this.setCreator(getId());
		this.setModify(getId());
	}
	
	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '分类菜单表id标识',
	private String superiorId;//	  `superiorId` VARCHAR(64) DEFAULT '0' COMMENT '上级id,默认0，表示最高级!',
	private String typeMenuIcoUrl;//	  `typeMenuIcoUrl` VARCHAR(1024) NOT NULL COMMENT '分类菜单图标url',
	private String typeMenuName;//	  `typeMenuName` VARCHAR(20) NOT NULL COMMENT '分类菜单名称',
	private int isDisplay=1;//	  `isDisplay` INT(11) DEFAULT '1' COMMENT '是否显示，0=否,1=是,2=取消显示',
	private String navigator;//	  `navigator` VARCHAR(255) DEFAULT NULL COMMENT '跳转路径path',
	private int state=0;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0..',
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator="admin";//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',==
	private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSuperiorId() {
		return superiorId;
	}
	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}
	public String getTypeMenuIcoUrl() {
		return typeMenuIcoUrl;
	}
	public void setTypeMenuIcoUrl(String typeMenuIcoUrl) {
		this.typeMenuIcoUrl = typeMenuIcoUrl;
	}
	public String getTypeMenuName() {
		return typeMenuName;
	}
	public void setTypeMenuName(String typeMenuName) {
		this.typeMenuName = typeMenuName;
	}
	public int getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}
	public String getNavigator() {
		return navigator;
	}
	public void setNavigator(String navigator) {
		this.navigator = navigator;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModify() {
		return modify;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
