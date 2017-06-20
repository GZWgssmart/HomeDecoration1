package com.gs.dao.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Cart;
import com.gs.bean.ProductAndCart;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;
/**
 * 
 * @author 曾创
 *购物车实现类
 */
public class CartDAOImpl implements CartDAO{

	@Override
	public void add(Cart t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_cart"
					+ "(id,user_id, product_id, total, cost, creat_time) "
					+ "values(uuid(), ?, ?, ?, ?, ?);");
			ps.setString(1, t.getUser_id());
			ps.setString(2, t.getProduct_id());
			ps.setInt(3, t.getTotal());
			ps.setFloat(4, t.getCost());
			ps.setDate(5, DateUtil.convert(t.getCreat_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}
	
	/**
	 * 通过商品id删除购物车信息
	 */
	@Override
	public void deleteById(String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from t_cart "
					+ "where product_id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void update(Cart t) {
		
	}

	@Override
	public void updatePwd(String pwd, Cart t) {
		
	}

	@Override
	public int count() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_cart");
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
	public List<Cart> queryAll() {
		return null;
	}

	@Override
	public List<ProductAndCart> queryByPage(int pageNo, int pageSize,String id) {
		Connection conn = ConnUtil.getConn();
		List<ProductAndCart> carts = new ArrayList<ProductAndCart>();
		try {
			PreparedStatement ps = conn.prepareStatement("select c.*,s.id,"
					+ "s.name,p.* from t_cart c,t_supply s, t_product p "
					+ "where c.product_id = p.id and p.supply_id = s.id and "
					+ "c.user_id = ? order by c.creat_time desc limit ?, ?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProductAndCart cart = new ProductAndCart();
				cart.setId(rs.getString("id"));
				cart.setUser_id(rs.getString("user_id"));
				cart.setProduct_id(rs.getString("product_id"));
				cart.setTotal(rs.getInt("total"));
				cart.setCost(rs.getFloat("cost"));
				cart.setName(rs.getString(11));
				cart.setSale_price(rs.getFloat("sale_price"));
				cart.setCreat_time(rs.getDate("creat_time"));
				cart.setSup_id(rs.getString(7));
				cart.setSup_name(rs.getString(8));
				cart.setStatus(rs.getString("status"));
				carts.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return carts;
	}

	@Override
	public List<Cart> queryByName(String name) {
		return null;
	}

	@Override
	public Cart queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public Cart queryById(String id) {
		Connection conn = ConnUtil.getConn();
		Cart cart = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_cart where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setUser_id(rs.getString("user_id"));
				cart.setProduct_id(rs.getString("product_id"));
				cart.setTotal(rs.getInt("total"));
				cart.setCost(rs.getFloat("cost"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cart;
	}

	@Override
	public Cart queryByProId(String proId) {
		Connection conn = ConnUtil.getConn();
		Cart cart = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_cart where product_id = ?");
			ps.setString(1, proId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setUser_id(rs.getString("user_id"));
				cart.setProduct_id(rs.getString("product_id"));
				cart.setTotal(rs.getInt("total"));
				cart.setCost(rs.getFloat("cost"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cart;
	}

	@Override
	public void updateTotal(Cart cart) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_cart set"
					+ " total = ?,cost=? where id= ?");
			ps.setInt(1, cart.getTotal());
			ps.setFloat(2, cart.getCost());
			ps.setString(3, cart.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public List<Cart> queryByPager(int pageNo, int pageSize) {
		return null;
	}

	@Override
	public int countById(String userId) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_cart where user_id = ?");
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
	public ProductAndCart queryByCartId(String cartId) {
		Connection conn = ConnUtil.getConn();
		ProductAndCart cart = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_cart c, t_product p where c.id= ? and c.product_id "
					+ "= p.id;");
			ps.setString(1, cartId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cart = new ProductAndCart();
				cart.setId(rs.getString("id"));
				cart.setUser_id(rs.getString("user_id"));
				cart.setProduct_id(rs.getString("product_id"));
				cart.setTotal(rs.getInt("total"));
				cart.setCost(rs.getFloat("cost"));
				cart.setName(rs.getString("name"));
				cart.setSale_price(rs.getFloat("sale_price"));
				cart.setCreat_time(rs.getDate("creat_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return cart;
	}

}
