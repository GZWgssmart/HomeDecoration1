package com.gs.service.adm;

import java.util.Date;

import com.gs.bean.Admin;
import com.gs.service.BaseService;

/**
 * 
 * @author 曾创
 *管理员service
 */
public interface AdminService extends BaseService<String, Admin>{
	
	/**
	 * 更改登录时间(由系统完成)
	 */
	public void updateLoginTime(Date time, Admin t);
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
}
