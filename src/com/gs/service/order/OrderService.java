package com.gs.service.order;

import java.util.List;

import com.gs.bean.Order;
import com.gs.service.BaseService;

public interface OrderService extends BaseService<String, Order>{

	/**
	 * 查出商品和购物车表的组合数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Order> queryByPage(int pageNo, int pageSize, String userId);
	/**
	 * 通过用户id来查找购物车清单数量
	 * @param userId
	 * @return
	 */
	public int countById(String userId);
	/**
	 * 把支付状态改为已支付
	 * @param id
	 */
	public void updatePayed(String id);
	/**
	 * 通过userid和未支付查出订单表id
	 * @param userId
	 * @return
	 */
	public Order queryByUserIdAndPayed(String userId);
	
}
