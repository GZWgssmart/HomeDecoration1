package com.gs.service.comp;

import java.util.Date;
import java.util.List;

import com.gs.bean.Company;
import com.gs.dao.comp.CompanyBaseDAO;
import com.gs.dao.comp.CompanyBaseDAOImpl;
import com.gs.util.ConnUtil;

/**
 * 装修公司表t_company				
 * @author ID.LQF
 *
 */
public class CompanyBaseServiceImpl extends ConnUtil implements CompanyBaseService{

	private CompanyBaseDAO companyBaseDAO;
	
	public CompanyBaseServiceImpl(){
		companyBaseDAO = new CompanyBaseDAOImpl();
	}
	
	@Override
	public void add(Company t) {
		companyBaseDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		companyBaseDAO.deleteById(id);
	}

	@Override
	public void update(Company t) {
		companyBaseDAO.update(t);
	}
	
	@Override
	public void updatePwd(String pwd,Company t) {
		companyBaseDAO.updatePwd(pwd,t);
	}

	@Override
	public List<Company> queryAll() {
		return companyBaseDAO.queryAll();
	}

	@Override
	public List<Company> queryByPager(int pageNo, int pageSize) {
		return companyBaseDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public int count() {
		return companyBaseDAO.count();
	}

	@Override
	public List<Company> queryByName(String name) {
		return companyBaseDAO.queryByName(name);
	}

	@Override
	public Company queryByEmailPwd(String email, String pwd) {
		return companyBaseDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public void updateCheckedAndTime(String check, Date time, String id) {
		companyBaseDAO.updateCheckedAndTime(check, time, id);
	}

	@Override
	public void updateLoginTime(Date time, Company t) {
		companyBaseDAO.updateLoginTime(time, t);
	}

	@Override
	public void updateStatus(String check, String id) {
		companyBaseDAO.updateStatus(check, id);
	}

	@Override
	public Company queryById(String id) {
		return companyBaseDAO.queryById(id);
	}

	@Override
	public boolean queryByEmail(String email) {
		return companyBaseDAO.queryByEmail(email);
	}

	@Override
	public List<Company> queryChecked(int pageNo, int pageSize) {
		return companyBaseDAO.queryChecked(pageNo, pageSize);
	}

	@Override
	public void updateLogo(String logo, String id) {
		companyBaseDAO.updateLogo(logo, id);
	}

	@Override
	public List<Company> queryCompanyTop6() {
		return companyBaseDAO.queryCompanyTop6();
	}

	@Override
	public int countPass() {
		return companyBaseDAO.countPass();
	}

	@Override
	public int countChecked() {
		// TODO Auto-generated method stub
		return companyBaseDAO.countChecked();
	}

	@Override
	public List<Company> searchCom(int pageNo, int pageSize,String com) {
		// TODO Auto-generated method stub
		return companyBaseDAO.searchCom(pageNo, pageSize,com);
	}

	@Override
	public int searchConInt(String com) {
		// TODO Auto-generated method stub
		return companyBaseDAO.searchConInt(com);
	}
	@Override
	public List<Company> Comname(String search) {
		// TODO Auto-generated method stub
		return companyBaseDAO.Comname(search);
	}


}
