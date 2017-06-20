package com.gs.service.cart;

import java.util.List;

import com.gs.bean.Cart;
import com.gs.bean.ProductAndCart;
import com.gs.service.BaseService;
/**
 * 
 * @author 曾创
 *购物车service
 */
public interface CartService extends BaseService<String, Cart>{

	/**
	 * 通过商品id来查找购物车中是否有该物品，如有则改变数量，无，则添加
	 * @param proId
	 * @return
	 */
	public Cart queryByProId(String proId);
	/**
	 * 有商品在购物车，则该变商品数量
	 */
	public void updateTotal(Cart cart);
	/**
	 * 查出商品和购物车表的组合数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ProductAndCart> queryByPage(int pageNo, int pageSize, String userId);
	/**
	 * 通过用户id来查找购物车清单数量
	 * @param userId
	 * @return
	 */
	public int countById(String userId);
	/**
	 * 通过购物车id搜索出购物车和商品信息
	 * @param cartId
	 * @return
	 */
	public ProductAndCart queryByCartId(String cartId);
}
