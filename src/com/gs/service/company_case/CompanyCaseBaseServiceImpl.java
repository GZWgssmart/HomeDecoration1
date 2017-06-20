package com.gs.service.company_case;

import java.util.List;

import com.gs.bean.CompanyCase;
import com.gs.dao.comp_case.CompanyCaseBaseDAO;
import com.gs.dao.comp_case.CompanyCaseBaseDAOImpl;

/**
 * 装修公司案例表t_company_case				
 * @author ID.LQF
 *
 */
public class CompanyCaseBaseServiceImpl implements CompanyCaseBaseService{

	private CompanyCaseBaseDAO companyCaseBaseDAO;
	
	public CompanyCaseBaseServiceImpl() {
		companyCaseBaseDAO = new CompanyCaseBaseDAOImpl();
	}
	
	@Override
	public void add(CompanyCase t) {
		companyCaseBaseDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		companyCaseBaseDAO.deleteById(id);
	}

	@Override
	public void update(CompanyCase t) {
		companyCaseBaseDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, CompanyCase t) {
		companyCaseBaseDAO.updatePwd(pwd, t);
	}

	@Override
	public List<CompanyCase> queryAll() {
		return companyCaseBaseDAO.queryAll();
	}

	@Override
	public List<CompanyCase> queryByPager(int pageNo, int pageSize) {
		return companyCaseBaseDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public CompanyCase queryByEmailPwd(String email, String pwd) {
		return companyCaseBaseDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public int count() {
		return companyCaseBaseDAO.count();
	}

	@Override
	public List<CompanyCase> queryByName(String name) {
		return companyCaseBaseDAO.queryByName(name);
	}

	@Override
	public void updateStatus(String check, String id) {
		companyCaseBaseDAO.updateStatus(check, id);
	}

	@Override
	public CompanyCase queryById(String id) {
		return companyCaseBaseDAO.queryById(id);
	}

	@Override
	public List<CompanyCase> queryCompanyTop3(String id) {
		return companyCaseBaseDAO.queryCompanyTop3(id);
	}

	@Override
	public List<CompanyCase> queryByCompId(String id) {
		return companyCaseBaseDAO.queryByCompId(id);
	}

	@Override
	public List<CompanyCase> queryCompanyTop3() {
		return companyCaseBaseDAO.queryCompanyTop3();
	}

	@Override
	public List<CompanyCase> queryByPager(String id, int pageNo, int pageSize) {
		return companyCaseBaseDAO.queryByPager(id, pageNo, pageSize);
	}

	@Override
	public int count(String id) {
		return companyCaseBaseDAO.count(id);
	}

}
