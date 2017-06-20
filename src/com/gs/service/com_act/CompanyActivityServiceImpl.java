package com.gs.service.com_act;

import java.util.List;

import com.gs.bean.CompanyActivity;
import com.gs.dao.com_act.CompanyActivityDAO;
import com.gs.dao.com_act.CompanyActivityDAOImpl;

/**
 * 装修公司活动表t_company_activity	
 * @author ID.LQF
 *
 */
public class CompanyActivityServiceImpl implements CompanyActivityService{

	private CompanyActivityDAO companyActivityDAO;
	
	public CompanyActivityServiceImpl() {
		companyActivityDAO = new CompanyActivityDAOImpl();
	}
	
	@Override
	public void add(CompanyActivity t) {
		companyActivityDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		companyActivityDAO.deleteById(id);
	}

	@Override
	public void update(CompanyActivity t) {
		companyActivityDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, CompanyActivity t) {
		companyActivityDAO.updatePwd(pwd, t);
	}

	@Override
	public List<CompanyActivity> queryAll() {
		return companyActivityDAO.queryAll();
	}

	@Override
	public List<CompanyActivity> queryByPager(int pageNo, int pageSize) {
		return companyActivityDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public CompanyActivity queryByEmailPwd(String email, String pwd) {
		return companyActivityDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public int count() {
		return companyActivityDAO.count();
	}

	@Override
	public List<CompanyActivity> queryByName(String name) {
		return companyActivityDAO.queryByName(name);
	}

	@Override
	public void updateStatus(String check, String id) {
		companyActivityDAO.updateStatus(check, id);
	}

	@Override
	public CompanyActivity queryById(String id) {
		return companyActivityDAO.queryById(id);
	}

	@Override
	public List<CompanyActivity> queryActiveTop3(String id) {
		return companyActivityDAO.queryActiveTop3(id);
	}

	@Override
	public List<CompanyActivity> queryByCompId(String id) {
		return companyActivityDAO.queryByCompId(id);
	}

	@Override
	public List<CompanyActivity> queryActiveTop3() {
		return companyActivityDAO.queryActiveTop3();
	}

	@Override
	public List<CompanyActivity> queryByPager(String id, int pageNo, int pageSize) {
		return companyActivityDAO.queryByPager(id, pageNo, pageSize);
	}

	@Override
	public int count(String id) {
		return companyActivityDAO.count(id);
	}

}
