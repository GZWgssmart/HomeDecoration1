package com.gs.service.supply;

import java.util.Date;
import java.util.List;

import com.gs.bean.Supply;
import com.gs.dao.supply.SupplyBaseDAO;
import com.gs.dao.supply.SupplyBaseDAOImpl;

/**
 * 建材商表t_supply				
 * @author ID.LQF
 *
 */
public class SupplyBaseServiceImpl implements SupplyBaseService{

	private SupplyBaseDAO supplyBaseDAO;
	
	public SupplyBaseServiceImpl(){
		supplyBaseDAO = new SupplyBaseDAOImpl();
	}
	
	@Override
	public void add(Supply t) {
		supplyBaseDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		supplyBaseDAO.deleteById(id);
	}

	@Override
	public void update(Supply t) {
		supplyBaseDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, Supply t) {
		supplyBaseDAO.updatePwd(pwd, t);
	}

	@Override
	public List<Supply> queryAll() {
		return supplyBaseDAO.queryAll();
	}

	@Override
	public List<Supply> queryByPager(int pageNo, int pageSize) {
		return supplyBaseDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public Supply queryByEmailPwd(String email, String pwd) {
		return supplyBaseDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public int count() {
		return supplyBaseDAO.count();
	}

	@Override
	public List<Supply> queryByName(String name) {
		return supplyBaseDAO.queryByName(name);
	}

	@Override
	public void updateCheckedAndTime(String check, Date time, String id) {
		supplyBaseDAO.updateCheckedAndTime(check, time, id);
	}

	@Override
	public void updateLoginTime(Date time, Supply t) {
		supplyBaseDAO.updateLoginTime(time, t);
	}

	@Override
	public void updateStatus(String check, String t) {
		supplyBaseDAO.updateStatus(check, t);
	}

	@Override
	public Supply queryById(String id) {
		return supplyBaseDAO.queryById(id);
	}

	@Override
	public boolean queryByEmail(String email) {
		return supplyBaseDAO.queryByEmail(email);
	}

	@Override
	public List<Supply> queryChecked(int pageNo, int pageSize) {
		return supplyBaseDAO.queryChecked(pageNo, pageSize);
	}

	@Override
	public void updateLogo(String logo, String id) {
		supplyBaseDAO.updateLogo(logo, id);
	}

	@Override
	public List<Supply> desTop6() {
		return supplyBaseDAO.desTop6();
	}

	@Override
	public int countCheck() {
		return supplyBaseDAO.countCheck();
	}

	@Override
	public List<Supply> queryByPagerCheck(int pageNo, int pageSize) {
		return supplyBaseDAO.queryByPagerCheck(pageNo, pageSize);
	}

	@Override
	public int countPass() {
		return supplyBaseDAO.countPass();
	}

	@Override
	public List<Supply> searchCom(int pageNo, int pageSize, String com) {
		// TODO Auto-generated method stub
		return supplyBaseDAO.searchCom(pageNo, pageSize, com);
	}

	@Override
	public int searchConInt(String com) {
		// TODO Auto-generated method stub
		return supplyBaseDAO.searchConInt(com);
	}

	@Override
	public List<Supply> Comname(String search) {
		// TODO Auto-generated method stub
		return supplyBaseDAO.Comname(search);
	}

}
