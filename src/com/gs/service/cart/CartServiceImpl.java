package com.gs.service.cart;

import java.util.List;

import com.gs.bean.Cart;
import com.gs.bean.ProductAndCart;
import com.gs.dao.cart.CartDAO;
import com.gs.dao.cart.CartDAOImpl;

public class CartServiceImpl implements CartService{
	
	private CartDAO cartImpl;
	
	public CartServiceImpl() {
		cartImpl = new CartDAOImpl();
	}

	@Override
	public void add(Cart t) {
		cartImpl.add(t);
	}

	@Override
	public void deleteById(String id) {
		cartImpl.deleteById(id);
	}

	@Override
	public void update(Cart t) {
		cartImpl.update(t);
	}

	@Override
	public void updatePwd(String pwd, Cart t) {
		cartImpl.updatePwd(pwd, t);
	}

	@Override
	public int count() {
		return cartImpl.count();
	}

	@Override
	public List<Cart> queryAll() {
		return cartImpl.queryAll();
	}

	@Override
	public List<Cart> queryByPager(int pageNo, int pageSize) {
		return cartImpl.queryByPager(pageNo, pageSize);
	}

	@Override
	public List<Cart> queryByName(String name) {
		return cartImpl.queryByName(name);
	}

	@Override
	public Cart queryByEmailPwd(String email, String pwd) {
		return cartImpl.queryByEmailPwd(email, pwd);
	}

	@Override
	public Cart queryById(String id) {
		return cartImpl.queryById(id);
	}

	@Override
	public Cart queryByProId(String proId) {
		return cartImpl.queryByProId(proId);
	}

	@Override
	public void updateTotal(Cart cart) {
		cartImpl.updateTotal(cart);
	}

	@Override
	public List<ProductAndCart> queryByPage(int pageNo, int pageSize, String userId) {
		return cartImpl.queryByPage(pageNo, pageSize, userId);
	}

	@Override
	public int countById(String userId) {
		return cartImpl.countById(userId);
	}

	@Override
	public ProductAndCart queryByCartId(String cartId) {
		return cartImpl.queryByCartId(cartId);
	}

}
