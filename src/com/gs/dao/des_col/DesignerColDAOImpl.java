package com.gs.dao.des_col;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Designer;
import com.gs.bean.DesignerCol;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 设计师收藏表t_designer_col				
 * @author ID.LQF
 *
 */
public class DesignerColDAOImpl implements DesignerColDAO{

	@Override
	public void add(DesignerCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_designer_col(id,customer_id,designer_id,created_time)"
					+ " values (uuid(),?,?,?) ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getDesigner_id());
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
					+ "t_designer_col where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(DesignerCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_designer_col set customer_id=?,designer_id=?,"
					+ "created_time=? where id=? ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getDesigner_id());
			ps.setDate(3, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, DesignerCol t) {
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_designer_col");
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
	public List<DesignerCol> queryAll() {
		Connection con = ConnUtil.getConn();
		List<DesignerCol> companys = new ArrayList<DesignerCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_col");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				DesignerCol companyCol = new DesignerCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setDesigner_id(re.getString("designer_id"));
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
	public List<Designer> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = con.prepareStatement("select d.* from "
					+ "t_designer_col dc,t_designer d where "
					+ "dc.designer_id = d.id and dc.customer_id = ? "
					+ "order by dc.created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Designer des = new Designer();
				des.setId(rs.getString("id"));
				des.setEmail(rs.getString("email"));
				des.setName(rs.getString("name"));
				des.setHeadicon(rs.getString("headicon"));
				des.setPhone(rs.getString("phone"));
				des.setAddress(rs.getString("address"));
				des.setExperience(rs.getString("experience"));
				des.setStyle(rs.getString("style"));
				des.setDes(rs.getString("des"));
				des.setCreated_time(rs.getDate("created_time"));
				des.setChecked(rs.getString("checked"));
				des.setChecked_time(rs.getDate("checked_time"));
				des.setLast_login_time(rs.getDate("last_login_time"));
				des.setStatus(rs.getString("status"));
				designers.add(des);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designers;
	}

	@Override
	public List<DesignerCol> queryByName(String name) {
		return null;
	}

	@Override
	public DesignerCol queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public DesignerCol queryById(String id) {
		Connection con = ConnUtil.getConn();
		DesignerCol companyCol =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_col where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCol = new DesignerCol();
				companyCol.setId(id);
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setDesigner_id(re.getString("designer_id"));
				companyCol.setCreated_time(re.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCol;
	}

	@Override
	public String saveCheck(String cus_id, String des_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_designer_col where customer_id=? and designer_id=?");
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

	@Override
	public List<DesignerCol> queryByPager(int pageNo, int pageSize) {
		return null;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_designer_col where customer_id = ?");
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
					+ "t_designer_col where customer_id = ? and "
					+ "designer_id = ?");
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
