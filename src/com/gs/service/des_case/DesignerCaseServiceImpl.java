package com.gs.service.des_case;

import java.util.List;

import com.gs.bean.DesignerCase;
import com.gs.dao.des_case.DesignerCaseBaseDAO;
import com.gs.dao.des_case.DesignerCaseBaseDAOImpl;

/**
 * 设计师案例表t_designer_case				
 * @author ID.LQF
 *
 */
public class DesignerCaseServiceImpl implements DesignerCaseService{

	private DesignerCaseBaseDAO designerDAO;
	
	public DesignerCaseServiceImpl() {
		designerDAO = new DesignerCaseBaseDAOImpl();
	}
	
	@Override
	public void add(DesignerCase t) {
		designerDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		designerDAO.deleteById(id);
	}

	@Override
	public void update(DesignerCase t) {
		designerDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, DesignerCase t) {
		designerDAO.updatePwd(pwd, t);
	}

	@Override
	public List<DesignerCase> queryAll() {
		return designerDAO.queryAll();
	}

	@Override
	public List<DesignerCase> queryByPager(int pageNo, int pageSize) {
		return designerDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public DesignerCase queryByEmailPwd(String email, String pwd) {
		return designerDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public int count() {
		return designerDAO.count();
	}

	@Override
	public List<DesignerCase> queryByName(String name) {
		return designerDAO.queryByName(name);
	}

	@Override
	public void updateStatus(String check, String id) {
		designerDAO.updateStatus(check, id);
	}

	@Override
	public DesignerCase queryById(String id) {
		return designerDAO.queryById(id);
	}

	@Override
	public List<DesignerCase> desCaseTop3() {
		return designerDAO.desCaseTop3();
	}

	@Override
	public int countCheck() {
		return designerDAO.countCheck();
	}

	@Override
	public List<DesignerCase> queryByPagerCheck(int pageNo, int pageSize) {
		return designerDAO.queryByPagerCheck(pageNo, pageSize);
	}

	@Override
	public int countCheck(String id) {
		return designerDAO.countCheck(id);
	}

	@Override
	public List<DesignerCase> PagerCheck(String id, int pageNo, int pageSize) {
		return designerDAO.PagerCheck(id, pageNo, pageSize);
	}

	@Override
	public List<DesignerCase> desCaseTop3(String id) {
		return designerDAO.desCaseTop3(id);
	}

	@Override
	public int countChecked(String id) {
		return designerDAO.countChecked(id);
	}

	@Override
	public List<DesignerCase> PagerChecked(String id, int pageNo, int pageSize) {
		return designerDAO.PagerChecked(id, pageNo, pageSize);
	}

}
