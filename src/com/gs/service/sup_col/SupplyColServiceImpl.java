package com.gs.service.sup_col;

import java.util.List;

import com.gs.bean.Supply;
import com.gs.bean.SupplyCol;
import com.gs.dao.sup_col.SupplyColDAO;
import com.gs.dao.sup_col.SupplyColDAOImpl;

/**
 * 建材商收藏表t_supply_col				
 * @author ID.LQF
 *
 */
public class SupplyColServiceImpl implements SupplyColService{

	private SupplyColDAO supplyColDAO;
	
	public SupplyColServiceImpl() {
		supplyColDAO = new SupplyColDAOImpl();
	}
	@Override
	public void add(SupplyCol t) {
		supplyColDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		supplyColDAO.deleteById(id);
	}

	@Override
	public void update(SupplyCol t) {
		supplyColDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, SupplyCol t) {
		supplyColDAO.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return supplyColDAO.count();
	}

	@Override
	public List<SupplyCol> queryAll() {
		return supplyColDAO.queryAll();
	}

	@Override
	public List<SupplyCol> queryByPager(int pageNo, int pageSize) {
		return supplyColDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<SupplyCol> queryByName(String name) {
		return supplyColDAO.queryByName(name);
	}

	@Override
	public SupplyCol queryByEmailPwd(String email, String pwd) {
		return supplyColDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public SupplyCol queryById(String id) {
		return supplyColDAO.queryById(id);
	}
	@Override
	public String saveCheck(String cus_id, String sup_id) {
		return supplyColDAO.saveCheck(cus_id, sup_id);
	}
	@Override
	public List<Supply> queryByPager(String id, int pageNo, int pageSize) {
		return supplyColDAO.queryByPager(id, pageNo, pageSize);
	}
	@Override
	public int count(String id) {
		return supplyColDAO.count(id);
	}
	@Override
	public String queryById(String cus_id, String des_id) {
		return supplyColDAO.queryById(cus_id, des_id);
	}

}
