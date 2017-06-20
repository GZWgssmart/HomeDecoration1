package com.gs.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Order;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;
/**
 * 
 * @author 7025
 *订单实现类
 */
public class OrderDAOImpl implements OrderDAO{

	@Override
	public void add(Order t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_order"
					+ "(id,user_id, payed, cost, created_time) "
					+ "values(uuid(),?, ?, ?, ?)");
			ps.setString(1, t.getUser_id());
			ps.setString(2, t.getPayed());
			ps.setFloat(3, t.getCost());
			ps.setDate(4, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void deleteById(String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from "
					+ "t_order where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void update(Order t) {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
	}

	@Override
	public void updatePwd(String pwd, Order t) {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
	}

	@Override
	public int count() {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
		return 0;
	}

	@Override
	public List<Order> queryAll() {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
		return null;
	}

	@Override
	public List<Order> queryByPager(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
		return null;
	}

	@Override
	public List<Order> queryByName(String name) {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
		return null;
	}

	@Override
	public Order queryByEmailPwd(String email, String pwd) {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
		return null;
	}

	@Override
	public Order queryById(String id) {
		Connection conn = ConnUtil.getConn();
		Order order = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_order where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				order = new Order();
				order.setId(rs.getString("id"));
				order.setUser_id(rs.getString("user_id"));
				order.setCost(rs.getFloat("cost"));
				order.setPayed(rs.getString("payed"));
				order.setCreated_time(rs.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return order;
	}

	@Override
	public List<Order> queryByPage(int pageNo, int pageSize, String userId) {
		Connection conn = ConnUtil.getConn();
		List<Order> orders = new ArrayList<Order>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_order where user_id = ? order by created_time "
					+ "desc limit ?,?");
			ps.setString(1, userId);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getString("id"));
				order.setUser_id(rs.getString("user_id"));
				order.setCost(rs.getFloat("cost"));
				order.setCreated_time(rs.getDate("created_time"));
				order.setPayed(rs.getString("payed"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return orders;
	}

	@Override
	public int countById(String userId) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_order where user_id = ?");
			ps.setString(1, userId);
			ResultSet re = ps.executeQuery();
			if(re.next()){
				return re.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return 0;
	}

	@Override
	public void updatePayed(String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_order set "
					+ "payed = 'Y' where user_id=? and payed='N'");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public Order queryByUserIdAndPayed(String userId) {
		Connection conn = ConnUtil.getConn();
		Order order = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_order where user_id = ? and payed='N'");
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				order = new Order();
				order.setId(rs.getString("id"));
				order.setUser_id(rs.getString("user_id"));
				order.setCost(rs.getFloat("cost"));
				order.setPayed(rs.getString("payed"));
				order.setCreated_time(rs.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return order;
	}
}
