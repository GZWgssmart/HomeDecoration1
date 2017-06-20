package com.gs.service.des_col;

import java.util.List;

import com.gs.bean.Designer;
import com.gs.bean.DesignerCol;
import com.gs.dao.des_col.DesignerColDAO;
import com.gs.dao.des_col.DesignerColDAOImpl;

public class DesignerColServiceImpl implements DesignerColService{

	private DesignerColDAO designerColDAO;
	
	public DesignerColServiceImpl() {
		designerColDAO = new DesignerColDAOImpl();
	}
	@Override
	public void add(DesignerCol t) {
		designerColDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		designerColDAO.deleteById(id);
	}

	@Override
	public void update(DesignerCol t) {
		designerColDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, DesignerCol t) {
		designerColDAO.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return designerColDAO.count();
	}

	@Override
	public List<DesignerCol> queryAll() {
		return designerColDAO.queryAll();
	}

	@Override
	public List<DesignerCol> queryByPager(int pageNo, int pageSize) {
		return designerColDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<DesignerCol> queryByName(String name) {
		return designerColDAO.queryByName(name);
	}

	@Override
	public DesignerCol queryByEmailPwd(String email, String pwd) {
		return designerColDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public DesignerCol queryById(String id) {
		return designerColDAO.queryById(id);
	}
	@Override
	public String saveCheck(String cus_id, String des_id) {
		return designerColDAO.saveCheck(cus_id, des_id);
	}
	@Override
	public List<Designer> queryByPager(String id, int pageNo, int pageSize) {
		return designerColDAO.queryByPager(id, pageNo, pageSize);
	}
	@Override
	public int count(String id) {
		return designerColDAO.count(id);
	}
	@Override
	public String queryById(String cus_id, String des_id) {
		return designerColDAO.queryById(cus_id, des_id);
	}

}
