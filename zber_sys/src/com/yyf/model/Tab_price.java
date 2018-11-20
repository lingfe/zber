package com.yyf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_price.java
  * 描述： 价格表
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午8:11:10
  * 修改内容：
 */
public class Tab_price {
		
		private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '价格表id标识',
		private double original_price=0;//	  `original_price` DOUBLE DEFAULT '0' COMMENT '原价',
		private double present_price=0;//	  `present_price` DOUBLE DEFAULT '0' COMMENT '现价',
		private double per_capita_price=0;//	  `per_capita_price` DOUBLE DEFAULT '0' COMMENT '人均消费，人均价格',
		private String price_company="月";//	  `price_company` VARCHAR(10) DEFAULT '月' COMMENT '价格单位：月,株,盒,斤，人',
		private int surplusNum=0;//	  `surplusNum` INT(11) DEFAULT '0' COMMENT '剩余数量',
		private String surplusNum_company="份";//	  `surplusNum_company` VARCHAR(10) DEFAULT '份' COMMENT '剩余数量单位:份,件,个..',
		private int state=0;//	  `state` INT(11) DEFAULT '0' COMMENT '状态..',
	  
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
		private String setId;//项目id,商铺id,商品id
		private double distribution_fee=0;//配送费
		private double charging_fee=0;//起送费
		
		//param ===>>表示该字段是参数需要,不会同步到数据

		
		public String getId() {
			return id;
		}
		public String getSetId() {
			return setId;
		}
		public double getDistribution_fee() {
			return distribution_fee;
		}
		public double getCharging_fee() {
			return charging_fee;
		}
		public void setSetId(String setId) {
			this.setId = setId;
		}
		public void setDistribution_fee(double distribution_fee) {
			this.distribution_fee = distribution_fee;
		}
		public void setCharging_fee(double charging_fee) {
			this.charging_fee = charging_fee;
		}
		public void setId(String id) {
			this.id = id;
		}
		public double getOriginal_price() {
			return original_price;
		}
		public void setOriginal_price(double original_price) {
			this.original_price = original_price;
		}
		public double getPresent_price() {
			return present_price;
		}
		public void setPresent_price(double present_price) {
			this.present_price = present_price;
		}
		public double getPer_capita_price() {
			return per_capita_price;
		}
		public void setPer_capita_price(double per_capita_price) {
			this.per_capita_price = per_capita_price;
		}
		public String getPrice_company() {
			return price_company;
		}
		public void setPrice_company(String price_company) {
			this.price_company = price_company;
		}
		public int getSurplusNum() {
			return surplusNum;
		}
		public void setSurplusNum(int surplusNum) {
			this.surplusNum = surplusNum;
		}
		public String getSurplusNum_company() {
			return surplusNum_company;
		}
		public void setSurplusNum_company(String surplusNum_company) {
			this.surplusNum_company = surplusNum_company;
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
