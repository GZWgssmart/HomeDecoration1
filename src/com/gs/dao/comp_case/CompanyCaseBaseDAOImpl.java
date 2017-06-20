package com.gs.dao.comp_case;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.CompanyCase;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

public class CompanyCaseBaseDAOImpl implements CompanyCaseBaseDAO{

	@Override
	public void add(CompanyCase t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_company_case(id,company_id,name,plot_name,style,"
					+ "image_1,image_2,image_3,image_4,image_5,des,"
					+ "created_time) values (uuid(),?,?,?,?,?,?,?,?,?,?,?) ");
			ps.setString(1, t.getCompany_id());
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
					+ "t_company_case where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(CompanyCase t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_company_case set company_id=?,name=?,plot_name=?,"
					+ "style=?,image_1=?,image_2=?,image_3=?,image_4=?,"
					+ "image_5=?,des=? where id=?");
			ps.setString(1, t.getCompany_id());
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
	public void updatePwd(String pwd, CompanyCase t) {
	}

	@Override
	public List<CompanyCase> queryAll() {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public List<CompanyCase> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public CompanyCase queryByEmailPwd(String email, String pwd) {
		Connection con = ConnUtil.getConn();
		CompanyCase companyCase = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case where email = ?,pwd = ?");
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCase;
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from t_company_case");
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
	public List<CompanyCase> queryByName(String name) {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case where name=?");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public void updateStatus(String check, String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update "
					+ "t_company_case set status=? where id=?");
			ps.setString(1, check);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public CompanyCase queryById(String id) {
		Connection con = ConnUtil.getConn();
		CompanyCase companyCase = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_company_case where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				companyCase = new CompanyCase();
				companyCase.setId(id);
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCase;
	}

	@Override
	public List<CompanyCase> queryCompanyTop3(String id) {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_company_case where status='Y' and company_id=? limit 0,3");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public List<CompanyCase> queryByCompId(String id) {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case where company_id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public List<CompanyCase> queryCompanyTop3() {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * "
					+ "from t_company_case where status='Y' limit 0,3");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public List<CompanyCase> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_company_case where company_id = ? order by "
					+ "created_time desc limit ?,?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				CompanyCase companyCase = new CompanyCase();
				companyCase.setId(re.getString("id"));
				companyCase.setCompany_id(re.getString("company_id"));
				companyCase.setName(re.getString("name"));
				companyCase.setPlot_name(re.getString("plot_name"));
				companyCase.setStyle(re.getString("style"));
				companyCase.setImage_1(re.getString("image_1"));
				companyCase.setImage_2(re.getString("image_2"));
				companyCase.setImage_3(re.getString("image_3"));
				companyCase.setImage_4(re.getString("image_4"));
				companyCase.setImage_5(re.getString("image_5"));
				companyCase.setDes(re.getString("des"));
				companyCase.setCreated_time(re.getDate("created_time"));
				companyCase.setStatus(re.getString("status"));
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return companyCases;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_company_case where "
					+ "company_id='"+id+"')stu");
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

}
