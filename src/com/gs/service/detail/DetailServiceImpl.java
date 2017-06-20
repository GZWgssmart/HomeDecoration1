package com.gs.service.detail;

import java.util.List;

import com.gs.bean.OrderDetail;
import com.gs.bean.ProAndDetail;
import com.gs.dao.detail.DetailDAO;
import com.gs.dao.detail.DetailDAOImpl;

public class DetailServiceImpl implements DetailService{
	
	private DetailDAO detaImpl;
	
	public DetailServiceImpl() {
		detaImpl = new DetailDAOImpl();
	}

	@Override
	public void add(OrderDetail t) {
		detaImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		detaImpl.deleteById(id);
	}

	@Override
	public void update(OrderDetail t) {
		
	}

	@Override
	public void updatePwd(String pwd, OrderDetail t) {
		
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public List<OrderDetail> queryAll() {
		return null;
	}

	@Override
	public List<OrderDetail> queryByPager(int pageNo, int pageSize) {
		return null;
	}

	@Override
	public List<OrderDetail> queryByName(String name) {
		return null;
	}

	@Override
	public OrderDetail queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public OrderDetail queryById(String id) {
		return detaImpl.queryById(id);
	}

	@Override
	public List<ProAndDetail> queryProAndDeta(String userId, int pageNo, int pageSize) {
		return detaImpl.queryProAndDeta(userId, pageNo, pageSize);
	}

	@Override
	public int countById(String userId) {
		return detaImpl.countById(userId);
	}
	
}
