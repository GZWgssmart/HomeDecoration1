package com.gs.dao.des_col;

import java.util.List;

import com.gs.bean.Designer;
import com.gs.bean.DesignerCol;
import com.gs.dao.BaseDAO;

/**
 * 设计师收藏表t_designer_col				
 * @author ID.LQF
 *
 */
public interface DesignerColDAO extends BaseDAO<String, DesignerCol>{

	/**
	 * 通过用户id和设计师id在设计师收藏表中查看是否已收藏
	 * @param cus_id
	 * @param des_id
	 * @return
	 */
	public String saveCheck(String cus_id, String des_id);
	/**
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Designer> queryByPager(String id, int pageNo, int pageSize);
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
