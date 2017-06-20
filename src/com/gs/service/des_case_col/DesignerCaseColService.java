package com.gs.service.des_case_col;

import java.util.List;

import com.gs.bean.DesignerCase;
import com.gs.bean.DesignerCaseCol;
import com.gs.service.BaseService;

/**
 * 设计师案例收藏表t_designer_case_col				
 * @author ID.LQF
 *
 */
public interface DesignerCaseColService extends BaseService<String, DesignerCaseCol>{

	/**
	 * 通过用户id和设计师id在设计师收藏表中查看是否已收藏
	 * @param cus_id
	 * @param des_id
	 * @return
	 */
	public String saveCheck(String cus_id, String desCase_id);
	/**
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<DesignerCase> queryByPager(String id, int pageNo, int pageSize);
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
