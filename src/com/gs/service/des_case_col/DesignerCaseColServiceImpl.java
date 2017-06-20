package com.gs.service.des_case_col;

import java.util.List;

import com.gs.bean.DesignerCase;
import com.gs.bean.DesignerCaseCol;
import com.gs.dao.des_case_col.DesignerCaseColDAO;
import com.gs.dao.des_case_col.DesignerCaseColDAOImpl;

/**
 * 设计师案例收藏表t_designer_case_col				
 * @author ID.LQF
 *
 */
public class DesignerCaseColServiceImpl implements DesignerCaseColService{

	private DesignerCaseColDAO designerCaseColDAO;
	
	public DesignerCaseColServiceImpl() {
		designerCaseColDAO = new DesignerCaseColDAOImpl();
	}
	@Override
	public void add(DesignerCaseCol t) {
		designerCaseColDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		designerCaseColDAO.deleteById(id);
	}

	@Override
	public void update(DesignerCaseCol t) {
		designerCaseColDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, DesignerCaseCol t) {
		designerCaseColDAO.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return designerCaseColDAO.count();
	}

	@Override
	public List<DesignerCaseCol> queryAll() {
		return designerCaseColDAO.queryAll();
	}

	@Override
	public List<DesignerCaseCol> queryByPager(int pageNo, int pageSize) {
		return designerCaseColDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<DesignerCaseCol> queryByName(String name) {
		return designerCaseColDAO.queryByName(name);
	}

	@Override
	public DesignerCaseCol queryByEmailPwd(String email, String pwd) {
		return designerCaseColDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public DesignerCaseCol queryById(String id) {
		return designerCaseColDAO.queryById(id);
	}
	@Override
	public String saveCheck(String cus_id, String desCase_id) {
		return designerCaseColDAO.saveCheck(cus_id, desCase_id);
	}
	@Override
	public List<DesignerCase> queryByPager(String id, int pageNo, int pageSize) {
		return designerCaseColDAO.queryByPager(id, pageNo, pageSize);
	}
	@Override
	public int count(String id) {
		return designerCaseColDAO.count(id);
	}
	@Override
	public String queryById(String cus_id, String des_id) {
		return designerCaseColDAO.queryById(cus_id, des_id);
	}

}
