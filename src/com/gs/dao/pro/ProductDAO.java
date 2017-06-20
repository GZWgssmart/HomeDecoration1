package com.gs.dao.pro;

import java.util.List;

import com.gs.bean.Product;
import com.gs.dao.BaseDAO;
/**
 * 
 * @author 曾创
 *商品表DAO
 */
public interface ProductDAO extends BaseDAO<String, Product>{
	/**
	 * 更改商品状态（默认为可用）
	 */
	public void updateStatus(String status, String id);
	/**
	 * top3精选商品
	 * @return
	 */
	public List<Product> desTop3();
	/**
	 * top3精选商品
	 * @return
	 */
	public List<Product> desTop3(String id);
	/**
	 * 有约束的商品计数
	 * @return
	 */
	public int countCheck();
	/**
	 * 有约束的商品计数
	 * @return
	 */
	public int countCheck(String id);
	/**
	 * 有约束的商品计数
	 * @return
	 */
	public int countChecked(String id);
	/**
	 * 有约束的商品分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> queryByPagerCheck(int pageNo, int pageSize);
	/**
	 * 有约束的商品分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> queryByPagerCheck(String id, int pageNo, int pageSize);
	/**
	 * 有约束的商品分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> queryByPagerChecked(String id, int pageNo, int pageSize);
	/**
	 * 搜索查询商品
	 */
	
	public List<Product> searchCom(int pageNo, int pageSize, String com);
	
	/**
	 * 搜索查询商品个数
	 */
	
	public int searchConInt(String com);
	
	/**
	 * 搜索查询商品名字
	 */
	
	public List<Product> Comname(String search);
}
