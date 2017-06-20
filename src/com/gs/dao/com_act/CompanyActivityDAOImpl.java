package com.gs.dao.com_act;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.CompanyActivity;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 装修公司活动表t_company_activity				
 * @author ID.LQF
 *
 */
public class CompanyActivityDAOImpl implements CompanyActivityDAO{

	@Override
	public void add(CompanyActivity t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_company_activity(id, company_id,name,image,des,"
					+ "created_time) values (uuid(),?,?,?,?,?) ");
			ps.setString(1, t.getCompany_id());
			ps.setString(2, t.getName());
			ps.setString(3, t.getImage());
			ps.setString(4, t.getDes());
			ps.setDate(5, DateUtil.convert(t.getCreated_time()));
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
					+ "t_company_activity where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(CompanyActivity t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_company_activity set company_id=?,name=?,image=?,"
					+ "des=? where id=?");
			ps.setString(1, t.getCompany_id());
			ps.setString(2, t.getName());
			ps.setString(3, t.getImage());
			ps.setString(4, t.getDes());
			ps.setString(5, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, CompanyActivity t) {
	}

	@Override
	public List<CompanyActivity> queryAll() {
		Connection con = ConnUtil.getConn();
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_activity");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivitys;
	}

	@Override
	public List<CompanyActivity> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_activity limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivitys;
	}

	@Override
	public CompanyActivity queryByEmailPwd(String email, String pwd) {
		Connection con = ConnUtil.getConn();
		CompanyActivity companyActivity = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_activity where email = ?,pwd = ?");
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivity;
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company_activity");
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
	public List<CompanyActivity> queryByName(String name) {
		Connection con = ConnUtil.getConn();
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_activity where name=?");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivitys;
	}

	@Override
	public void updateStatus(String check, String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_company_activity set status=? where id=?");
			ps.setString(1, check);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public CompanyActivity queryById(String id) {
		Connection con = ConnUtil.getConn();
		CompanyActivity companyActivity = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_activity where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyActivity = new CompanyActivity();
				companyActivity.setId(id);
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivity;
	}

	@Override
	public List<CompanyActivity> queryActiveTop3(String id) {
		Connection con = ConnUtil.getConn();
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_company_activity where status='Y' and "
					+ "company_id=? limit 0,3");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivitys;
	}

	@Override
	public List<CompanyActivity> queryByCompId(String id) {
		Connection con = ConnUtil.getConn();
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_activity where company_id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivitys;
	}

	@Override
	public List<CompanyActivity> queryActiveTop3() {
		Connection con = ConnUtil.getConn();
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_company_activity where status='Y' limit 0,3");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivitys;
	}

	@Override
	public List<CompanyActivity> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_activity where company_id = ? order by "
					+ "created_time desc limit ?, ?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity.setId(re.getString("id"));
				companyActivity.setCompany_id(re.getString("company_id"));
				companyActivity.setName(re.getString("name"));
				companyActivity.setImage(re.getString("image"));
				companyActivity.setDes(re.getString("des"));
				companyActivity.setCreated_time(re.getDate("created_time"));
				companyActivity.setStatus(re.getString("status"));
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyActivitys;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_company_activity where "
					+ "company_id='"+id+"')stu");
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

}
