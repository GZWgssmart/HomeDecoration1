package com.gs.bean;

import java.util.Date;

/**
 * 设计师案例表t_designer_case				
 * @author ID.LQF
 *
 */
public class DesignerCase {

	private String id;  //	unique_identifier	编号	主键，uuid
	private String designer_id;  //	unique_identifier	设计师编号	外键，关联到t_designer表的id字段
	private String name;  //	varchar(100)	案例名称	not null
	private String plot_name;  //	varchar(100)	小区名称	not null
	private String style;  //	varchar(20)	装修风格	not null
	private String image_1;	  //varchar(500)	图片1路径	
	private String image_2;   //	varchar(500)	图片2路径	
	private String image_3;  //	varchar(500)	图片3路径	
	private String image_4;  	// varchar(500)	图片4路径	
	private String image_5;  //	varchar(500)	图片5路径	
	private String des;  //	varchar(500)	描述	
	private Date created_time;   //	datetime	创建时间	添加时的系统当前时间
	private String status;  //	char(1)	是否可用，Y表示可用，N表示不可用	default Y
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesigner_id() {
		return designer_id;
	}
	public void setDesigner_id(String designer_id) {
		this.designer_id = designer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlot_name() {
		return plot_name;
	}
	public void setPlot_name(String plot_name) {
		this.plot_name = plot_name;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getImage_1() {
		return image_1;
	}
	public void setImage_1(String image_1) {
		this.image_1 = image_1;
	}
	public String getImage_2() {
		return image_2;
	}
	public void setImage_2(String image_2) {
		this.image_2 = image_2;
	}
	public String getImage_3() {
		return image_3;
	}
	public void setImage_3(String image_3) {
		this.image_3 = image_3;
	}
	public String getImage_4() {
		return image_4;
	}
	public void setImage_4(String image_4) {
		this.image_4 = image_4;
	}
	public String getImage_5() {
		return image_5;
	}
	public void setImage_5(String image_5) {
		this.image_5 = image_5;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
