package com.gs.service.sup_act;

import java.util.List;

import com.gs.bean.SupplyActivity;
import com.gs.dao.sup_act.SupplyActivityDAO;
import com.gs.dao.sup_act.SupplyActivityDAOImpl;
/**
 * 
 * @author 曾创
 *建材商活动表service的实现类
 */
public class SupplyActivityServiceImpl implements SupplyActivityService{

	private SupplyActivityDAO actImpl;
	
	public SupplyActivityServiceImpl() {
		actImpl = new SupplyActivityDAOImpl();
	}
	
	@Override
	public void add(SupplyActivity t) {
		actImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		actImpl.deleteById(id);
	}

	@Override
	public void update(SupplyActivity t) {
		actImpl.update(t);
	}

	@Override
	public void updatePwd(String pwd, SupplyActivity t) {
		actImpl.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return actImpl.count();
	}

	@Override
	public List<SupplyActivity> queryAll() {
		return actImpl.queryAll();
	}

	@Override
	public List<SupplyActivity> queryByPager(int pageNo, int pageSize) {
		return actImpl.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<SupplyActivity> queryByName(String name) {
		return actImpl.queryByName(name);
	}

	@Override
	public SupplyActivity queryByEmailPwd(String email, String pwd) {
		return actImpl.queryByEmailPwd(email, pwd);
	}

	@Override
	public void updateStatus(String status, String id) {
		actImpl.updateStatus(status, id);
	}

	@Override
	public SupplyActivity queryById(String id) {
		return actImpl.queryById(id);
	}

	@Override
	public List<SupplyActivity> actTop3() {
		return actImpl.actTop3();
	}

	@Override
	public int countCheck() {
		return actImpl.countCheck();
	}

	@Override
	public List<SupplyActivity> queryByPagerCheck(int pageNo, int pageSize) {
		return actImpl.queryByPagerCheck(pageNo, pageSize);
	}

	@Override
	public int countCheck(String id) {
		return actImpl.countCheck(id);
	}

	@Override
	public List<SupplyActivity> PagerCheck(String id, int pageNo, int pageSize) {
		return actImpl.PagerCheck(id, pageNo, pageSize);
	}

	@Override
	public List<SupplyActivity> actTop3(String id) {
		return actImpl.actTop3(id);
	}

	@Override
	public int countChecked(String id) {
		return actImpl.countChecked(id);
	}

	@Override
	public List<SupplyActivity> PagerChecked(String id, int pageNo, int pageSize) {
		return actImpl.PagerChecked(id, pageNo, pageSize);
	}

}
