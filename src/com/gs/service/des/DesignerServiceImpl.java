package com.gs.service.des;

import java.util.Date;
import java.util.List;

import com.gs.bean.Designer;
import com.gs.dao.des.DesignerDAO;
import com.gs.dao.des.DesignerDAOImpl;

public class DesignerServiceImpl implements DesignerService {
	
	private DesignerDAO desImpl;
	
	public DesignerServiceImpl() {
		desImpl = new DesignerDAOImpl();
	}
	
	@Override
	public void updateCheckedAndTime(String check, Date time, String id) {
		desImpl.updateCheckedAndTime(check, time, id);
	}

	@Override
	public void updateLoginTime(Date time, Designer t) {
		desImpl.updateLoginTime(time, t);
	}

	@Override
	public void updateStatus(String status, String t) {
		desImpl.updateStatus(status, t);
	}

	@Override
	public void add(Designer t) {
		desImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		desImpl.deleteById(id);
	}

	@Override
	public void update(Designer t) {
		desImpl.update(t);
	}

	@Override
	public int count() {
		return desImpl.count();
	}

	@Override
	public List<Designer> queryAll() {
		return desImpl.queryAll();
	}

	@Override
	public List<Designer> queryByPager(int pageNo, int pageSize) {
		return desImpl.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<Designer> queryByName(String name) {
		return desImpl.queryByName(name);
	}

	@Override
	public Designer queryByEmailPwd(String email, String pwd) {
		return desImpl.queryByEmailPwd(email, pwd);
	}

	@Override
	public void updatePwd(String pwd, Designer t) {
		desImpl.updatePwd(pwd, t);
	}

	@Override
	public Designer queryById(String id) {
		return desImpl.queryById(id);
	}

	@Override
	public List<Designer> queryChecked(int pageNo, int pageSize) {
		return desImpl.queryChecked(pageNo, pageSize);
	}

	@Override
	public boolean queryEmail(String email) {
		return desImpl.queryEmail(email);
	}

	@Override
	public void updateHeadIcon(String headIcon, String id) {
		desImpl.updateHeadIcon(headIcon, id);
	}

	@Override
	public List<Designer> desTop5() {
		return desImpl.desTop5();
	}

	@Override
	public int countCheck() {
		return desImpl.countCheck();
	}

	@Override
	public List<Designer> queryByPagerCheck(int pageNo, int pageSize) {
		return desImpl.queryByPagerCheck(pageNo, pageSize);
	}

	@Override
	public int countPass() {
		return desImpl.countPass();
	}

	@Override
	public List<Designer> searchCom(int pageNo, int pageSize, String com) {
		// TODO Auto-generated method stub
		return desImpl.searchCom(pageNo, pageSize, com);
	}

	@Override
	public int searchConInt(String com) {
		// TODO Auto-generated method stub
		return desImpl.searchConInt(com);
	}

	@Override
	public List<Designer> Comname(String search) {
		// TODO Auto-generated method stub
		return desImpl.Comname(search);
	}

}
