package com.gs.service.adm;

import java.util.Date;
import java.util.List;

import com.gs.bean.Admin;
import com.gs.dao.adm.AdminDAO;
import com.gs.dao.adm.AdminDAOImpl;
/**
 * 
 * @author 曾创
 *管理员service实现类
 */
public class AdminServiceImpl implements AdminService{
	
	private AdminDAO admImpl;
	
	public AdminServiceImpl() {
		admImpl = new AdminDAOImpl();
	}

	@Override
	public void add(Admin t) {
		admImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		admImpl.deleteById(id);
	}

	@Override
	public void update(Admin t) {
		admImpl.update(t);
	}

	@Override
	public void updatePwd(String pwd, Admin t) {
		admImpl.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return admImpl.count();
	}

	@Override
	public List<Admin> queryAll() {
		return admImpl.queryAll();
	}

	@Override
	public List<Admin> queryByPager(int pageNo, int pageSize) {
		return admImpl.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<Admin> queryByName(String name) {
		return admImpl.queryByName(name);
	}

	@Override
	public Admin queryByEmailPwd(String email, String pwd) {
		return admImpl.queryByEmailPwd(email, pwd);
	}

	@Override
	public void updateLoginTime(Date time, Admin t) {
		admImpl.updateLoginTime(time, t);
	}

	@Override
	public void updateStatus(String status, String t) {
		admImpl.updateStatus(status, t);
	}

	@Override
	public Admin queryById(String id) {
		return admImpl.queryById(id);
	}

	@Override
	public boolean queryEmail(String email) {
		return admImpl.queryEmail(email);
	}

}
