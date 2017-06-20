package com.gs.dao.comp_case_col;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.CompanyCase;
import com.gs.bean.CompanyCaseCol;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 装修公司案例收藏表t_company_case_col				
 * @author ID.LQF
 *
 */
public class CompanyCaseColDAOImpl implements CompanyCaseColDAO{

	@Override
	public void add(CompanyCaseCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_company_case_col(id,customer_id,case_id,"
					+ "created_time) values (uuid(),?,?,?) ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getCase_id());
			ps.setDate(3, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void deleteById(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("delete from "
					+ "t_company_case_col where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(CompanyCaseCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_company_case_col set customer_id=?,case_id=?"
					+ ",created_time=? where id=? ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getCase_id());
			ps.setDate(3, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, CompanyCaseCol t) {
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company_case_col");
			ResultSet re = ps.executeQuery();
			if(re.next()){
				return re.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return 0;
	}

	@Override
	public List<CompanyCaseCol> queryAll() {
		Connection con = ConnUtil.getConn();
		List<CompanyCaseCol> companys = new ArrayList<CompanyCaseCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_company_case_col");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyCaseCol companyCol = new CompanyCaseCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setCase_id(re.getString("case_id"));
				companyCol.setCreated_time(re.getDate("created_time"));
				companys.add(companyCol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companys;
	}

	@Override
	public List<CompanyCaseCol> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<CompanyCaseCol> companys = new ArrayList<CompanyCaseCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case_col limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				CompanyCaseCol companyCol = new CompanyCaseCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setCase_id(re.getString("case_id"));
				companyCol.setCreated_time(re.getDate("created_time"));
				companys.add(companyCol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companys;
	}

	@Override
	public List<CompanyCaseCol> queryByName(String name) {
		return null;
	}

	@Override
	public CompanyCaseCol queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public CompanyCaseCol queryById(String id) {
		Connection con = ConnUtil.getConn();
		CompanyCaseCol companyCol =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case_col where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCol = new CompanyCaseCol();
				companyCol.setId(id);
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setCase_id(re.getString("case_id"));
				companyCol.setCreated_time(re.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCol;
	}

	@Override
	public String saveCheck(String cus_id, String comCase_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_company_case_col where customer_id=? and case_id=?");
			ps.setString(1, cus_id);
			ps.setString(2, comCase_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return null;
	}

	@Override
	public List<CompanyCase> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select c.* from "
					+ "t_company_case_col c,t_company_case ca where "
					+ "c.case_id = ca.id and c.customer_id = ? order by "
					+ "c.created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("case_id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company_case_col where customer_id = ?");
			ps.setString(1, id);
			ResultSet re = ps.executeQuery();
			if(re.next()){
				return re.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return 0;
	}

	@Override
	public String queryById(String cus_id, String des_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_company_case_col where customer_id = ? and "
					+ "case_id = ?");
			ps.setString(1, cus_id);
			ps.setString(2, des_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return null;
	}

}
