package com.gs.bean;

import java.util.Date;
/**
 * 建材商表t_supply				
 * @author ID.LQF
 *
 */
public class Supply {
				
		private String id;		//1	id	unique_identifier	编号	主键，uuid
		private String email;		//2	email	varchar(100)	邮箱	unique
		private String password;		//3	password	varchar(200)	密码	md5加密存储
		private String name;		//4	name	varchar(100)	名称	not null
		private String principal;		//5	principal	varchar(10)	负责人	not null
		private String logo;		//6	logo	varchar(500)	公司logo图片地址	default logo.png
		private String address;		//	7	address	varchar(100)	地址	not null
		private String phone;		//	8	phone	varchar(11)	手机号	not null
		private String tel;		//	9	tel	varchar(11)	固定电话	
		private Date open_date;		//	10	open_date	datetime	成立日期	not null
		private float longitude;		//	11	longitude	float	经度	
		private float latitude;		//	12	latitude	float	纬度	
		private String des;		//	13	des	varchar(500)	描述	
		private Date created_time;		//	14	created_time	datetime	创建时间	添加时的系统当前时间
		private String checked;		//	15	checked	char(1)	是否审核,Y表示审核通过，N表示未审核	default N, Y或N
		private Date checked_time;		//	16	checked_time	datetime	审核的时间	审核时的系统当前时间
		private Date last_login_time;		//	17	last_login_time	datetime	最近一次登录时间	登录时的系统时间 
		private String status;		//	19	status	char(1)	是否可用，Y表示可用，N表示不可用	default Y
		
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
		public String getPrincipal() {
			return principal;
		}
		public void setPrincipal(String principal) {
			this.principal = principal;
		}
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		
		public Date getOpen_date() {
			return open_date;
		}
		public void setOpen_date(Date open_date) {
			this.open_date = open_date;
		}
		public float getLongitude() {
			return longitude;
		}
		public void setLongitude(float longitude) {
			this.longitude = longitude;
		}
		public float getLatitude() {
			return latitude;
		}
		public void setLatitude(float latitude) {
			this.latitude = latitude;
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
