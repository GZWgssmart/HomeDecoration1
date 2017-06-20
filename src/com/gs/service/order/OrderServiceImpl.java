package com.gs.service.order;

import java.util.List;

import com.gs.bean.Order;
import com.gs.dao.order.OrderDAO;
import com.gs.dao.order.OrderDAOImpl;

public class OrderServiceImpl implements OrderService{
	
	private OrderDAO orderImpl;
	
	public OrderServiceImpl() {
		orderImpl = new OrderDAOImpl();
	}

	@Override
	public void add(Order t) {
		orderImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		orderImpl.deleteById(id);
	}

	@Override
	public void update(Order t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePwd(String pwd, Order t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> queryByPager(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> queryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order queryByEmailPwd(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order queryById(String id) {
		return orderImpl.queryById(id);
	}

	@Override
	public List<Order> queryByPage(int pageNo, int pageSize, String userId) {
		return orderImpl.queryByPage(pageNo, pageSize, userId);
	}

	@Override
	public int countById(String userId) {
		return orderImpl.countById(userId);
	}

	@Override
	public void updatePayed(String id) {
		orderImpl.updatePayed(id);
	}

	@Override
	public Order queryByUserIdAndPayed(String userId) {
		return orderImpl.queryByUserIdAndPayed(userId);
	}

}
