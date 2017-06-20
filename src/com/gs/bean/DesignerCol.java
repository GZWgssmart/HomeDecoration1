package com.gs.bean;

import java.util.Date;
/**
 * 
 * @author 曾创
 *设计师收藏表t_designer_col				
 */
public class DesignerCol {

	private String id;//	unique_identifier	编号	主键，uuid
	private String customer_id;//	unique_identifier	用户编号	外键，关联到t_customer表的id字段
	private String designer_id;//	unique_identifier	设计师编号	外键，关联到t_designer表的id字段
	private Date created_time;//	datetime	创建时间	添加时的系统当前时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getDesigner_id() {
		return designer_id;
	}
	public void setDesigner_id(String designer_id) {
		this.designer_id = designer_id;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

}
