package com.gs.dao.des_case;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.DesignerCase;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 设计师案例表t_designer_case				
 * @author ID.LQF
 *
 */
public class DesignerCaseBaseDAOImpl implements DesignerCaseBaseDAO{

	@Override
	public void add(DesignerCase t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_designer_case(id,designer_id,name,plot_name,style,"
					+ "image_1,image_2,image_3,image_4,image_5,des,"
					+ "created_time) values (uuid(),?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, t.getDesigner_id());
			ps.setString(2, t.getName());
			ps.setString(3, t.getPlot_name());
			ps.setString(4, t.getStyle());
			ps.setString(5, t.getImage_1());
			ps.setString(6, t.getImage_2());
			ps.setString(7, t.getImage_3());
			ps.setString(8, t.getImage_4());
			ps.setString(9, t.getImage_5());
			ps.setString(10, t.getDes());
			ps.setDate(11, DateUtil.convert(t.getCreated_time()));
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
					+ "t_designer_case where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(DesignerCase t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_designer_case set designer_id=?,name=?,"
					+ "plot_name=?,style=?,image_1=?,image_2=?,image_3=?,"
					+ "image_4=?,image_5=?,des=? where id=?");
			ps.setString(1, t.getDesigner_id());
			ps.setString(2, t.getName());
			ps.setString(3, t.getPlot_name());
			ps.setString(4, t.getStyle());
			ps.setString(5, t.getImage_1());
			ps.setString(6, t.getImage_2());
			ps.setString(7, t.getImage_3());
			ps.setString(8, t.getImage_4());
			ps.setString(9, t.getImage_5());
			ps.setString(10, t.getDes());
			ps.setString(11, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, DesignerCase t) {
	}

	@Override
	public List<DesignerCase> queryAll() {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}

	@Override
	public List<DesignerCase> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case order by created_time desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}

	@Override
	public DesignerCase queryByEmailPwd(String email, String pwd) {
		Connection con = ConnUtil.getConn();
		DesignerCase designerCase = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case where email = ?,pwd = ?");
			ResultSet re =ps.executeQuery();
			if(re.next()){
				designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCase;
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_designer_case");
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
	public List<DesignerCase> queryByName(String name) {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case where name=?");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}

	@Override
	public void updateStatus(String check, String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_designer_case set status=? where id=?");
			ps.setString(1, check);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public DesignerCase queryById(String id) {
		Connection con = ConnUtil.getConn();
		DesignerCase designerCase = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				designerCase = new DesignerCase();
				designerCase.setId(id);
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCase;
	}

	@Override
	public List<DesignerCase> desCaseTop3() {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_designer_case where status = 'Y' limit 0,3");
			ResultSet re = ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}

	@Override
	public int countCheck() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_designer_case where status = 'Y'");
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
	public List<DesignerCase> queryByPagerCheck(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case where status = 'Y' order by "
					+ "created_time desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}

	@Override
	public int countCheck(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_designer_case where "
					+ "designer_id='"+id+"' and status ='Y')stu");
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
	public List<DesignerCase> PagerCheck(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select dc.* from "
					+ "t_designer_case dc,t_designer d where "
					+ "dc.designer_id=? and dc.status = 'Y' and "
					+ "dc.designer_id = d.id order by dc.created_time "
					+ "desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}
	
	@Override
	public int countChecked(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_designer_case where "
					+ "designer_id=?)stu");
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
	public List<DesignerCase> PagerChecked(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_designer_case where designer_id= ? order by "
					+ "created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(re.getString("designer_id"));
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}

	@Override
	public List<DesignerCase> desCaseTop3(String id) {
		Connection con = ConnUtil.getConn();
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_designer_case where status = 'Y' and "
					+ "designer_id=? limit 0,3");
			ps.setString(1, id);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				DesignerCase designerCase = new DesignerCase();
				designerCase.setId(re.getString("id"));
				designerCase.setDesigner_id(id);
				designerCase.setName(re.getString("name"));
				designerCase.setPlot_name(re.getString("plot_name"));
				designerCase.setStyle(re.getString("style"));
				designerCase.setImage_1(re.getString("image_1"));
				designerCase.setImage_2(re.getString("image_2"));
				designerCase.setImage_3(re.getString("image_3"));
				designerCase.setImage_4(re.getString("image_4"));
				designerCase.setImage_5(re.getString("image_5"));
				designerCase.setDes(re.getString("des"));
				designerCase.setCreated_time(re.getDate("created_time"));
				designerCase.setStatus(re.getString("status"));
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return designerCases;
	}
}
