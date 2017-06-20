package com.gs.dao.des;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gs.bean.Designer;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;
/**
 * 
 * @author 曾创
 *设计师DAO实现类
 */
public class DesignerDAOImpl implements DesignerDAO{
	
	@Override
	public void add(Designer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into "
					+ "t_designer(id,email, password, name, phone,"
					+ " experience, created_time, "
					+ "last_login_time,headicon) "
					+ "values(uuid(),?,?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getName());
			ps.setString(4, t.getPhone());
			ps.setString(5, t.getExperience());
			ps.setDate(6, DateUtil.convert(t.getCreated_time()));
			ps.setDate(7, DateUtil.convert(t.getLast_login_time()));
			ps.setString(8, t.getHeadicon());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 通过id来删除
	 */
	@Override
	public void deleteById(String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from "
					+ "t_desgner where id = ?");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	/**
	 * 更新基本资料
	 */
	@Override
	public void update(Designer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer "
					+ "set email=?, name=?, headicon=?, phone=?, address=?, "
					+ "experience=?, style=?, des=? where id=?");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getName());
			ps.setString(3, t.getHeadicon());
			ps.setString(4, t.getPhone());
			ps.setString(5, t.getAddress());
			ps.setString(6, t.getExperience());
			ps.setString(7, t.getStyle());
			ps.setString(8, t.getDes());
			ps.setString(9, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 更新密码
	 */
	@Override
	public void updatePwd(String pwd, Designer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer "
					+ "set password=? where id=?");
			ps.setString(1, pwd);
			ps.setString(2, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	
	/**
	 * 查询设计师个数
	 */
	@Override
	public int count() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_designer");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return 0;
	}
	/**
	 * 查看所有设计师的个人信息
	 */
	@Override
	public List<Designer> queryAll() {
		Connection conn = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
		ConnUtil.close(conn);
		return designers;
	}
	/**
	 * 分页查看所有设计师
	 */
	@Override
	public List<Designer> queryByPager(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer order by created_time limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return designers;
	}
	/**
	 * 通过name查找设计师
	 */
	@Override
	public List<Designer> queryByName(String name) {
		Connection conn = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer where name like '%" + name + "%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
		ConnUtil.close(conn);
		return designers;
	}
	/**
	 * 更新审核信息和审核时间
	 */
	@Override
	public void updateCheckedAndTime(String check, Date time, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer "
					+ "set checked=?, checked_time=? where id=?");
			ps.setString(1, check);
			ps.setDate(2, DateUtil.convert(time));
			ps.setString(3, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 更新登录时间
	 */
	@Override
	public void updateLoginTime(Date time, Designer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer "
					+ "set last_login_time=? where id=?");
			ps.setDate(1, DateUtil.convert(time));
			ps.setString(2, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 更新账号状态
	 */
	@Override
	public void updateStatus(String status, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer "
					+ "set status=? where id=?");
			ps.setString(1, status);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 通过账户密码查询登录
	 */
	@Override
	public Designer queryByEmailPwd(String email, String pwd) {
		Connection conn = ConnUtil.getConn();
		Designer des = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				des = new Designer();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return des;
	}
	
	@Override
	public Designer queryById(String id) {
		Connection conn = ConnUtil.getConn();
		Designer des = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				des = new Designer();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return des;
	}
	
	@Override
	public List<Designer> queryChecked(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer where checked = 'N' order by created_time "
					+ "desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return designers;
	}
	/**
	 * 
	 */
	@Override
	public boolean queryEmail(String email) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return false;
	}
	@Override
	public void updateHeadIcon(String headIcon, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer "
					+ "set headicon=? where id=?");
			ps.setString(1, headIcon);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	@Override
	public List<Designer> desTop5() {
		Connection conn = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * "
					+ "from t_designer where status = 'Y' and checked = 'Y' limit 0,5");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return designers;
	}
	@Override
	public int countCheck() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_designer where status = 'Y' and checked = 'Y'");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return 0;
	}
	@Override
	public List<Designer> queryByPagerCheck(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer where checked = 'Y' and status = 'Y' "
					+ "order by created_time desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return designers;
	}
	@Override
	public int countPass() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_designer where checked = 'N'");
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
	public List<Designer> searchCom(int pageNo, int pageSize, String com) {
		Connection con = ConnUtil.getConn();
		List<Designer> designers = new ArrayList<Designer>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer where name like '"+com+"%%' and "
							+ "checked = 'Y' limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public int searchConInt(String com) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_designer where name like '"+com+"%%' ");
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
	public List<Designer> Comname(String search) {
		return null;
	}

}
