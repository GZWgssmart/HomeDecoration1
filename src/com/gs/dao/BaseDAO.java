package com.gs.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<PK extends Serializable, T> {

	/**
	 * 添加用户
	 * @param t
	 */
	public void add(T t);
	/**
	 * 通过id来删除
	 * @param id
	 */
	public void deleteById(PK id);
	/**
	 * 
	 * @param t
	 */
	public void update(T t);
	/**
	 * 
	 * @param pwd
	 */
	public void updatePwd(String pwd, T t);
	/**
	 * 
	 * @return
	 */
	public int count();
	/**
	 * 
	 * @return
	 */
	public List<T> queryAll();
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> queryByPager(int pageNo, int pageSize);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<T> queryByName(PK name);
	/**
	 * 
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryByEmailPwd(String email, String pwd);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(String id);
}
