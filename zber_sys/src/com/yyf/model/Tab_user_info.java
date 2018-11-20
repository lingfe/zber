package com.yyf.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
  * 文件名：Tab_userinfo.java
  * 描述： 用户信息表
  * 修改人： lingfe
  * 修改时间：2018年10月1日 上午8:29:02
  * 修改内容：
 */
public class Tab_user_info {
	
	/**
	 * 构造函数，用来设定初始值
	 */
	public Tab_user_info(){
		this.setId(UUID.randomUUID().toString());
		this.setCreator(getId());
		this.setModify(getId());
	}
	
	private String id;//  `id` VARCHAR(64) NOT NULL COMMENT '用户信息表id标识',
	private String username;//	  `username` VARCHAR(64) DEFAULT NULL COMMENT '用户名称',
	private String  realname;//	  `realname` VARCHAR(64) DEFAULT NULL COMMENT '真实姓名',
	private String avatar;//	  `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像',
	private int is_follow=0;//	  `is_follow` INT(11) DEFAULT '0' COMMENT '是否显示关注按钮,0=否，不显示，1=是,显示。',
	private int relo=0;//	  `relo` INT(11) DEFAULT '0' COMMENT '用户角色，\n   0=普通用户\n   1=商户用户\n   2=管理用户\n   3=企业用户',
	private int credit=100;//	  `credit` INT(11) DEFAULT '100' COMMENT '信用,\n   0-50,信用低\n   50-80,信用一般\n   80-100,信用高\n   100+,非常高',
	private int is_authentication=0;//	  `is_authentication` INT(11) DEFAULT '0' COMMENT '是0=未认证,否1=已认证，认证?',
	private String businessTime="09:00-19:00";//	  `businessTime` VARCHAR(64) DEFAULT '09:00-19:00' COMMENT '营业时间，默认:09:00-19:00',
	private String pwd="123456";//	  `pwd` VARCHAR(64) DEFAULT NULL COMMENT '登录密码',
	private String tel;//	  `tel` VARCHAR(64) DEFAULT NULL COMMENT '电话',
	private String email;//	  `email` VARCHAR(64) DEFAULT NULL COMMENT '邮件',
	private String mobile;//	  `mobile` VARCHAR(64) DEFAULT NULL COMMENT '移动电话号码',
	private String qq;//	  `qq` VARCHAR(64) DEFAULT NULL COMMENT 'QQ',
	private Double balance=0.00;//	  `balance` DOUBLE DEFAULT '0.00' COMMENT '余额',
	private Date lastTime;//	  `lastTime` DATETIME DEFAULT NULL COMMENT '最后一次登陆时间',
	private int state=1;//	  `state` INT(11) DEFAULT '0' COMMENT '账号状态：0未启用，1正常，2、异常，3冻结',
	private String idCard;//	  `idCard` VARCHAR(255) DEFAULT NULL COMMENT '身份证号码',
	private String provinceCode;//	  `provinceCode` VARCHAR(64) DEFAULT NULL COMMENT '省',
	private String provinceName;//	  `provinceName` VARCHAR(64) DEFAULT NULL COMMENT '省',
	private String cityCode;//	  `cityCode` VARCHAR(64) DEFAULT NULL COMMENT '市',
	private String cityName;//	  `cityName` VARCHAR(64) DEFAULT NULL COMMENT '市',
	private String regionCode;//	  `regionCode` VARCHAR(64) DEFAULT NULL COMMENT '区',
	private String regionName;//	  `regionName` VARCHAR(64) DEFAULT NULL COMMENT '区',
	private String address;//	  `address` VARCHAR(256) DEFAULT NULL COMMENT '详细地址',
	private String remark;//	  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAUL	T NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	//add   ===>>表示该字段是后来添加，会同步到数据库
	private String openid;//小程序用户id
    private int is_merchant=0;//是否是商户。0=否，不是;1=是
	
	//param ===>>表示该字段是参数需要,不会同步到数据    
	public Tab_user_follow user_follow=new Tab_user_follow(); //关注信息
	public int follow_num;//关注人数
	public int like_num;//喜欢总数量
	
	
	
	public int getIs_merchant() {
		return is_merchant;
	}
	public void setIs_merchant(int is_merchant) {
		this.is_merchant = is_merchant;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getIs_follow() {
		return is_follow;
	}
	public void setIs_follow(int is_follow) {
		this.is_follow = is_follow;
	}
	public int getRelo() {
		return relo;
	}
	public void setRelo(int relo) {
		this.relo = relo;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getIs_authentication() {
		return is_authentication;
	}
	public void setIs_authentication(int is_authentication) {
		this.is_authentication = is_authentication;
	}
	public String getBusinessTime() {
		return businessTime;
	}
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
