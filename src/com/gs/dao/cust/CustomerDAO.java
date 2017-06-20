package com.gs.dao.cust;

import java.util.Date;

import com.gs.bean.Customer;
import com.gs.dao.BaseDAO;

/**
 * 
 * @author 曾创
 *用户表DAO
 */
public interface CustomerDAO extends BaseDAO<String, Customer>{
	
	/**
	 * 更改登录时间(由系统完成)
	 */
	public void updateLoginTime(Date time, Customer t);
	/**
	 * 更改账户状态（默认为可用）
	 */
	public void updateStatus(String status, String id);
	/**
	 * 查找数据库中是否有此email
	 * @param email
	 * @return
	 */
	public boolean queryEmail(String email);
	/**
	 * 更改头像
	 * @param headIcon
	 * @param id
	 */
	public void updateHeadIcon(String headIcon, String id);
}
