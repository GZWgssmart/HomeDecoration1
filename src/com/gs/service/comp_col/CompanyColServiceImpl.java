package com.gs.service.comp_col;

import java.util.List;

import com.gs.bean.Company;
import com.gs.bean.CompanyCol;
import com.gs.dao.comp_col.CompanyColDAO;
import com.gs.dao.comp_col.CompanyColDAOImpl;

public class CompanyColServiceImpl implements CompanyColService{

	private CompanyColDAO companyColDAO;
	
	public CompanyColServiceImpl() {
		companyColDAO = new CompanyColDAOImpl();
	}
	@Override
	public void add(CompanyCol t) {
		companyColDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		companyColDAO.deleteById(id);
	}

	@Override
	public void update(CompanyCol t) {
		companyColDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, CompanyCol t) {
		companyColDAO.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return companyColDAO.count();
	}

	@Override
	public List<CompanyCol> queryAll() {
		return companyColDAO.queryAll();
	}

	@Override
	public List<CompanyCol> queryByPager(int pageNo, int pageSize) {
		return companyColDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<CompanyCol> queryByName(String name) {
		return companyColDAO.queryByName(name);
	}

	@Override
	public CompanyCol queryByEmailPwd(String email, String pwd) {
		return companyColDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public CompanyCol queryById(String id) {
		return companyColDAO.queryById(id);
	}
	@Override
	public String saveCheck(String cus_id, String com_id) {
		return companyColDAO.saveCheck(cus_id, com_id);
	}
	@Override
	public List<Company> queryByPager(String id, int pageNo, int pageSize) {
		return companyColDAO.queryByPager(id, pageNo, pageSize);
	}
	@Override
	public int count(String id) {
		return companyColDAO.count(id);
	}
	@Override
	public String queryById(String cus_id, String des_id) {
		return companyColDAO.queryById(cus_id, des_id);
	}

}
