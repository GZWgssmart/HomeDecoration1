package com.gs.service.app;

import java.util.List;

import com.gs.bean.Appointment;
import com.gs.service.BaseService;

/**
 * 用户预约表t_appointment				
 * @author ID.LQF
 *
 */
public interface AppointmentService extends BaseService<String, Appointment>{

	/**
	 * 根据公司的id来获取他的所有预约进而分页
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Appointment> queryByPager(String id, int pageNo, int pageSize);
	
	/**
	 * 根据id查找页数
	 * @param id
	 * @return
	 */
	public int count(String id);
	
	/**
	 * 根据公司的id来获取他的所有预约
	 * @param id
	 * @return
	 */
	public List<Appointment> queryByCompId(String id);
	/**
	 * 根据id查找页数
	 * @param id
	 * @return
	 */
	public int countCus(String id);
	
	/**
	 * 根据公司的id来获取他的所有预约
	 * @param id
	 * @return
	 */
	public List<Appointment> queryByCusId(String id);
	/**
	 * 根据公司的id来获取他的所有预约进而分页
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Appointment> queryByCusPager(String id, int pageNo, int pageSize);
	/**
	 * 通过id判断是否已预约
	 * @param cus_id
	 * @param com_id
	 * @return
	 */
	public String appCheck(String cus_id, String com_id);
	
}
