package com.yyf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_user_follow.java
  * 描述： 用户关注表
  * 修改人： lingfe
  * 修改时间：2018年10月19日 下午12:33:58
  * 修改内容：
 */
public class Tab_user_follow {
	
	private String id;//	id VARCHAR(64) NOT NULL COMMENT '用户关注表id标识',
	private String user_id;//	user_id VARCHAR(64) COMMENT '用户id，被关注用户',
	private int state=0;//	  `state` INT(11) DEFAULT '0' COMMENT '状态.0=未关注，1=已关注',
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator="admin";//	 创建人，在这里同时也表示关注者的用户id
	private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	//add   ===>>表示该字段是后来添加，会同步到数据库
			
			
	//param ===>>表示该字段是参数需要,不会同步到数据库
	public Tab_user_info user_info;//用户信息
	public Tab_release_info release_info;//项目信息
	public int model=6;//用于布局
	
	
	public String getId() {
		return id;
	}
	public String getUser_id() {
		return user_id;
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
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
