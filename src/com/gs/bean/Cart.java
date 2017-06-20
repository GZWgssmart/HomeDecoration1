package com.gs.bean;

import java.util.Date;
/**
 * 
 * @author 曾创
 *购物车表
 */
public class Cart {

	private String id;
	private String user_id;
	private String product_id;
	private int total; // 商品数量
	private float cost; // 商品总价
	private Date creat_time;
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
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreated_time(Date creat_time) {
		this.creat_time = creat_time;
	}
}
