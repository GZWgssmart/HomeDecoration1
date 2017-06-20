package com.gs.dao.comp_col;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Company;
import com.gs.bean.CompanyCol;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 装修公司收藏表t_company_col				
 * @author ID.LQF
 *
 */
public class CompanyColDAOImpl implements CompanyColDAO{

	@Override
	public void add(CompanyCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_company_col(id,customer_id,company_id,created_time)"
					+ " values (uuid(),?,?,?) ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getCompany_id());
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
					+ "t_company_col where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(CompanyCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_company_col set customer_id=?,company_id=?,"
					+ "created_time=? where id=? ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getCompany_id());
			ps.setDate(3, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, CompanyCol t) {
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company_col");
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
	public List<CompanyCol> queryAll() {
		Connection con = ConnUtil.getConn();
		List<CompanyCol> companys = new ArrayList<CompanyCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_col");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyCol companyCol = new CompanyCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setCompany_id(re.getString("company_id"));
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
	public List<CompanyCol> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<CompanyCol> companys = new ArrayList<CompanyCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_col limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				CompanyCol companyCol = new CompanyCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setCompany_id(re.getString("company_id"));
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
	public List<CompanyCol> queryByName(String name) {
		return null;
	}

	@Override
	public CompanyCol queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public CompanyCol queryById(String id) {
		Connection con = ConnUtil.getConn();
		CompanyCol companyCol =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_col where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCol = new CompanyCol();
				companyCol.setId(id);
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setCompany_id(re.getString("company_id"));
				companyCol.setCreated_time(re.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCol;
	}

	@Override
	public String saveCheck(String cus_id, String com_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_company_col where customer_id=? and company_id=?");
			ps.setString(1, cus_id);
			ps.setString(2, com_id);
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
	public List<Company> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Company> companys = new ArrayList<Company>();
		try {
			PreparedStatement ps = con.prepareStatement("select com.* from "
					+ "t_company_col c,t_company com where c.company_id = "
					+ "com.id and c.customer_id = ? order by c.created_time "
					+ "desc limit ?, ?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				Company company = new Company();
				company.setId(re.getString("id"));
				company.setEmail(re.getString("email"));
				company.setName(re.getString("name"));
				company.setPrincipal(re.getString("principal"));
				company.setLogo(re.getString("logo"));
				company.setAddress(re.getString("address"));
				company.setPhone(re.getString("phone"));
				company.setTel(re.getString("tel"));
				company.setOpenDate(re.getDate("open_date"));
				company.setLongitude(re.getFloat("longitude"));
				company.setLatitude(re.getFloat("latitude"));
				company.setDes(re.getString("des"));
				company.setCreated_time(re.getDate("created_time"));
				company.setChecked(re.getString("checked"));
				company.setChecked_time(re.getDate("checked_time"));
				company.setLast_login_time(re.getDate("last_login_time"));
				company.setStatus(re.getString("status"));
				companys.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companys;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company_col where customer_id = ?");
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
					+ "t_company_col where customer_id = ? "
					+ "and company_id = ?");
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
