package com.yyf.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 文件名：Tab_lbt_attribute.java 
 * 描述： 轮播图属性表 
 * 修改人： lingfe 
 * 修改时间：2018年10月2日 上午9:08:02
 * 修改内容：
 */
public class Tab_lbt_attribute {

	private String id;// `id` VARCHAR(64) NOT NULL COMMENT
						// '轮播图属性表id标识(根据id去图片表获取图片)',
	private String indicator_dots;// `indicator_dots` VARCHAR(10) DEFAULT NULL
									// COMMENT '是=true,否=flase,显示面板指示点',
	private String indicator_color;// `indicator_color` VARCHAR(10) DEFAULT NULL
									// COMMENT '指示点颜色，color值',
	private String indicator_active_color;// `indicator_active_color`
											// VARCHAR(10) DEFAULT NULL COMMENT
											// '当前选中的指示点颜色，color值',
	private String autoplay;// `autoplay` VARCHAR(10) DEFAULT NULL COMMENT
							// '是=true,否=flase,自动切换',
	private int interval_time;// `interval_time` INT(11) DEFAULT NULL COMMENT
								// '自动切换时间间隔，毫秒ms',
	private int duration;// `duration` INT(11) DEFAULT NULL COMMENT
							// '滑动动画时长,毫秒ms',
	private int state;// 状态,0=正常,1=停用

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate = new Date();// `cdate` DATETIME DEFAULT
									// CURRENT_TIMESTAMP ON UPDATE
									// CURRENT_TIMESTAMP COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate = new Date();// `mdate` DATETIME DEFAULT
									// CURRENT_TIMESTAMP ON UPDATE
									// CURRENT_TIMESTAMP COMMENT '最后修改时间',
	private String creator = "admin";// `creator` VARCHAR(64) DEFAULT 'admin'
										// COMMENT '创建人',==
	private String modify = "admin";// `modify` VARCHAR(64) DEFAULT 'admin'
									// COMMENT '修改人',
	private String version = "0";// `version` VARCHAR(64) DEFAULT '0' COMMENT
									// '数据版本',
	
	//add   ===>>表示该字段是后来添加，会同步到数据库
	private String setId;//分类菜单id,项目id，其他id。如果=0，则表示最顶级，也就是服务菜单的轮播图属性
	
	//param ===>>表示该字段是参数需要,不会同步到数据
	public List<Tab_images> images_list;//图片集合
	
	
	public String getSetId() {
		return setId;
	}
	public void setSetId(String setId) {
		this.setId = setId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndicator_dots() {
		return indicator_dots;
	}
	public void setIndicator_dots(String indicator_dots) {
		this.indicator_dots = indicator_dots;
	}
	public String getIndicator_color() {
		return indicator_color;
	}
	public void setIndicator_color(String indicator_color) {
		this.indicator_color = indicator_color;
	}
	public String getIndicator_active_color() {
		return indicator_active_color;
	}
	public void setIndicator_active_color(String indicator_active_color) {
		this.indicator_active_color = indicator_active_color;
	}
	public String getAutoplay() {
		return autoplay;
	}
	public void setAutoplay(String autoplay) {
		this.autoplay = autoplay;
	}
	public int getInterval_time() {
		return interval_time;
	}
	public void setInterval_time(int interval_time) {
		this.interval_time = interval_time;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
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
