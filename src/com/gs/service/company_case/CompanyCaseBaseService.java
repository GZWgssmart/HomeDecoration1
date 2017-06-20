package com.gs.service.company_case;

import java.util.List;

import com.gs.bean.CompanyCase;
import com.gs.service.BaseService;

/**
 * 装修公司案例表t_company_case				
 * @author ID.LQF
 *
 */
public interface CompanyCaseBaseService extends BaseService<String, CompanyCase>{

	/**
	 * 更改状态
	 */
	public void updateStatus(String check, String id);
	
	/**
	 * 查找最顶端的3个装修公司案例表
	 */
	public List<CompanyCase> queryCompanyTop3();
	
	/**
	 * 查找单个公司的3个装修公司案例表
	 */
	public List<CompanyCase> queryCompanyTop3(String id);
	
	/**
	 * 根据公司的id来获取他的所有案例
	 * @param id
	 * @return
	 */
	public List<CompanyCase> queryByCompId(String id);
	
	/**
	 * 根据公司的id来获取他的所有案例进而分页
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<CompanyCase> queryByPager(String id, int pageNo, int pageSize);
	
	/**
	 * 根据ID来查找
	 * @param id
	 * @return
	 */
	public int count(String id);
	
}
