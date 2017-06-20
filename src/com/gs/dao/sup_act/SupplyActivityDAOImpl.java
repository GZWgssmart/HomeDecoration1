package com.gs.dao.sup_act;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.SupplyActivity;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;
/**
 * 
 * @author 曾创
 *建材商活动表DAO实现类
 */
public class SupplyActivityDAOImpl implements SupplyActivityDAO{

	@Override
	public void add(SupplyActivity t) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into "
					+ "t_supply_activity(id,supply_id, name, image, des, "
					+ "created_time) values(uuid(),?, ?, ?, ?, ?)");
			ps.setString(1, t.getSupply_id());
			ps.setString(2, t.getName());
			ps.setString(3, t.getImage());
			ps.setString(4, t.getDes());
			ps.setDate(5, DateUtil.convert(t.getCreated_time()));
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
					+ "t_supply_activity where id = ?");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void update(SupplyActivity t) {
		Connection conn = ConnUtil.getConn();
		try {
			if(t.getImage() != null) {
				PreparedStatement ps = conn.prepareStatement("update "
						+ "t_supply_activity set des=?, name=?, image=? "
						+ "where id=?");
				ps.setString(1, t.getDes());
				ps.setString(2, t.getName());
				ps.setString(3, t.getImage());
				ps.setString(4, t.getId());
				ps.execute();
			} else {
				PreparedStatement ps = conn.prepareStatement("update "
						+ "t_supply_activity set des=?, name=? where id=?");
				ps.setString(1, t.getDes());
				ps.setString(2, t.getName());
				ps.setString(3, t.getId());
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public void updatePwd(String pwd, SupplyActivity t) {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
	}

	@Override
	public int count() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_supply_activity");
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
	public List<SupplyActivity> queryAll() {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
		return null;
	}

	@Override
	public List<SupplyActivity> queryByPager(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<SupplyActivity> acts = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_supply_activity limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SupplyActivity act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(rs.getString("supply_id"));
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return acts;
	}

	@Override
	public List<SupplyActivity> queryByName(String name) {
		Connection conn = ConnUtil.getConn();
		List<SupplyActivity> acts = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_supply_activity where name like '%" + name + "%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SupplyActivity act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(rs.getString("supply_id"));
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return acts;
	}

	@Override
	public SupplyActivity queryByEmailPwd(String email, String pwd) {
		Connection conn = ConnUtil.getConn();
		
		ConnUtil.close(conn);
		return null;
	}

	@Override
	public void updateStatus(String status, String id) {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update "
					+ "t_supply_activity set status=? where id=?");
			ps.setString(1, status);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
	}

	@Override
	public SupplyActivity queryById(String id) {
		Connection conn = ConnUtil.getConn();
		SupplyActivity act = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_supply_activity where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(rs.getString("supply_id"));
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return act;
	}

	@Override
	public List<SupplyActivity> actTop3() {
		Connection conn = ConnUtil.getConn();
		List<SupplyActivity> acts = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * "
					+ "from t_supply_activity where status = 'Y' limit 0,3");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SupplyActivity act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(rs.getString("supply_id"));
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return acts;
	}

	@Override
	public int countCheck() {
		Connection conn = ConnUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) "
					+ "from t_supply_activity where status = 'Y'");
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
	public List<SupplyActivity> queryByPagerCheck(int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<SupplyActivity> acts = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_supply_activity where status = 'Y' limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SupplyActivity act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(rs.getString("supply_id"));
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return acts;
	}

	@Override
	public int countCheck(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_supply_activity where "
					+ "supply_id=? and status ='Y')stu");
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
	public List<SupplyActivity> PagerCheck(String id, int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<SupplyActivity> acts = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_supply_activity where status = 'Y' and "
					+ "supply_id = ? order by created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SupplyActivity act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(rs.getString("supply_id"));
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return acts;
	}

	@Override
	public List<SupplyActivity> actTop3(String id) {
		Connection conn = ConnUtil.getConn();
		List<SupplyActivity> acts = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * "
					+ "from t_supply_activity where status = 'Y' and "
					+ "supply_id=? limit 0,3");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SupplyActivity act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(id);
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return acts;
	}

	@Override
	public int countChecked(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_supply_activity where "
					+ "supply_id=?)stu");
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
	public List<SupplyActivity> PagerChecked(String id, int pageNo, int pageSize) {
		Connection conn = ConnUtil.getConn();
		List<SupplyActivity> acts = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from "
					+ "t_supply_activity where supply_id=? order by "
					+ "created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SupplyActivity act = new SupplyActivity();
				act.setId(rs.getString("id"));
				act.setName(rs.getString("name"));
				act.setSupply_id(rs.getString("supply_id"));
				act.setStatus(rs.getString("status"));
				act.setImage(rs.getString("image"));
				act.setCreated_time(rs.getDate("created_time"));
				act.setDes(rs.getString("des"));
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(conn);
		return acts;
	}

}
