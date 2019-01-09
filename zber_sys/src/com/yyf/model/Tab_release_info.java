package com.yyf.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_release_info.java
  * 描述： 项目信息表，发布数据信息表
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午8:50:19
  * 修改内容：
 */
public class Tab_release_info {
	
		private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '发布数据信息表id标识',
		private String typeMenu_id;// 	  `typeMenu_id` VARCHAR(64) NOT NULL COMMENT '分类菜单id， 在这里标识该数据归属于该id菜单',
		private int is_subscribe=0;//	  `is_subscribe` INT(11) DEFAULT '0' COMMENT '是否可以预约0=否，不可以，1=是,可以。',
		private int details_model=0;//	  `details_model` INT(11) DEFAULT '0' COMMENT '详情布局model,\0=其他，默认布局方式。',
		private String lable;//	  `lable` VARCHAR(1024) DEFAULT NULL COMMENT '标签',
		private String title;//	  `title` VARCHAR(128) NOT NULL COMMENT '标题、主题',
		private String describe_info;//	  `describe_info` VARCHAR(1024) DEFAULT NULL COMMENT '描述信息',
		private int model=0;//	  `model` INT(11) DEFAULT '0' COMMENT '推荐时布局model。 0,其他，默认布局方式\n 1,//官方闪租（常规，专用布局方式）2,//新鲜野货（常规，专用布局方式）3,//生活预约（常规，专用布局方式）4,//餐饮食品（常规，专用布局方式）  5,//特殊宠物（常规，专用布局方式）',
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
		private String address;//表示服务地址，服务范围地址，多个用“,”隔开。
		
		//param ===>>表示该字段是参数需要,不会同步到数据
		private int share_num;//分享次数
		private int like_num;//	 总喜欢人数',
		private int sell_num;//	总销售数量',
		private int browse_num;//浏览次数
		public Tab_lbt_attribute lbt_attribute;//轮播图属性
		public Tab_user_info user_info;//用户信息
		public Tab_price price;//价格信息
		public List<Tab_images> images;//图片信息
		public List<Tab_tabs> tabs_list;//tabs导航菜单
		public List<String> lable_list;//标签数组
		public Tab_user_like user_like;//喜欢信息
		
		
		
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public int getBrowse_num() {
			return browse_num;
		}
		public void setBrowse_num(int browse_num) {
			this.browse_num = browse_num;
		}
		public int getShare_num() {
			return share_num;
		}
		public void setShare_num(int share_num) {
			this.share_num = share_num;
		}
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTypeMenu_id() {
			return typeMenu_id;
		}
		public void setTypeMenu_id(String typeMenu_id) {
			this.typeMenu_id = typeMenu_id;
		}
		public int getIs_subscribe() {
			return is_subscribe;
		}
		public void setIs_subscribe(int is_subscribe) {
			this.is_subscribe = is_subscribe;
		}
		public int getDetails_model() {
			return details_model;
		}
		public void setDetails_model(int details_model) {
			this.details_model = details_model;
		}
		public String getLable() {
			return lable;
		}
		public void setLable(String lable) {
			this.lable = lable;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getModel() {
			return model;
		}
		public void setModel(int model) {
			this.model = model;
		}
		public int getLike_num() {
			return like_num;
		}
		public void setLike_num(int like_num) {
			this.like_num = like_num;
		}
		public int getSell_num() {
			return sell_num;
		}
		public void setSell_num(int sell_num) {
			this.sell_num = sell_num;
		}
		public String getDescribe_info() {
			return describe_info;
		}
		public void setDescribe_info(String describe_info) {
			this.describe_info = describe_info;
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
