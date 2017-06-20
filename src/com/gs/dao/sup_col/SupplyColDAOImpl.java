package com.gs.dao.sup_col;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Supply;
import com.gs.bean.SupplyCol;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 建材商收藏表t_supply_col				
 * @author ID.LQF
 *
 */
public class SupplyColDAOImpl implements SupplyColDAO{

	@Override
	public void add(SupplyCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_supply_col(id,customer_id,supply_id,created_time) "
					+ "values (uuid(),?,?,?) ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getSupply_id());
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
					+ "t_supply_col where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(SupplyCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_supply_col"
					+ " set customer_id=?,supply_id=?,created_time=? "
					+ "where id=? ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getSupply_id());
			ps.setDate(3, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, SupplyCol t) {
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_supply_col");
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
	public List<SupplyCol> queryAll() {
		Connection con = ConnUtil.getConn();
		List<SupplyCol> companys = new ArrayList<SupplyCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply_col");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				SupplyCol companyCol = new SupplyCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setSupply_id(re.getString("supply_id"));
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
	public List<SupplyCol> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<SupplyCol> companys = new ArrayList<SupplyCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from t_supply_col limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				SupplyCol companyCol = new SupplyCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setSupply_id(re.getString("supply_id"));
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
	public List<SupplyCol> queryByName(String name) {
		return null;
	}

	@Override
	public SupplyCol queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public SupplyCol queryById(String id) {
		Connection con = ConnUtil.getConn();
		SupplyCol companyCol =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply_col where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCol = new SupplyCol();
				companyCol.setId(id);
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setSupply_id(re.getString("supply_id"));
				companyCol.setCreated_time(re.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCol;
	}

	@Override
	public String saveCheck(String cus_id, String sup_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_supply_col where customer_id=? and supply_id=?");
			ps.setString(1, cus_id);
			ps.setString(2, sup_id);
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
	public List<Supply> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select s.* from "
					+ "t_supply_col sc,t_supply s where sc.customer_id=? "
					+ "and sc.supply_id = s.id order by sc.created_time "
					+ "desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				Supply supply = new Supply();
				supply.setId(re.getString("id"));
				supply.setEmail(re.getString("email"));
				supply.setName(re.getString("name"));
				supply.setPrincipal(re.getString("principal"));
				supply.setLogo(re.getString("logo"));
				supply.setAddress(re.getString("address"));
				supply.setPhone(re.getString("phone"));
				supply.setTel(re.getString("tel"));
				supply.setOpen_date(re.getDate("open_date"));
				supply.setLongitude(re.getFloat("longitude"));
				supply.setLatitude(re.getFloat("latitude"));
				supply.setDes(re.getString("des"));
				supply.setCreated_time(re.getDate("created_time"));
				supply.setChecked(re.getString("checked"));
				supply.setChecked_time(re.getDate("checked_time"));
				supply.setLast_login_time(re.getDate("last_login_time"));
				supply.setStatus(re.getString("status"));
				supplys.add(supply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return supplys;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_supply_col where customer_id = ?");
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
					+ "t_supply_col where customer_id = ? and supply_id = ?");
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
