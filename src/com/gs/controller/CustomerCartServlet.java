package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.gs.bean.Cart;
import com.gs.bean.Customer;
import com.gs.bean.Order;
import com.gs.bean.OrderDetail;
import com.gs.bean.ProAndDetail;
import com.gs.bean.Product;
import com.gs.bean.ProductAndCart;
import com.gs.service.cart.CartService;
import com.gs.service.cart.CartServiceImpl;
import com.gs.service.detail.DetailService;
import com.gs.service.detail.DetailServiceImpl;
import com.gs.service.order.OrderService;
import com.gs.service.order.OrderServiceImpl;
import com.gs.service.pro.ProductService;
import com.gs.service.pro.ProductServiceImpl;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
import com.gs.util.PathUtil;

/**
 * 
 * @author 曾创
 *购物车servlet
 */
@WebServlet(name = "CustomerCartServlet", urlPatterns = {"/cart/*"},loadOnStartup=1)
public class CustomerCartServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CartService cartImpl;
	private ProductService proImpl;
	private OrderService orderImpl;
	private DetailService detaImpl;
	
	public CustomerCartServlet() {
		cartImpl = new CartServiceImpl();
		proImpl = new ProductServiceImpl();
		orderImpl = new OrderServiceImpl();
		detaImpl = new DetailServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj == null) {
			resp.sendRedirect("../cus/loginpage");
		} else {
			String path = WebUtil.getUriMethod(req);
			if(path.equals("cart")) {
				cartListPager(req, resp);
				req.getRequestDispatcher("/classification/user/cus_cartList.jsp").forward(req, resp);
			} else if(path.equals("addCart")) {
				addCart(req, resp);
			} else if(path.equals("order")) {// 显示订单
				orderPager(req, resp);
				req.getRequestDispatcher("/classification/user/order.jsp").forward(req, resp);
			} else if(path.equals("detail")) {// 显示订单详情
				detailPager(req, resp);
				req.getRequestDispatcher("/classification/user/detail.jsp").forward(req, resp);
			} else if(path.equals("add")) {
				addTotal(req, resp);
			} else if(path.equals("lower")) {
				lowerTotal(req, resp);
			} else if(path.equals("delete")) {
				deletePro(req, resp);
			} else if(path.equals("addOrder")) {
				addOrder(req, resp);
			}
		}
	}
	/**
	 * 通过商品id查询出商品信息
	 * @param proId
	 * @return
	 */
	private Product proQueryById(String proId) {
		return proImpl.queryById(proId);
	}
	/**
	 * 订单列表后台显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void detailPager(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		Customer cus = null;
		if(obj != null) {
			cus = (Customer)obj;
		} else {
			resp.sendRedirect("../index/home");
		}
		int count  = detaImpl.countById(cus.getId());// 总建材数目
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("pageSize");// 每页显示数目
		int pageCount = (count%pageSize) == 0 ? (count/pageSize) : (count/pageSize) + 1; // 总页数
		int pageNo = 1;//页码
		String pageNoStr = req.getParameter("pageNo");
		PrintWriter out = resp.getWriter();
		if(pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo > pageCount) {
					pageNo = pageCount;
				}
			} catch (NumberFormatException e) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"页码格式化异常\"}");
				return;
			}
		}
		List<ProAndDetail> proList = detaImpl.queryProAndDeta(cus.getId(), pageNo, pageSize);
		req.setAttribute("proList", proList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 订单列表后台显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void orderPager(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		Customer cus = null;
		if(obj != null) {
			cus = (Customer)obj;
		} else {
			resp.sendRedirect("../index/home");
		}
		int count  = orderImpl.countById(cus.getId());// 总建材数目
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("pageSize");// 每页显示数目
		int pageCount = (count%pageSize) == 0 ? (count/pageSize) : (count/pageSize) + 1; // 总页数
		int pageNo = 1;//页码
		String pageNoStr = req.getParameter("pageNo");
		PrintWriter out = resp.getWriter();
		if(pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo > pageCount) {
					pageNo = pageCount;
				}
			} catch (NumberFormatException e) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"页码格式化异常\"}");
				return;
			}
		}
		List<Order> proList = orderImpl.queryByPage(pageNo, pageSize, cus.getId());
		req.setAttribute("proList", proList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 删除商品
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deletePro(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		cartImpl.deleteById(id);
		resp.sendRedirect("cart");
	}
	/**
	 * 添加商品到订单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idStr = req.getParameter("ids");
		String cost = req.getParameter("cost");
		String[] ids = idStr.split(",");
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		Object obj = session.getAttribute("customer");
		Customer cus = null;
		if(obj != null) {
			cus = (Customer)obj;
		} else {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"您没有权限！请登录用户账号\"}");
			return;
		}
		Order order = new Order();
		order.setUser_id(cus.getId());
		order.setPayed("N");
		order.setCost(Float.parseFloat(cost));
		order.setCreated_time(Calendar.getInstance().getTime());
		orderImpl.add(order);
		String tempid = orderImpl.queryByUserIdAndPayed(cus.getId()).getId();
		for(int i=0,len = ids.length; i<len; i++) {
			OrderDetail deta = new OrderDetail();
			deta.setOrder_id(tempid);
			deta.setProduct_id(ids[i]);
			Cart cart = cartImpl.queryByProId(ids[i]);
			deta.setCost(cart.getCost());
			deta.setPrice(cart.getCost()/cart.getTotal());
			deta.setTotal(cart.getTotal());
			deta.setCreated_time(Calendar.getInstance().getTime());
			detaImpl.add(deta);
		}
		orderImpl.updatePayed(cus.getId());
		for(int i=0,len = ids.length; i<len; i++) {
			cartImpl.deleteById(ids[i]);
		}
		out.write("{\"error\":\"成功\"}");
	}
	/**
	 * 添加数量
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addTotal(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		PrintWriter out = resp.getWriter();
		ProductAndCart cart = cartImpl.queryByCartId(id);
		cart.setTotal(cart.getTotal() + 1);
		cart.setCost(cart.getTotal() * cart.getSale_price());
		Cart carts = new Cart();
		carts.setId(id);
		carts.setTotal(cart.getTotal());
		carts.setCost(cart.getCost());
		cartImpl.updateTotal(carts);
		
		Map<String,String> saveMap = new HashMap<String,String>();
		saveMap.put("error", "成功");
		saveMap.put("total", ""+carts.getTotal()+"");
		saveMap.put("cost", ""+carts.getCost()+"");
		out.write(JSON.toJSONString(saveMap));
	}
	
	/**
	 * 减少数量
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void lowerTotal(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		PrintWriter out = resp.getWriter();
		ProductAndCart cart = cartImpl.queryByCartId(id);
		cart.setTotal(cart.getTotal() - 1);
		cart.setCost(cart.getTotal() * cart.getSale_price());
		if(cart.getTotal() == 0) {
			cartImpl.deleteById(id);
			Map<String,String> saveMap = new HashMap<String,String>();
			saveMap.put("error", "删除成功");
			out.write(JSON.toJSONString(saveMap));
		} else {
			Cart carts = new Cart();
			carts.setId(id);
			carts.setTotal(cart.getTotal());
			carts.setCost(cart.getCost());
			cartImpl.updateTotal(carts);
			
			Map<String,String> saveMap = new HashMap<String,String>();
			saveMap.put("error", "成功");
			saveMap.put("total", ""+carts.getTotal()+"");
			saveMap.put("cost", ""+carts.getCost()+"");
			out.write(JSON.toJSONString(saveMap));
		}
	}
	
	/**
	 * 添加商品到购物车
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pro_id = req.getParameter("id");
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		Object obj = session.getAttribute("customer");
		Customer cus = null;
		if(obj != null) {
			cus = (Customer)obj;
		} else {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"您没有权限！请登录用户账号\"}");
			return;
		}
		Cart cart = cartImpl.queryByProId(pro_id);
		if(cart == null) {
			cart = new Cart();
			cart.setProduct_id(pro_id);
			cart.setUser_id(cus.getId());
			cart.setTotal(1);
			cart.setCost(proQueryById(pro_id).getSale_price());
			cart.setCreated_time(Calendar.getInstance().getTime());
			cartImpl.add(cart);
		} else {
			float price = cart.getCost()/cart.getTotal();
			cart.setTotal(cart.getTotal()+1);
			cart.setCost(cart.getTotal()*price);
			cartImpl.updateTotal(cart);
		}
		out.write("{\"error\":\"成功\"}");
	}
	/**
	 * 购物车列表后台显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void cartListPager(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		Customer cus = null;
		if(obj != null) {
			cus = (Customer)obj;
		} else {
			resp.sendRedirect("../index/home");
		}
		int count  = cartImpl.countById(cus.getId());// 总建材数目
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("pageSize");// 每页显示数目
		int pageCount = (count%pageSize) == 0 ? (count/pageSize) : (count/pageSize) + 1; // 总页数
		int pageNo = 1;//页码
		String pageNoStr = req.getParameter("pageNo");
		PrintWriter out = resp.getWriter();
		if(pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo > pageCount) {
					pageNo = pageCount;
				}
			} catch (NumberFormatException e) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"页码格式化异常\"}");
				return;
			}
		}
		List<ProductAndCart> proList = cartImpl.queryByPage(pageNo, pageSize, cus.getId());
		req.setAttribute("proList", proList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
}
