package com.gs.service.cust;

import java.util.Date;
import java.util.List;

import com.gs.bean.Customer;
import com.gs.dao.cust.CustomerDAO;
import com.gs.dao.cust.CustomerDAOImpl;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDAO cusImpl;
	
	public CustomerServiceImpl() {
		cusImpl = new CustomerDAOImpl();
	}
	
	@Override
	public void add(Customer t) {
		cusImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		cusImpl.deleteById(id);
	}

	@Override
	public void update(Customer t) {
		cusImpl.update(t);
	}

	@Override
	public int count() {
		return cusImpl.count();
	}

	@Override
	public List<Customer> queryAll() {
		return cusImpl.queryAll();
	}

	@Override
	public List<Customer> queryByPager(int pageNo, int pageSize) {
		return cusImpl.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<Customer> queryByName(String name) {
		return cusImpl.queryByName(name);
	}

	@Override
	public void updateLoginTime(Date time, Customer t) {
		cusImpl.updateLoginTime(time, t);
	}

	@Override
	public void updateStatus(String status, String t) {
		cusImpl.updateStatus(status, t);
	}

	@Override
	public Customer queryByEmailPwd(String email, String pwd) {
		return cusImpl.queryByEmailPwd(email, pwd);
	}

	@Override
	public void updatePwd(String pwd, Customer t) {
		cusImpl.updatePwd(pwd, t);
	}

	@Override
	public Customer queryById(String id) {
		return cusImpl.queryById(id);
	}

	@Override
	public boolean queryEmail(String email) {
		return cusImpl.queryEmail(email);
	}

	@Override
	public void updateHeadIcon(String headIcon, String id) {
		cusImpl.updateHeadIcon(headIcon, id);
	}

}
