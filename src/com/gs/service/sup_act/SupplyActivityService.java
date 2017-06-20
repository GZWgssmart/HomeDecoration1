package com.gs.service.sup_act;

import java.util.List;

import com.gs.bean.SupplyActivity;
import com.gs.dao.BaseDAO;
/**
 * 
 * @author 曾创
 *建材商活动表service
 */
public interface SupplyActivityService extends BaseDAO<String, SupplyActivity>{

	/**
	 * 更改活动状态（默认为可用）
	 */
	public void updateStatus(String status, String id);
	/**
	 * 活动top3
	 * @return
	 */
	public List<SupplyActivity> actTop3();
	/**
	 * 活动top3
	 * @return
	 */
	public List<SupplyActivity> actTop3(String id);
	/**
	 * 有约束的活动计数
	 * @return
	 */
	public int countCheck();
	/**
	 * 有约束的活动分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<SupplyActivity> queryByPagerCheck(int pageNo, int pageSize);
	/**
	 * 有约束的活动计数
	 * @return
	 */
	public int countChecked(String id);
	/**
	 * 有约束的建材商活动分页
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<SupplyActivity> PagerChecked(String id, int pageNo, int pageSize);
	/**
	 * 有约束的活动计数
	 * @return
	 */
	public int countCheck(String id);
	/**
	 * 有约束的建材商活动分页
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<SupplyActivity> PagerCheck(String id, int pageNo, int pageSize);
}
