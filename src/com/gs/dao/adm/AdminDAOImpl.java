package com.gs.dao.adm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gs.bean.Admin;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;
/**
 * 
 * @author 曾创
 *管理员DAO实现类
 */
public class AdminDAOImpl implements AdminDAO{
	/**
	 * 添加普通管理员
	 */
	@Override
	public void add(Admin t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into "
					+ "t_admin(id, email, password, name, phone, role, "
					+ "created_time, last_login_time) values"
					+ "(uuid(), ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getName());
			ps.setString(4, t.getPhone());
			ps.setString(5, t.getRole());
			ps.setDate(6, DateUtil.convert(t.getCreated_time()));
			ps.setDate(7, DateUtil.convert(t.getLast_login_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 删除管理员
	 */
	@Override
	public void deleteById(String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from "
					+ "t_admin where id = ?");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 更新管理员信息
	 */
	@Override
	public void update(Admin t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_admin "
					+ "set email=?, name=?, phone=? where id=?");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getName());
			ps.setString(3, t.getPhone());
			ps.setString(4, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 修改密码
	 */
	@Override
	public void updatePwd(String pwd, Admin t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_admin "
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
	 * 管理员计数
	 */
	@Override
	public int count() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_admin");
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
	 * 查询所有管理员
	 */
	@Override
	public List<Admin> queryAll() {
		Connection conn = ConnUtil.getConn();
		List<Admin> adms = new ArrayList<Admin>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_admin");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Admin adm = new Admin();
				adm.setId(rs.getString("id"));
				adm.setEmail(rs.getString("email"));
				adm.setName(rs.getString("name"));
				adm.setPhone(rs.getString("phone"));
				adm.setRole(rs.getString("role"));
				adm.setCreated_time(rs.getDate("created_time"));
				adm.setLast_login_time(rs.getDate("last_login_time"));
				adm.setStatus(rs.getString("status"));
				adms.add(adm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return adms;
	}
	/**
	 * 分页查询所有管理员
	 */
	@Override
	public List<Admin> queryByPager(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<Admin> adms = new ArrayList<Admin>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_admin where role = 'normal' order by created_time "
					+ "desc limit ?, ?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Admin adm = new Admin();
				adm.setId(rs.getString("id"));
				adm.setEmail(rs.getString("email"));
				adm.setName(rs.getString("name"));
				adm.setPhone(rs.getString("phone"));
				adm.setRole(rs.getString("role"));
				adm.setCreated_time(rs.getDate("created_time"));
				adm.setLast_login_time(rs.getDate("last_login_time"));
				adm.setStatus(rs.getString("status"));
				adms.add(adm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return adms;
	}
	/**
	 * 通过名字查询管理员
	 */
	@Override
	public List<Admin> queryByName(String name) {
		Connection conn = ConnUtil.getConn();
		List<Admin> adms = new ArrayList<Admin>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_designer where name like '%" + name + "%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Admin adm = new Admin();
				adm.setId(rs.getString("id"));
				adm.setEmail(rs.getString("email"));
				adm.setName(rs.getString("name"));
				adm.setPhone(rs.getString("phone"));
				adm.setRole(rs.getString("role"));
				adm.setCreated_time(rs.getDate("created_time"));
				adm.setLast_login_time(rs.getDate("last_login_time"));
				adm.setStatus(rs.getString("status"));
				adms.add(adm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return adms;
	}
	/**
	 * 管理员通过账号密码登录
	 */
	@Override
	public Admin queryByEmailPwd(String email, String pwd) {
		Connection conn = ConnUtil.getConn();
		Admin adm = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_admin where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				adm = new Admin();
				adm.setId(rs.getString("id"));
				adm.setEmail(rs.getString("email"));
				adm.setName(rs.getString("name"));
				adm.setPhone(rs.getString("phone"));
				adm.setRole(rs.getString("role"));
				adm.setCreated_time(rs.getDate("created_time"));
				adm.setLast_login_time(rs.getDate("last_login_time"));
				adm.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return adm;
	}
	/**
	 * 更新登录时间
	 */
	@Override
	public void updateLoginTime(Date time, Admin t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_admin "
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
	 * 修改账号状态
	 */
	@Override
	public void updateStatus(String status, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_admin "
					+ "set status=? where id=?");
			ps.setString(1, status);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	
	@Override
	public Admin queryById(String id) {
		Connection conn = ConnUtil.getConn();
		Admin adm = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_admin where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				adm = new Admin();
				adm.setId(id);
				adm.setEmail(rs.getString("email"));
				adm.setName(rs.getString("name"));
				adm.setCreated_time(rs.getDate("created_time"));
				adm.setLast_login_time(rs.getDate("last_login_time"));
				adm.setPhone(rs.getString("phone"));
				adm.setRole(rs.getString("role"));
				adm.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return adm;
	}
	@Override
	public boolean queryEmail(String email) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_admin where email = ?");
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

}
