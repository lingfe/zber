package com.yyf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
  * 文件名：Tab_project_like_num.java
  * 描述： 用户喜欢表
  * 修改人： lingfe
  * 修改时间：2018年10月4日 上午8:05:47
  * 修改内容：
 */
public class Tab_user_like {
	 
	    private String id;//	   id VARCHAR(64) NOT NULL COMMENT '项目喜欢人数表id标识',
	    private String project_id;//	   project_id VARCHAR(64) NOT NULL COMMENT '项目id',
	    private int state=0;//	  `state` INT(11) DEFAULT '0' COMMENT '状态.0=bu喜欢，1=喜欢',
	   
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
		public String getProject_id() {
			return project_id;
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
		public void setProject_id(String project_id) {
			this.project_id = project_id;
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
