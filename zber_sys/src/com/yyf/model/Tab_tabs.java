package com.yyf.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_tabs.java
  * 描述： 项目详情菜单表,tabs菜单导航
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午8:40:23
  * 修改内容：
 */
public class Tab_tabs {
		
		private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT 'tabs菜单导航id标识',
		private String project_id;//	  `project_id` VARCHAR(64) NOT NULL COMMENT '项目id',
		private String content;//	  `content` TEXT NOT NULL COMMENT '内容:json数据',
		private int tab_model;//	  `tab_model` INT(11) DEFAULT '0' COMMENT '该菜单的布局方式，\n	默认0=图文布局\n   ',
		private int state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态..',
	  
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
		private String creator="admin";//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',==
		private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
		private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
		
		//add
		private String tabs_name;//tabs菜单名称
		//param
		public List<Tab_tabs_content> tabs_content_List;//tabs导航菜单内容数据
		
		public String getTabs_name() {
			return tabs_name;
		}
		public void setTabs_name(String tabs_name) {
			this.tabs_name = tabs_name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getProject_id() {
			return project_id;
		}
		public void setProject_id(String project_id) {
			this.project_id = project_id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getTab_model() {
			return tab_model;
		}
		public void setTab_model(int tab_model) {
			this.tab_model = tab_model;
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
