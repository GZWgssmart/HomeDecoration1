package com.gs.dao.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Appointment;
import com.gs.util.ConnUtil;
import com.gs.util.DateUtil;

/**
 * 用户预约表t_appointment				
 * @author ID.LQF
 *
 */
public class AppointmentDAOImpl implements AppointmentDAO{

	@Override
	public void add(Appointment t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("insert into "
					+ "t_appointment(id,user_id,company_id,name,company_name,"
					+ "phone,plot_name,area,way,budget,created_time) values "
					+ "(uuid(),?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, t.getUser_id());
			ps.setString(2, t.getCompany_id());
			ps.setString(3, t.getCompany_name());
			ps.setString(4, t.getName());
			ps.setString(5, t.getPhone());
			ps.setString(6, t.getPlot_name());
			ps.setFloat(7, t.getArea());
			ps.setString(8, t.getWay());
			ps.setString(9, t.getBudget());
			ps.setDate(10, DateUtil.convert(t.getCreated_time()));
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
					+ "t_appointment where id=?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void update(Appointment t) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("update t_appointment"
					+ " set company_id=?,name=?,image=?,des=?,created_time=?,"
					+ "status=? where id=?");
			ps.setString(1, t.getUser_id());
			ps.setString(2, t.getCompany_id());
			ps.setString(3, t.getName());
			ps.setString(4, t.getPhone());
			ps.setString(4, t.getPlot_name());
			ps.setFloat(6, t.getArea());
			ps.setString(7, t.getWay());
			ps.setString(8, t.getBudget());
			ps.setDate(9, DateUtil.convert(t.getCreated_time()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
	}

	@Override
	public void updatePwd(String pwd, Appointment t) {
	}

	@Override
	public List<Appointment> queryAll() {
		Connection con = ConnUtil.getConn();
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				Appointment appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointments;
	}

	@Override
	public List<Appointment> queryByPager(int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment order by created_time desc limit ?,?");
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				Appointment appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointments;
	}

	@Override
	public Appointment queryByEmailPwd(String email, String pwd) {
		Connection con = ConnUtil.getConn();
		Appointment appointment = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment where email = ?,pwd = ?");
			ResultSet re =ps.executeQuery();
			if(re.next()){
				appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointment;
	}

	@Override
	public int count() {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select  count(id) "
					+ "from t_appointment");
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
	public List<Appointment> queryByName(String name) {
		Connection con = ConnUtil.getConn();
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment where name=?");
			ResultSet re =ps.executeQuery();
			while(re.next()){
				Appointment appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointments;
	}

	@Override
	public Appointment queryById(String id) {
		Connection con = ConnUtil.getConn();
		Appointment appointment = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment where id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			if(re.next()){
				appointment = new Appointment();
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointment;
	}

	@Override
	public List<Appointment> queryByPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment where company_id = ? order by "
					+ "created_time desc limit ?, ?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				Appointment appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointments;
	}

	@Override
	public int count(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_appointment where company_id'"
					+id+"')stu");
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
	public List<Appointment> queryByCompId(String id) {
		Connection con = ConnUtil.getConn();
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment where company_id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			while(re.next()){
				Appointment appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointments;
	}

	@Override
	public int countCus(String id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select count(id) "
					+ "from (select * from t_appointment where user_id='"
					+id+"')stu");
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
	public List<Appointment> queryByCusId(String id) {
		Connection con = ConnUtil.getConn();
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment where user_id=?");
			ps.setString(1, id);
			ResultSet re =ps.executeQuery();
			while(re.next()){
				Appointment appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointments;
	}

	@Override
	public List<Appointment> queryByCusPager(String id, int pageNo, int pageSize) {
		Connection con = ConnUtil.getConn();
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from "
					+ "t_appointment where user_id = ? order by created_time"
					+ " desc limit ?, ?");
			ps.setString(1, id);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet re = ps.executeQuery();
			while(re.next()){
				Appointment appointment = new Appointment();
				appointment.setId(re.getString("id"));
				appointment.setUser_id(re.getString("user_id"));
				appointment.setCompany_id(re.getString("company_id"));
				appointment.setCompany_name(re.getString("company_name"));
				appointment.setName(re.getString("name"));
				appointment.setPhone(re.getString("phone"));
				appointment.setPlot_name(re.getString("plot_name"));
				appointment.setArea(re.getFloat("area"));
				appointment.setWay(re.getString("way"));
				appointment.setBudget(re.getString("budget"));
				appointment.setCreated_time(re.getDate("created_time"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(con);
		return appointments;
	}

	@Override
	public String appCheck(String cus_id, String com_id) {
		Connection con = ConnUtil.getConn();
		try {
			PreparedStatement ps = con.prepareStatement("select id from "
					+ "t_appointment where user_id = ? and company_id = ?");
			ps.setString(1, cus_id);
			ps.setString(2, com_id);
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
