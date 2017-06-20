package com.gs.dao.comp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gs.bean.Company;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 装修公司表t_company				
 * @author ID.LQF
 *
 */

public class CompanyBaseDAOImpl implements CompanyBaseDAO{

	@Override
	public void add(Company t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_company(id,email,password,name,principal,phone,"
					+ "created_time,checked_time,last_login_time,logo) "
					+ "values (uuid(),?,?,?,?,?,?,?,?,?) ");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getName());
			ps.setString(4, t.getPrincipal());
			ps.setString(5, t.getPhone());
			ps.setDate(6, DateUtil.convert(t.getCreated_time()));
			ps.setDate(7, DateUtil.convert(t.getChecked_time()));
			ps.setDate(8, DateUtil.convert(t.getLast_login_time()));
			ps.setString(9, t.getLogo());
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
					+ "t_company where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(Company t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_company "
					+ "set email=?,name=?,principal=?,logo=?,address=?,"
					+ "phone=?,open_date=?,longitude=?,latitude=?,des=?"
					+ ",last_login_time=?,tel=? where id=?");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getName());
			ps.setString(3, t.getPrincipal());
			ps.setString(4, t.getLogo());
			ps.setString(5, t.getAddress());
			ps.setString(6, t.getPhone());
			ps.setDate(7, DateUtil.convert(t.getOpenDate()));
			ps.setFloat(8, t.getLongitude());
			ps.setFloat(9, t.getLatitude());
			ps.setString(10, t.getDes());
			ps.setDate(11, DateUtil.convert(t.getLast_login_time()));
			ps.setString(12, t.getTel());
			ps.setString(13, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}
	
	@Override
	public void updatePwd(String pwd,Company t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_company "
					+ "set password=? where id=?");
			ps.setString(1, pwd);
			ps.setString(2, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updateCheckedAndTime(String check, Date time, String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_company "
					+ "set checked=?, checked_time=? where id=?");
			ps.setString(1, check);
			ps.setDate(2, DateUtil.convert(time));
			ps.setString(3, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updateLoginTime(Date time, Company t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_company "
					+ "set last_login_time=? where id=?");
			ps.setDate(1, DateUtil.convert(time));
			ps.setString(2, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updateStatus(String check, String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_company "
					+ "set status=? where id=?");
			ps.setString(1, String.valueOf(check));
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}
	
	@Override
	public List<Company> queryAll() {
		Connection con = ConnUtil.getConn();
		List<Company> companys = new ArrayList<Company>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company");
			ResultSet re =ps.executeQuery();
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
	public Company queryByEmailPwd(String email, String pwd) {
		Connection con = ConnUtil.getConn();
		Company company = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company where email = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				company = new Company();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return company;
	}

	@Override
	public List<Company> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Company> companys = new ArrayList<Company>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company where checked ='Y' limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company");
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
	public int countChecked() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company where checked='Y'");
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
	public List<Company> queryByName(String name) {
		Connection con = ConnUtil.getConn();
		List<Company> companys = new ArrayList<Company>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company where name=?");
			ResultSet re =ps.executeQuery();
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
	public Company queryById(String id) {
		Connection con = ConnUtil.getConn();
		Company company =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				company = new Company();
				company.setId(id);
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return company;
	}

	@Override
	public boolean queryByEmail(String email) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company where email=?");
			ps.setString(1, email);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return false;
	}

	@Override
	public List<Company> queryChecked(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Company> companys = new ArrayList<Company>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company where checked = 'N' order by created_time "
					+ "desc limit ?, ?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public void updateLogo(String logo, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_company "
					+ "set logo=? where id=?");
			ps.setString(1, logo);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public List<Company> queryCompanyTop6() {
		Connection con = ConnUtil.getConn();
		List<Company> companys = new ArrayList<Company>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_company where status = 'Y' and checked = 'Y' limit 0,6");
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
	public int countPass() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company where checked = 'N'");
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
	public List<Company> searchCom(int pageNo, int pageSize,String com) {
		Connection con = ConnUtil.getConn();
		List<Company> companys = new ArrayList<Company>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company where name like '"+com+"%' and "
							+ "checked = 'Y' limit ?, ?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public int searchConInt(String com) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company where name like '"+com+"%%' and "
							+ "checked = 'Y' ");
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
	public List<Company> Comname(String search) {
		return null;
	}

}
