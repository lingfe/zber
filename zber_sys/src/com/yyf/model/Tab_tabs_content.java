package com.yyf.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
  * 文件名：Tab_tabs_content.java
  * 描述： tabs菜单导航的内容表
  * 修改人： lingfe
  * 修改时间：2018年11月17日 下午4:01:45
  * 修改内容：
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Tab_tabs_content {
	
	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT 'tabs菜单导航的内容表id标识',
	private String get_id;//	  `get_id` VARCHAR(64) DEFAULT NULL COMMENT '根据该id得到该数据',
	private int model=0;//	  `model` INT(11) DEFAULT '0' COMMENT '布局方式',
	
	private String title;//	title VARCHAR(255) COMMENT '标题',  
	private String content_bold_first;//	content_bold_first VARCHAR(1025) COMMENT '内容加粗，放在内容首',
	private String content;//	content VARCHAR(1024) COMMENT '内容',
	private String content_bold_tail;//	content_bold_tail VARCHAR(1024) COMMENT '内容加粗，放在内容尾',
	private String content_to;//	content_to VARCHAR(1024) COMMENT '内容2,当加粗内容在需要放在中间时使用。',
	private String img_txt;//	img_txt VARCHAR(255) COMMENT '图片说明文本',
	private int state=0;//状态
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator="admin";//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',==
	private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	
	//add   ===>>表示该字段是后来添加，会同步到数据库
	
	//param ===>>表示该字段是参数需要,不会同步到数据
	public List<Tab_images> images_list;//图片信息
	
	public String getId() {
		return id;
	}
	public String getGet_id() {
		return get_id;
	}
	public int getModel() {
		return model;
	}
	public String getTitle() {
		return title;
	}
	public String getContent_bold_first() {
		return content_bold_first;
	}
	public String getContent() {
		return content;
	}
	public String getContent_bold_tail() {
		return content_bold_tail;
	}
	public String getContent_to() {
		return content_to;
	}
	public String getImg_txt() {
		return img_txt;
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
	public void setGet_id(String get_id) {
		this.get_id = get_id;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent_bold_first(String content_bold_first) {
		this.content_bold_first = content_bold_first;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setContent_bold_tail(String content_bold_tail) {
		this.content_bold_tail = content_bold_tail;
	}
	public void setContent_to(String content_to) {
		this.content_to = content_to;
	}
	public void setImg_txt(String img_txt) {
		this.img_txt = img_txt;
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
