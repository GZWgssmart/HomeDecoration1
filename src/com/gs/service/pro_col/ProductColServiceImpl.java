package com.gs.service.pro_col;

import java.util.List;

import com.gs.bean.Product;
import com.gs.bean.ProductCol;
import com.gs.dao.pro_col.ProductColDAO;
import com.gs.dao.pro_col.ProductColDAOImpl;

public class ProductColServiceImpl implements ProductColService{

	private ProductColDAO productColDAO;
	
	public ProductColServiceImpl() {
		productColDAO = new ProductColDAOImpl();
	}
	@Override
	public void add(ProductCol t) {
		productColDAO.add(t);
	}

	@Override
	public void deleteById(String id) {
		productColDAO.deleteById(id);
	}

	@Override
	public void update(ProductCol t) {
		productColDAO.update(t);
	}

	@Override
	public void updatePwd(String pwd, ProductCol t) {
		productColDAO.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return productColDAO.count();
	}

	@Override
	public List<ProductCol> queryAll() {
		return productColDAO.queryAll();
	}

	@Override
	public List<ProductCol> queryByPager(int pageNo, int pageSize) {
		return productColDAO.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<ProductCol> queryByName(String name) {
		return productColDAO.queryByName(name);
	}

	@Override
	public ProductCol queryByEmailPwd(String email, String pwd) {
		return productColDAO.queryByEmailPwd(email, pwd);
	}

	@Override
	public ProductCol queryById(String id) {
		return productColDAO.queryById(id);
	}
	@Override
	public String saveCheck(String cus_id, String pro_id) {
		return productColDAO.saveCheck(cus_id, pro_id);
	}
	@Override
	public List<Product> queryByPager(String id, int pageNo, int pageSize) {
		return productColDAO.queryByPager(id, pageNo, pageSize);
	}
	@Override
	public int count(String id) {
		return productColDAO.count(id);
	}
	@Override
	public String queryById(String cus_id, String des_id) {
		return productColDAO.queryById(cus_id, des_id);
	}

}
