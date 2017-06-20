package com.gs.dao.detail;

import java.util.List;

import com.gs.bean.OrderDetail;
import com.gs.bean.ProAndDetail;
import com.gs.dao.BaseDAO;
/**
 * 
 * @author 7025
 *订单详情
 */
public interface DetailDAO extends BaseDAO<String, OrderDetail>{

	/**
	 * 通过用户id来查找购物车清单数量
	 * @param userId
	 * @return
	 */
	public int countById(String userId);
	/**
	 * 分页显示订单详情
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ProAndDetail> queryProAndDeta(String userId, int pageNo, int pageSize);
}
