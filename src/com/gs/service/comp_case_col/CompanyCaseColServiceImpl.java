package com.gs.service.comp_case_col;

import java.util.List;

import com.gs.bean.CompanyCase;
import com.gs.bean.CompanyCaseCol;
import com.gs.dao.comp_case_col.CompanyCaseColDAO;
import com.gs.dao.comp_case_col.CompanyCaseColDAOImpl;

/**
 * 装修公司案例收藏表t_company_case_col				
 * @author ID.LQF
 *
 */
public class CompanyCaseColServiceImpl implements CompanyCaseColService{

	private CompanyCaseColDAO companyCaseColDAO;
	
	public CompanyCaseColServiceImpl() {
		companyCaseColDAO = new CompanyCaseColDAOImpl();
	}
	@Override
	public void add(CompanyCaseCol t) {
		companyCaseColDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		companyCaseColDAO.deleteById(id);
	}

	@Override
	public void update(CompanyCaseCol t) {
		companyCaseColDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, CompanyCaseCol t) {
		companyCaseColDAO.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return companyCaseColDAO.count();
	}

	@Override
	public List<CompanyCaseCol> queryAll() {
		return companyCaseColDAO.queryAll();
	}

	@Override
	public List<CompanyCaseCol> queryByPager(int pageNo, int pageSize) {
		return companyCaseColDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<CompanyCaseCol> queryByName(String name) {
		return companyCaseColDAO.queryByName(name);
	}

	@Override
	public CompanyCaseCol queryByEmailPwd(String email, String pwd) {
		return companyCaseColDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public CompanyCaseCol queryById(String id) {
		return companyCaseColDAO.queryById(id);
	}
	@Override
	public String saveCheck(String cus_id, String comCase_id) {
		return companyCaseColDAO.saveCheck(cus_id, comCase_id);
	}
	@Override
	public List<CompanyCase> queryByPager(String id, int pageNo, int pageSize) {
		return companyCaseColDAO.queryByPager(id, pageNo, pageSize);
	}
	@Override
	public int count(String id) {
		return companyCaseColDAO.count(id);
	}
	@Override
	public String queryById(String cus_id, String des_id) {
		return companyCaseColDAO.queryById(cus_id, des_id);
	}

}
