package com.gs.bean;

import java.util.Date;
/**
 * 
 * @author 曾创
 *用户表t_customer				
 */
public class Customer {

	private String id;//	unique_identifier	编号	主键，uuid
	private String email;//	varchar(100)	邮箱	unique
	private String password;//	varchar(200)	密码	md5加密存储
	private String name;//	varchar(100)	名称	not null
	private String phone;//	varchar(11)	手机号	not null
	private String plot_name;//	varchar(100)	小区名称
	private String headIcon;
	private String address;//	varchar(100)	地址	
	private Date created_time;//	datetime	创建时间	添加时的系统当前时间
	private Date last_login_time;//	datetime	最近一次登录时间	登录时的系统时间 
	private String status;//	char(1)	是否可用，Y表示可用，N表示不可用	default Y
	public String getHeadIcon() {
		return headIcon;
	}
	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
