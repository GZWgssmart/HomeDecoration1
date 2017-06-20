package com.gs.dao.detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.OrderDetail;
import com.gs.bean.ProAndDetail;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

public class DetailDAOImpl implements DetailDAO{

	@Override
	public void add(OrderDetail t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into "
					+ "t_order_detail(id,order_id, product_id, total, cost, "
					+ "price,created_time) values(uuid(),?, ?, ?, ?, ?, ?);");
			ps.setString(1, t.getOrder_id());
			ps.setString(2, t.getProduct_id());
			ps.setInt(3, t.getTotal());
			ps.setFloat(4, t.getCost());
			ps.setFloat(5, t.getPrice());
			ps.setDate(6, DateUtil.convert(t.getCreated_time()));
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
					+ "t_order_detail where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void update(OrderDetail t) {
		
	}

	@Override
	public void updatePwd(String pwd, OrderDetail t) {
		
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public List<OrderDetail> queryAll() {
		return null;
	}

	@Override
	public List<OrderDetail> queryByPager(int pageNo, int pageSize) {
		return null;
	}

	@Override
	public List<OrderDetail> queryByName(String name) {
		return null;
	}

	@Override
	public OrderDetail queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public OrderDetail queryById(String id) {
		Connection conn = ConnUtil.getConn();
		OrderDetail deta = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_order_detail where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				deta = new OrderDetail();
				deta.setId(rs.getString("id"));
				deta.setOrder_id(rs.getString("user_id"));
				deta.setProduct_id(rs.getString("product_id"));
				deta.setTotal(rs.getInt("total"));
				deta.setCost(rs.getFloat("cost"));
				deta.setPrice(rs.getFloat("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return deta;
	}

	@Override
	public List<ProAndDetail> queryProAndDeta(String userId, int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<ProAndDetail> detaList = new ArrayList<ProAndDetail>();
		try {
			PreparedStatement ps = conn.prepareStatement("select d.*,p.name "
					+ "from t_product p, t_order_detail d, t_order o where "
					+ "d.order_id = o.id and o.user_id = ? and p.id = "
					+ "d.product_id order by d.created_time desc limit ?,?");
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProAndDetail deta = new ProAndDetail();
				deta.setId(rs.getString("id"));
				deta.setProduct_id(rs.getString("product_id"));
				deta.setOrder_id(rs.getString("order_id"));
				deta.setCost(rs.getFloat("cost"));
				deta.setTotal(rs.getInt("total"));
				deta.setName(rs.getString("name"));
				deta.setPrice(deta.getCost()/deta.getTotal());
				deta.setCreated_time(rs.getDate("created_time"));
				detaList.add(deta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return detaList;
	}

	@Override
	public int countById(String userId) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(d.id) "
					+ "from t_order_detail d,t_order o where o.user_id = ? "
					+ "and d.order_id = o.id");
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

}
