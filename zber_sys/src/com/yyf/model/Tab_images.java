package com.yyf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
  * 文件名：Tab_images.java
  * 描述： 图片表
  * 修改人： lingfe
  * 修改时间：2018年10月2日 上午9:37:45
  * 修改内容：
 */
public class Tab_images {
		
		private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '图片表id标识',
		private String setId;//	  `setId` VARCHAR(64) NOT NULL COMMENT '设置用于哪个模块的id。比如(项目id，轮播图id):该图片来自首页轮播图，那么就用首页轮播图属性id获取图片，',
		private String imgUrl;//	  `imgUrl` VARCHAR(1024) NOT NULL COMMENT '图片url',
		private String navigator;//	  `navigator` VARCHAR(255) DEFAULT NULL COMMENT '图片跳转地址path',
		private String remark;//	  `remark` TEXT COMMENT '图片描述',
		private int state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0=正常,1=停用',
	  
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
		private String creator="admin";//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',==
		private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
		private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
		
		//add   ===>>表示该字段是后来添加，会同步到数据库
		private String full_path;//图片完整路径，全路径
		private String storage_path;	//图片存储路径
		private String custom_name;//图片自定义名称
		private String old_name;//图片原名称
		private String suffix;//图片后缀
		
		
		//param ===>>表示该字段是参数需要,不会同步到数据
		
				
		
		public String getId() {
			return id;
		}
		public String getFull_path() {
			return full_path;
		}
		public String getStorage_path() {
			return storage_path;
		}
		public String getCustom_name() {
			return custom_name;
		}
		public String getOld_name() {
			return old_name;
		}
		public String getSuffix() {
			return suffix;
		}
		public void setFull_path(String full_path) {
			this.full_path = full_path;
		}
		public void setStorage_path(String storage_path) {
			this.storage_path = storage_path;
		}
		public void setCustom_name(String custom_name) {
			this.custom_name = custom_name;
		}
		public void setOld_name(String old_name) {
			this.old_name = old_name;
		}
		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSetId() {
			return setId;
		}
		public void setSetId(String setId) {
			this.setId = setId;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public String getNavigator() {
			return navigator;
		}
		public void setNavigator(String navigator) {
			this.navigator = navigator;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
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
