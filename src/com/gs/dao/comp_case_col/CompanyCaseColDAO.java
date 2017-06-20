package com.gs.dao.comp_case_col;

import java.util.List;

import com.gs.bean.CompanyCase;
import com.gs.bean.CompanyCaseCol;
import com.gs.dao.BaseDAO;

/**
 * 装修公司案例收藏表t_company_case_col				
 * @author ID.LQF
 *
 */
public interface CompanyCaseColDAO extends BaseDAO<String, CompanyCaseCol>{

	/**
	 * 通过用户id和设计师id在设计师收藏表中查看是否已收藏
	 * @param cus_id
	 * @param des_id
	 * @return
	 */
	public String saveCheck(String cus_id, String comCase_id);
	/**
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<CompanyCase> queryByPager(String id, int pageNo, int pageSize);
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
