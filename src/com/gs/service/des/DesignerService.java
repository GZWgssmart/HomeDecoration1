package com.gs.service.des;

import java.util.Date;
import java.util.List;

import com.gs.bean.Designer;
import com.gs.service.BaseService;
/**
 * 
 * @author 曾创
 *设计师service
 */
public interface DesignerService extends BaseService<String, Designer>{

	/**
	 * 修改审核状态和通过时间
	 */
	public void updateCheckedAndTime(String check, Date time, String id);
	/**
	 * 更改登录时间(由系统完成)
	 */
	public void updateLoginTime(Date time, Designer t);
	/**
	 * 更改账户状态（默认为可用）
	 */
	public void updateStatus(String status, String id);
	/**
	 * 分页查找所有未审核的设计师
	 * @param pageNo
	 * @param PageSize
	 * @return
	 */
	public List<Designer> queryChecked(int pageNo, int pageSize);
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
	/**
	 * top5精选设计师
	 * @return
	 */
	public List<Designer> desTop5();
	/**
	 * 有约束的设计师计数
	 * @return
	 */
	public int countCheck();
	/**
	 * 有约束的设计师分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Designer> queryByPagerCheck(int pageNo, int pageSize);
	/**
	 * 有约束的设计师计数(审核)
	 * @return
	 */
	public int countPass();
	

	/**
	 * 搜索查询设计师
	 */
	
	public List<Designer> searchCom(int pageNo, int pageSize, String com);
	
	/**
	 * 搜索查询设计师个数
	 */
	
	public int searchConInt(String com);
	
	/**
	 * 搜索查询设计师名字
	 */
	
	public List<Designer> Comname(String search);
}
