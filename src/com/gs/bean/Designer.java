package com.gs.bean;

import java.util.Date;
/**
 * 设计师表t_designer				
 * @author ID.LQF
 *
 */
public class Designer {

		private String id;	//	id	unique_identifier	编号	主键，uuid
		private String email;	//	email	varchar(100)	邮箱	unique
		private String password;	//	password	varchar(200)	密码	md5加密存储
		private String name;	//	name	varchar(100)	名称	not null
		private String headicon;	//	headicon	varchar(500)	头像路径	
		private String phone;	//	phone	varchar(11)	手机号	not null
		private String address;	//	address	varchar(100)	地址	
		private String experience;	//	experience	varchar(50)	工作经验	1-3，3-5， 5-8， 8
		private String style;	//	style	varchar(100)	擅长的风格	
		private String des;	//	des	varchar(500)	描述	
		private Date created_time;	//	created_time	datetime	创建时间	添加时的系统当前时间
		private String checked;	//	checked	char(1)	是否审核,Y表示审核通过，N表示未审核	default N, Y或N
		private Date checked_time;	//	checked_time	datetime	审核的时间	审核时的系统当前时间
		private Date last_login_time;	//	last_login_time	datetime	最近一次登录时间	登录时的系统时间 
		private String status;	//	status	char(1)	是否可用，Y表示可用，N表示不可用	default Y
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
		public String getHeadicon() {
			return headicon;
		}
		public void setHeadicon(String headicon) {
			this.headicon = headicon;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getExperience() {
			return experience;
		}
		public void setExperience(String experience) {
			this.experience = experience;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
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
		public String getChecked() {
			return checked;
		}
		public void setChecked(String checked) {
			this.checked = checked;
		}
		public Date getChecked_time() {
			return checked_time;
		}
		public void setChecked_time(Date checked_time) {
			this.checked_time = checked_time;
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
