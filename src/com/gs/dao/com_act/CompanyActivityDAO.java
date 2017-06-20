package com.gs.dao.com_act;

import java.util.List;

import com.gs.bean.CompanyActivity;
import com.gs.dao.BaseDAO;

/**
 * 装修公司活动表t_company_activity				
 * @author ID.LQF
 *
 */
public interface CompanyActivityDAO extends BaseDAO<String, CompanyActivity>{

	/**
	 * 更改状态
	 */
	public void updateStatus(String check, String id);
	
	/**
	 * 查找最热门的3个活动
	 * @return
	 */
	public List<CompanyActivity> queryActiveTop3();
	
	/**
	 * 查找单个公司最热门的3个活动
	 * @return
	 */
	public List<CompanyActivity> queryActiveTop3(String id);
	
	/**
	 * 根据公司的id来获取他的所有活动
	 * @param id
	 * @return
	 */
	public List<CompanyActivity> queryByCompId(String id);
	
	/**
	 * 根据公司的id来获取他的所有活动案例进而分页
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<CompanyActivity> queryByPager(String id, int pageNo, int pageSize);
	
	/**
	 * 根据id查找活动页数
	 * @param id
	 * @return
	 */
	public int count(String id);
	
}
