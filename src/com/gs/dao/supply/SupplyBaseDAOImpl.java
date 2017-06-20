package com.gs.dao.supply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gs.bean.Supply;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 建材商表t_supply				
 * @author ID.LQF
 *
 */
public class SupplyBaseDAOImpl implements SupplyBaseDAO{

	@Override
	public void add(Supply t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_supply(id,email,password,name,principal,phone,"
					+ "created_time,checked_time,last_login_time,logo) "
					+ "values(uuid(),?,?,?,?,?,?,?,?,?)");
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
					+ "t_supply where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(Supply t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_supply "
					+ "set email=?,name=?,principal=?,logo=?,address=?,"
					+ "phone=?,open_date=?,longitude=?,latitude=?,des=?,"
					+ "last_login_time=?,tel=? where id=?");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getName());
			ps.setString(3, t.getPrincipal());
			ps.setString(4, t.getLogo());
			ps.setString(5, t.getAddress());
			ps.setString(6, t.getPhone());
			ps.setDate(7, DateUtil.convert(t.getOpen_date()));
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
	public void updatePwd(String pwd, Supply t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_supply "
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
	public List<Supply> queryAll() {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply");
			ResultSet re =ps.executeQuery();
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
	public List<Supply> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public Supply queryByEmailPwd(String email, String pwd) {
		Connection con = ConnUtil.getConn();
		Supply supply = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				supply = new Supply();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return supply;
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_supply");
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
	public List<Supply> queryByName(String name) {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply where name=?");
			ResultSet re =ps.executeQuery();
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
	public void updateCheckedAndTime(String check, Date time, String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_supply "
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
	public void updateLoginTime(Date time, Supply t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_supply "
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
			PreparedStatement ps = con.prepareStatement("update t_supply "
					+ "set status=? where id=?");
			ps.setString(1, check);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public Supply queryById(String id) {
		Connection con = ConnUtil.getConn();
		Supply supply = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				supply = new Supply();
				supply.setId(id);
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return supply;
	}

	@Override
	public boolean queryByEmail(String email) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply where email=?");
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
	public List<Supply> queryChecked(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply where checked = 'N' order by created_time "
					+ "desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public void updateLogo(String logo, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_supply "
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
	public List<Supply> desTop6() {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_supply where status = 'Y' and checked = 'Y' limit 0,6");
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
	public int countCheck() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_supply where status = 'Y' and checked='Y'");
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
	public List<Supply> queryByPagerCheck(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply where status = 'Y' and checked='Y' order by created_time "
					+ "desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public int countPass() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_supply where checked = 'N'");
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
	public List<Supply> searchCom(int pageNo, int pageSize, String com) {
		Connection con = ConnUtil.getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_supply where name like '"+com+"%%' and "
							+ "checked = 'Y' limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public int searchConInt(String com) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_supply where name like '"+com+"%%' ");
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
	public List<Supply> Comname(String search) {
		return null;
	}

}
