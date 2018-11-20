package com.yyf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
  * 文件名：Tab_user_apply_merchant.java
  * 描述： 用户申请成为商户表
  * 修改人： lingfe
  * 修改时间：2018年10月6日 下午4:08:38
  * 修改内容：
 */
public class Tab_user_apply_merchant {
	
	private String id;//	 `id` VARCHAR(64) NOT NULL COMMENT '用户申请成为商户表id标识',
	private String sfz;//	  sfz VARCHAR(64) COMMENT '身份证号',
	private String realName;//	  realName VARCHAR(10)  COMMENT '真实姓名',
	private String contactNumber;//	  contactNumber VARCHAR(20) COMMENT '联系电话',
	private String address;//	  address VARCHAR(255) COMMENT '地址',
	private String position_info;//	  position_info VARCHAR(255) COMMENT '位置信息',
	private String images;//	  images VARCHAR(1024) COMMENT '营业执照、身份证正反面照片',
	private int state=0;//	 `state` INT(11) DEFAULT '0' COMMENT '状态,0=申请中,1=申请通过..',
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator="admin";//	  创建人。在这里表示申请人，申请用户的id
	private String modify="admin";//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	
	
	public String getId() {
		return id;
	}
	public String getSfz() {
		return sfz;
	}
	public String getRealName() {
		return realName;
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
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
