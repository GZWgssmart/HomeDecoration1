package com.gs.dao.cust;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gs.bean.Customer;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;
/**
 * 
 * @author 曾创
 *业主DAO实现类
 */
public class CustomerDAOImpl implements CustomerDAO{
	/**
	 * 添加业主
	 */
	@Override
	public void add(Customer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into "
					+ "t_customer(id,email, password, name, phone, "
					+ "created_time,headIcon) values(uuid(),?,?, ?, ?, ?, ?)");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getName());
			ps.setString(4, t.getPhone());
			ps.setDate(5, DateUtil.convert(t.getCreated_time()));
			ps.setString(6, t.getHeadIcon());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 通过id删除业主
	 */
	@Override
	public void deleteById(String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from "
					+ "t_customer where id = ?");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 更新业主基本信息
	 */
	@Override
	public void update(Customer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_customer "
					+ "set email=?, name=?, phone=?, plot_name=?, "
					+ "address=? where id=?");
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getName());
			ps.setString(3, t.getPhone());
			ps.setString(4, t.getPlot_name());
			ps.setString(5, t.getAddress());
			ps.setString(6, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 业主修改密码
	 */
	@Override
	public void updatePwd(String pwd, Customer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_customer "
					+ "set password=? where email=?");
			ps.setString(1, pwd);
			ps.setString(2, t.getEmail());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	/**
	 * 查询业主总数
	 */
	@Override
	public int count() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_customer");
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
	 * 查询所有业主
	 */
	@Override
	public List<Customer> queryAll() {
		Connection conn = ConnUtil.getConn();
		List<Customer> cusList = new ArrayList<Customer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_customer");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Customer cus = new Customer();
				cus.setId(rs.getString("id"));
				cus.setEmail(rs.getString("email"));
				cus.setAddress(rs.getString("address"));
				cus.setCreated_time(rs.getDate("createted_time"));
				cus.setLast_login_time(rs.getDate("last_login_time"));
				cus.setName(rs.getString("name"));
				cus.setHeadIcon(rs.getString("headIcon"));
				cus.setPlot_name(rs.getString("plot_name"));
				cus.setPhone(rs.getString("phone"));
				cus.setStatus(rs.getString("status"));
				cusList.add(cus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cusList;
	}
	/**
	 * 分页查找业主
	 */
	@Override
	public List<Customer> queryByPager(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<Customer> cusList = new ArrayList<Customer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_customer limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer cus = new Customer();
				cus.setId(rs.getString("id"));
				cus.setEmail(rs.getString("email"));
				cus.setAddress(rs.getString("address"));
				cus.setCreated_time(rs.getDate("created_time"));
				cus.setLast_login_time(rs.getDate("last_login_time"));
				cus.setName(rs.getString("name"));
				cus.setHeadIcon(rs.getString("headIcon"));
				cus.setPlot_name(rs.getString("plot_name"));
				cus.setPhone(rs.getString("phone"));
				cus.setStatus(rs.getString("status"));
				cusList.add(cus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cusList;
	}
	/**
	 * 通过名字模糊搜索业主
	 */
	@Override
	public List<Customer> queryByName(String name) {
		Connection conn = ConnUtil.getConn();
		List<Customer> cusList = new ArrayList<Customer>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_customer where name like '%" + name + "%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Customer cus = new Customer();
				cus.setId(rs.getString("id"));
				cus.setEmail(rs.getString("email"));
				cus.setAddress(rs.getString("address"));
				cus.setCreated_time(rs.getDate("createted_time"));
				cus.setLast_login_time(rs.getDate("last_login_time"));
				cus.setName(rs.getString("name"));
				cus.setHeadIcon(rs.getString("headIcon"));
				cus.setPlot_name(rs.getString("plot_name"));
				cus.setPhone(rs.getString("phone"));
				cus.setStatus(rs.getString("status"));
				cusList.add(cus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cusList;
	}
	/**
	 * 通过email,pwd来判断登录
	 */
	@Override
	public Customer queryByEmailPwd(String email, String pwd) {
		Connection conn = ConnUtil.getConn();
		Customer cus = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_customer where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cus = new Customer();
				cus.setId(rs.getString("id"));
				cus.setEmail(rs.getString("email"));
				cus.setAddress(rs.getString("address"));
				cus.setCreated_time(rs.getDate("created_time"));
				cus.setLast_login_time(rs.getDate("last_login_time"));
				cus.setName(rs.getString("name"));
				cus.setHeadIcon(rs.getString("headIcon"));
				cus.setPlot_name(rs.getString("plot_name"));
				cus.setPhone(rs.getString("phone"));
				cus.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cus;
	}
	/**
	 * 更改登录时间
	 */
	@Override
	public void updateLoginTime(Date time, Customer t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_customer "
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
	 * 更改业主账号状态
	 */
	@Override
	public void updateStatus(String status, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_customer "
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
	public Customer queryById(String id) {
		Connection conn = ConnUtil.getConn();
		Customer cus = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_customer where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cus = new Customer();
				cus.setId(id);
				cus.setAddress(rs.getString("address"));
				cus.setCreated_time(rs.getDate("created_time"));
				cus.setEmail(rs.getString("email"));
				cus.setLast_login_time(rs.getDate("last_login_time"));
				cus.setName(rs.getString("name"));
				cus.setPhone(rs.getString("phone"));
				cus.setHeadIcon(rs.getString("headIcon"));
				cus.setPlot_name(rs.getString("plot_name"));
				cus.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cus;
	}
	@Override
	public boolean queryEmail(String email) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_customer where email = ?");
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
			PreparedStatement ps = conn.prepareStatement("update t_customer "
					+ "set headIcon=? where id=?");
			ps.setString(1, headIcon);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

}
