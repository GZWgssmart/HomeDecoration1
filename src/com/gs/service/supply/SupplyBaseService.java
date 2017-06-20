package com.gs.service.supply;

import java.util.Date;
import java.util.List;

import com.gs.bean.Supply;
import com.gs.service.BaseService;

/**
 * 建材商表t_supply				
 * @author ID.LQF
 *
 */
public interface SupplyBaseService extends BaseService<String, Supply>{

	/**
	 * 更改审核时间
	 */
	public void updateCheckedAndTime(String check, Date time, String id);
	
	/**
	 * 更改登入时间
	 */
	public void updateLoginTime(Date time, Supply t);
	
	/**
	 * 更改状态
	 */
	public void updateStatus(String check, String id);
	
	/**
	 * 查找数据库中是否存在这个邮箱
	 */
	public boolean queryByEmail(String email);
	/**
	 * 分页查找所有未审核的设计师
	 * @param pageNo
	 * @param PageSize
	 * @return
	 */
	public List<Supply> queryChecked(int pageNo, int pageSize);
	/**
	 * 通过id来修改logo
	 * @param logo
	 * @param id
	 */
	public void updateLogo(String logo, String id);
	/**
	 * top6精选设计师
	 * @return
	 */
	public List<Supply> desTop6();
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
	public List<Supply> queryByPagerCheck(int pageNo, int pageSize);
	/**
	 * 有约束的设计师计数(审核)
	 * @return
	 */
	public int countPass();

	/**
	 * 搜索查询建材商
	 */
	
	public List<Supply> searchCom(int pageNo, int pageSize, String com);
	
	/**
	 * 搜索查询建材商个数
	 */
	
	public int searchConInt(String com);
	
	/**
	 * 搜索查询建材商名字
	 */
	
	public List<Supply> Comname(String search);
}
