package com.gs.dao.pro_col;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Product;
import com.gs.bean.ProductCol;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 建材收藏表t_product_col				
 * @author ID.LQF
 *
 */
public class ProductColDAOImpl implements ProductColDAO {

	@Override
	public void add(ProductCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_product_col(id,customer_id,product_id,created_time) "
					+ "values (uuid(),?,?,?) ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getProduct_id());
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
					+ "t_product_col where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(ProductCol t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_product_col "
					+ "set customer_id=?,product_id=?,created_time=? where id=? ");
			ps.setString(1, t.getCustomer_id());
			ps.setString(2, t.getProduct_id());
			ps.setDate(3, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, ProductCol t) {
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_product_col");
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
	public List<ProductCol> queryAll() {
		Connection con = ConnUtil.getConn();
		List<ProductCol> companys = new ArrayList<ProductCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_product_col");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				ProductCol companyCol = new ProductCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setProduct_id(re.getString("product_id"));
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
	public List<ProductCol> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<ProductCol> companys = new ArrayList<ProductCol>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_product_col limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				ProductCol companyCol = new ProductCol();
				companyCol.setId(re.getString("id"));
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setProduct_id(re.getString("product_id"));
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
	public List<ProductCol> queryByName(String name) {
		return null;
	}

	@Override
	public ProductCol queryByEmailPwd(String email, String pwd) {
		return null;
	}

	@Override
	public ProductCol queryById(String id) {
		Connection con = ConnUtil.getConn();
		ProductCol companyCol =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_product_col where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCol = new ProductCol();
				companyCol.setId(id);
				companyCol.setCustomer_id(re.getString("customer_id"));
				companyCol.setProduct_id(re.getString("product_id"));
				companyCol.setCreated_time(re.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCol;
	}

	@Override
	public String saveCheck(String cus_id, String pro_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_product_col where customer_id=? and product_id=?");
			ps.setString(1, cus_id);
			ps.setString(2, pro_id);
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
	public List<Product> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = con.prepareStatement("select p.* from "
					+ "t_product_col pc,t_product p where pc.customer_id=? "
					+ "and pc.product_id = p.id order by pc.created_time "
					+ "desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setId(rs.getString("id"));
				pro.setSupply_id(rs.getString("supply_id"));
				pro.setName(rs.getString("name"));
				pro.setPrice(rs.getFloat("price"));
				pro.setSale_price(rs.getFloat("sale_price"));
				pro.setImage(rs.getString("image"));
				pro.setDes(rs.getString("des"));
				pro.setCreated_time(rs.getDate("created_time"));
				pro.setStatus(rs.getString("status"));
				pros.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return pros;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_product_col where customer_id = ?");
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
					+ "t_product_col where customer_id = ? and product_id=?");
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
