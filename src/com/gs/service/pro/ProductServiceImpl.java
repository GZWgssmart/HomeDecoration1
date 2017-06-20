package com.gs.service.pro;

import java.util.List;

import com.gs.bean.Product;
import com.gs.dao.pro.ProductDAO;
import com.gs.dao.pro.ProductDAOImpl;
/**
 * 
 * @author 曾创
 *
 */
public class ProductServiceImpl implements ProductService{
	
	private ProductDAO proImpl;
	
	public ProductServiceImpl() {
		proImpl = new ProductDAOImpl();
	}
	
	@Override
	public void add(Product t) {
		proImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		proImpl.deleteById(id);
	}

	@Override
	public void update(Product t) {
		proImpl.update(t);
	}

	@Override
	public void updatePwd(String pwd, Product t) {
		proImpl.update(t);
	}

	@Override
	public int count() {
		return proImpl.count();
	}

	@Override
	public List<Product> queryAll() {
		return proImpl.queryAll();
	}

	@Override
	public List<Product> queryByPager(int pageNo, int pageSize) {
		return proImpl.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<Product> queryByName(String name) {
		return proImpl.queryByName(name);
	}

	@Override
	public Product queryByEmailPwd(String email, String pwd) {
		return proImpl.queryByEmailPwd(email, pwd);
	}

	@Override
	public Product queryById(String id) {
		return proImpl.queryById(id);
	}

	@Override
	public void updateStatus(String status, String id) {
		proImpl.updateStatus(status, id);
	}

	@Override
	public List<Product> desTop3() {
		return proImpl.desTop3();
	}

	@Override
	public int countCheck() {
		return proImpl.countCheck();
	}

	@Override
	public List<Product> queryByPagerCheck(int pageNo, int pageSize) {
		return proImpl.queryByPagerCheck(pageNo, pageSize);
	}

	@Override
	public List<Product> queryByPagerCheck(String id, int pageNo, int pageSize) {
		return proImpl.queryByPagerCheck(id, pageNo, pageSize);
	}

	@Override
	public int countCheck(String id) {
		return proImpl.countCheck(id);
	}

	@Override
	public List<Product> desTop3(String id) {
		return proImpl.desTop3(id);
	}

	@Override
	public List<Product> searchCom(int pageNo, int pageSize, String com) {
		return proImpl.searchCom(pageNo, pageSize, com);
	}

	@Override
	public int searchConInt(String com) {
		return proImpl.searchConInt(com);
	}

	@Override
	public List<Product> Comname(String search) {
		return proImpl.Comname(search);
	}

	@Override
	public List<Product> queryByPagerChecked(String id, int pageNo, int pageSize) {
		return proImpl.queryByPagerChecked(id, pageNo, pageSize);
	}

	@Override
	public int countChecked(String id) {
		return proImpl.countChecked(id);
	}

}
