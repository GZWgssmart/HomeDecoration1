package com.gs.dao.pro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Product;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;
/**
 * 
 * @author 曾创
 *商品表DAO的实现类
 */
public class ProductDAOImpl implements ProductDAO{
	/**
	 * 主键表的id由session中获取设置到product表中
	 */
	@Override
	public void add(Product t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into "
					+ "t_product(id,name, price, sale_price, image, des, "
					+ "created_time, supply_id) values(uuid(),?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, t.getName());
			ps.setFloat(2, t.getPrice());
			ps.setFloat(3, t.getSale_price());
			ps.setString(4, t.getImage());
			ps.setString(5, t.getDes());
			ps.setDate(6, DateUtil.convert(t.getCreated_time()));
			ps.setString(7, t.getSupply_id());
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
					+ "t_product where id = ?");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void update(Product t) {
		Connection conn = ConnUtil.getConn();
		try {
			if(t.getImage()!= null) {
				PreparedStatement ps = conn.prepareStatement("update t_product set "
						+ "name=?, price=?, sale_price=?, image=?, des=? where id=?");
				ps.setString(1, t.getName());
				ps.setFloat(2, t.getPrice());
				ps.setFloat(3, t.getSale_price());
				ps.setString(4, t.getImage());
				ps.setString(5, t.getDes());
				ps.setString(6, t.getId());
				ps.execute();
			} else {
				PreparedStatement ps = conn.prepareStatement("update t_product set "
						+ "name=?, price=?, sale_price=?, des=? where id=?");
				ps.setString(1, t.getName());
				ps.setFloat(2, t.getPrice());
				ps.setFloat(3, t.getSale_price());
				ps.setString(4, t.getDes());
				ps.setString(5, t.getId());
				ps.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void updatePwd(String pwd, Product t) {
		// 商品没有此功能
	}

	@Override
	public int count() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_product");
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
	public List<Product> queryAll() {
		Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_product");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return pros;
	}

	@Override
	public List<Product> queryByPager(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_product limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return pros;
	}

	@Override
	public List<Product> queryByName(String name) {
		Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_product where name like '%" + name + "%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
		ConnUtil.close(conn);
		return pros;
	}

	@Override
	public Product queryByEmailPwd(String email, String pwd) {
		// 商品没有此功能
		return null;
	}

	@Override
	public Product queryById(String id) {
		Connection conn = ConnUtil.getConn();
		Product pro = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_product where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pro = new Product();
				pro.setId(rs.getString("id"));
				pro.setSupply_id(rs.getString("supply_id"));
				pro.setName(rs.getString("name"));
				pro.setPrice(rs.getFloat("price"));
				pro.setSale_price(rs.getFloat("sale_price"));
				pro.setImage(rs.getString("image"));
				pro.setDes(rs.getString("des"));
				pro.setCreated_time(rs.getDate("created_time"));
				pro.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return pro;
	}

	@Override
	public void updateStatus(String status, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_product "
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
	public List<Product> desTop3() {
		Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * "
					+ "from t_product where status = 'Y' limit 0,3");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return pros;
	}

	@Override
	public int countCheck() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_product where status = 'Y'");
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
	public List<Product> queryByPagerChecked(String id, int pageNo, int pageSize) {
    	Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_product where supply_id = ? order by "
					+ "created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return pros;
	}
	
	@Override
	public List<Product> queryByPagerCheck(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_product where status = 'Y' order by created_time "
					+ "desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return pros;
	}
	
	@Override
	public List<Product> queryByPagerCheck(String id, int pageNo, int pageSize) {
    	Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_product where supply_id= ? and status = 'Y' "
					+ "order by created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		ConnUtil.close(conn);
		return pros;
	}

	@Override
	public int countChecked(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_product where supply_id= ?)stu");
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
	public int countCheck(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_product where supply_id=? "
					+ "and status ='Y')stu");
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
	public List<Product> desTop3(String id) {
		Connection conn = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * "
					+ "from t_product where status = 'Y' and supply_id=? limit 0,3");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product pro = new Product();
				pro.setId(rs.getString("id"));
				pro.setSupply_id(id);
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
		ConnUtil.close(conn);
		return pros;
	}

	@Override
	public List<Product> searchCom(int pageNo, int pageSize, String com) {
		Connection con = ConnUtil.getConn();
		List<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_product where name like '"+com+"%%' and status = 'Y' limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
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
	public int searchConInt(String com) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_product where name like '"+com+"%%' and status = 'Y'");
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
	public List<Product> Comname(String search) {
		return null;
	}

}
