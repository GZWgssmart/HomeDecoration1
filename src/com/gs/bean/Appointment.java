package com.gs.bean;

import java.util.Date;
/**
 * 
 * @author 曾创
 *用户预约表t_appointment				
 */
public class Appointment {

	private String id;//	unique_identifier	编号	主键，uuid
	private String user_id;//	unique_identifier	用户编号	
	private String company_id;//	unique_identifier	公司编号	外键，关联到t_company表的id字段
	private String name;//	varchar(50)	称呼	not null
	private String company_name;
	private String phone;//	varchar(11)	手机号	not null
	private String plot_name;//	varchar(100)	小区名称	not null
	private float area;//	float	建筑面积	not null
	private String way;//	varchar(10)	装修方式	check in whole(全包）,half(半包)
	private String budget;//	varchar(20)	装修预算	可选：5-8万，8-10万，10-15万，15万以上
	private Date created_time;//	datetime	创建时间	添加时的系统当前时间
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlot_name() {
		return plot_name;
	}
	public void setPlot_name(String plot_name) {
		this.plot_name = plot_name;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

}
