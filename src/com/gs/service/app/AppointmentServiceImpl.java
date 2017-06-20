package com.gs.service.app;

import java.util.List;

import com.gs.bean.Appointment;
import com.gs.dao.app.AppointmentDAO;
import com.gs.dao.app.AppointmentDAOImpl;

/**
 * 用户预约表t_appointment				
 * @author ID.LQF
 *
 */
public class AppointmentServiceImpl implements AppointmentService{

	private AppointmentDAO appointmentDAO;
	
	public AppointmentServiceImpl() {
		appointmentDAO = new AppointmentDAOImpl();
	}
	
	@Override
	public void add(Appointment t) {
		appointmentDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		appointmentDAO.deleteById(id);
	}

	@Override
	public void update(Appointment t) {
		appointmentDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, Appointment t) {
		appointmentDAO.updatePwd(pwd, t);
	}

	@Override
	public List<Appointment> queryAll() {
		return appointmentDAO.queryAll();
	}

	@Override
	public List<Appointment> queryByPager(int pageNo, int pageSize) {
		return appointmentDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public Appointment queryByEmailPwd(String email, String pwd) {
		return appointmentDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public int count() {
		return appointmentDAO.count();
	}

	@Override
	public List<Appointment> queryByName(String name) {
		return appointmentDAO.queryByName(name);
	}

	@Override
	public Appointment queryById(String id) {
		return appointmentDAO.queryById(id);
	}

	@Override
	public List<Appointment> queryByPager(String id, int pageNo, int pageSize) {
		return appointmentDAO.queryByPager(id, pageNo, pageSize);
	}

	@Override
	public int count(String id) {
		return appointmentDAO.count();
	}

	@Override
	public List<Appointment> queryByCompId(String id) {
		return appointmentDAO.queryByCompId(id);
	}

	@Override
	public int countCus(String id) {
		return appointmentDAO.countCus(id);
	}

	@Override
	public List<Appointment> queryByCusId(String id) {
		return appointmentDAO.queryByCusId(id);
	}

	@Override
	public List<Appointment> queryByCusPager(String id, int pageNo, int pageSize) {
		return appointmentDAO.queryByCusPager(id, pageNo, pageSize);
	}

	@Override
	public String appCheck(String cus_id, String com_id) {
		return appointmentDAO.appCheck(cus_id, com_id);
	}

}
