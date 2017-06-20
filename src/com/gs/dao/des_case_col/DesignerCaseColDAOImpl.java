package com.gs.dao.des_case_col;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.DesignerCase;
import com.gs.bean.DesignerCaseCol;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 设计师案例收藏表t_designer_case_col				
 * @author ID.LQF
 *
 */
public class DesignerCaseColDAOImpl implements DesignerCaseColDAO{

	@Override
	public void add(DesignerCaseCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_designer_case_col(id,customer_id,case_id,"
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
					+ "t_designer_case_col where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(DesignerCaseCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_designer_case_col set customer_id=?,case_id=?,"
					+ "created_time=? where id=? ");
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
	public void updatePwd(String pwd, DesignerCaseCol t) {
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_designer_case_col");
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
	public List<DesignerCaseCol> queryAll() {
		Connection con = ConnUtil.getConn();
		List<DesignerCaseCol> companys = new ArrayList<DesignerCaseCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case_col");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				DesignerCaseCol companyCol = new DesignerCaseCol();
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
	public List<DesignerCaseCol> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<DesignerCaseCol> companys = new ArrayList<DesignerCaseCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select dc.* from "
					+ "t_designer_case_col dc,t_designer_case d where "
					+ "dc.case_id = d.id order by dc.created_time limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				DesignerCaseCol companyCol = new DesignerCaseCol();
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
	public List<DesignerCaseCol> queryByName(String name) {
		return null;
	}

	@Override
	public DesignerCaseCol queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public DesignerCaseCol queryById(String id) {
		Connection con = ConnUtil.getConn();
		DesignerCaseCol companyCol =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case_col where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCol = new DesignerCaseCol();
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
	public String saveCheck(String cus_id, String desCase_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_designer_case_col where customer_id=? and case_id=?");
			ps.setString(1, cus_id);
			ps.setString(2, desCase_id);
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
	public List<DesignerCase> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> desCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case_col dc, t_designer_case d where "
					+ "dc.case_id = d.id and dc.customer_id = ? limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(rs.getString("case_id"));
				designerCase.setDesigner_id(rs.getString("designer_id"));
				designerCase.setName(rs.getString("name"));
				designerCase.setPlot_name(rs.getString("plot_name"));
				designerCase.setStyle(rs.getString("style"));
				designerCase.setImage_1(rs.getString("image_1"));
				designerCase.setImage_2(rs.getString("image_2"));
				designerCase.setImage_3(rs.getString("image_3"));
				designerCase.setImage_4(rs.getString("image_4"));
				designerCase.setImage_5(rs.getString("image_5"));
				designerCase.setDes(rs.getString("des"));
				designerCase.setCreated_time(rs.getDate("created_time"));
				designerCase.setStatus(rs.getString("status"));
				desCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return desCases;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) from t_designer_case_col where customer_id = ?");
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
			PreparedStatement ps = con.prepareStatement("select id from t_designer_case_col where customer_id = ? and case_id = ?");
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
