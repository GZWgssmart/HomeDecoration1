package com.gs.service.cust;

import java.util.Date;

import com.gs.bean.Customer;
import com.gs.service.BaseService;
/**
 * 
 * @author 曾创
 *业主的service
 */
public interface CustomerService extends BaseService<String, Customer>{

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
