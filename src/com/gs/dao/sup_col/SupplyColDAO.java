package com.gs.dao.sup_col;

import java.util.List;

import com.gs.bean.Supply;
import com.gs.bean.SupplyCol;
import com.gs.dao.BaseDAO;

/**
 * 建材商收藏表t_supply_col				
 * @author ID.LQF
 *
 */
public interface SupplyColDAO extends BaseDAO<String, SupplyCol>{

	/**
	 * 通过用户id和设计师id在设计师收藏表中查看是否已收藏
	 * @param cus_id
	 * @param des_id
	 * @return
	 */
	public String saveCheck(String cus_id, String sup_id);
	/**
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Supply> queryByPager(String id, int pageNo, int pageSize);
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
