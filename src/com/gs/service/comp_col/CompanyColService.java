package com.gs.service.comp_col;

import java.util.List;

import com.gs.bean.Company;
import com.gs.bean.CompanyCol;
import com.gs.service.BaseService;

/**
 * 装修公司收藏表t_company_col				
 * @author ID.LQF
 *
 */
public interface CompanyColService extends BaseService<String, CompanyCol>{

	/**
	 * 通过用户id和设计师id在设计师收藏表中查看是否已收藏
	 * @param cus_id
	 * @param des_id
	 * @return
	 */
	public String saveCheck(String cus_id, String com_id);
	/**
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Company> queryByPager(String id, int pageNo, int pageSize);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int count(String id);
	/**
	 * 通过用户表和设计师表查出收藏表id
	 * @param cus_id
	 * @param des_id
	 * @return
	 */
	public String queryById(String cus_id, String des_id);
	
}
