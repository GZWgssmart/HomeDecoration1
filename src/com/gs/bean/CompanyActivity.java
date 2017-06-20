package com.gs.bean;

import java.util.Date;

/**
 * 装修公司活动表t_company_activity				
 * @author ID.LQF
 *
 */

public class CompanyActivity {

	private String id;  //	unique_identifier	编号	主键，uuid
	private String company_id;  //	unique_identifier	装修公司编号	外键，关联到t_company表的id字段
	private String name;   //	varchar(100)	案例名称	not null
	private String image;  //	varchar(500)	活动图片路径	
	private String des;  //	varchar(500)	描述	not null
	private Date created_time;  //	datetime	创建时间	添加时的系统当前时间
	private String status;  //	char(1)	是否可用，Y表示可用，N表示不可用	default Y
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
