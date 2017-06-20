package com.gs.dao.comp;

import java.util.Date;
import java.util.List;

import com.gs.bean.Company;
import com.gs.dao.BaseDAO;

public interface CompanyBaseDAO extends BaseDAO<String, Company>{

	/**
	 * 更改审核时间
	 */
	public void updateCheckedAndTime(String check, Date time, String id);
	
	/**
	 * 更改登入时间
	 */
	public void updateLoginTime(Date time, Company t);
	
	/**
	 * 更改状态
	 */
	public void updateStatus(String check, String id);
	
	/**
	 * 查找数据库中是否存在这个邮箱
	 */
	public boolean queryByEmail(String email);
	/**
	 * 分页查找所有未审核的装修公司
	 * @param pageNo
	 * @param PageSize
	 * @return
	 */
	public List<Company> queryChecked(int pageNo, int pageSize);
	/**
	 * 通过id来修改logo
	 * @param logo
	 * @param id
	 */
	public void updateLogo(String logo, String id);
	
	/**
	 * 查找最顶端的6个装修公司
	 */
	public List<Company> queryCompanyTop6();
	/**
	 * 有约束的设计师计数(审核)
	 * @return
	 */
	public int countPass();
	
	public int countChecked();
	
	/**
	 * 搜索查询装修公司
	 */
	
	public List<Company> searchCom(int pageNo, int pageSize, String com);
	
	/**
	 * 搜索查询装修公司个数
	 */
	
	public int searchConInt(String com);
	
	/**
	 * 搜索查询装修公司名字
	 */
	
	public List<Company> Comname(String search);
}
