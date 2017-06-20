package com.gs.bean;

import java.util.Date;
/**
 * 
 * @author 曾创
 *建材表t_product				
 */
public class Product {

	private String id;//	unique_identifier	编号	主键，uuid
	private String supply_id;//	unique_identifier	建材商编号	外键，关联到t_supply表的id字段
	private String  name;//	varchar(100)	商品名称	not null
	private float price;//	float	商品价格	not null
	private float sale_price;//	float	商品折扣后的价格	not null
	private String image;//	varchar(500)	商品图片的地址	not null
	private String des;//	varchar(500)	描述	
	private Date created_time;//	datetime	创建时间	添加时的系统当前时间
	private String status;//	char(1)	是否可用，Y表示上架，N表示下架	default Y
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSupply_id() {
		return supply_id;
	}
	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getSale_price() {
		return sale_price;
	}
	public void setSale_price(float sale_price) {
		this.sale_price = sale_price;
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
