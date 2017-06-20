package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.bean.Customer;
import com.gs.bean.Product;
import com.gs.bean.ProductCol;
import com.gs.bean.Supply;
import com.gs.bean.SupplyActivity;
import com.gs.bean.SupplyCol;
import com.gs.service.pro.ProductService;
import com.gs.service.pro.ProductServiceImpl;
import com.gs.service.pro_col.ProductColService;
import com.gs.service.pro_col.ProductColServiceImpl;
import com.gs.service.sup_act.SupplyActivityService;
import com.gs.service.sup_act.SupplyActivityServiceImpl;
import com.gs.service.sup_col.SupplyColService;
import com.gs.service.sup_col.SupplyColServiceImpl;
import com.gs.service.supply.SupplyBaseService;
import com.gs.service.supply.SupplyBaseServiceImpl;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
import com.gs.util.PathUtil;

/**
 * 
 * @author 曾创
 *建材商前端页面显示
 */
public class BHSupplyServlet extends HttpServlet{

	private static final long serialVersionUID = -7231562242262552910L;
	
	private SupplyBaseService supImpl;
	private SupplyActivityService actImpl;
	private ProductService proImpl;
	private SupplyColService supColImpl;
	private ProductColService proColImpl;
	
	public BHSupplyServlet() {
		supImpl = new SupplyBaseServiceImpl();
		actImpl = new SupplyActivityServiceImpl();
		proImpl = new ProductServiceImpl();
		supColImpl = new SupplyColServiceImpl();
		proColImpl = new ProductColServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String path = WebUtil.getUriMethod(req);
		if(path.equals("all_sup")) {
			supTop6(req, resp);
			supActTop3(req, resp);
			proTop3(req, resp);
			supPager(req, resp);
			req.getRequestDispatcher("/classification/businessman/all_supply.jsp").forward(req, resp);
		} else if(path.equals("sup")) {
			supShow(req, resp);
			req.getRequestDispatcher("/classification/businessman/single_supply.jsp").forward(req, resp);
		} else if(path.equals("supAct")) {
			supActShow(req, resp);
			req.getRequestDispatcher("/classification/businessman/supply_act.jsp").forward(req, resp);
		} else if(path.equals("allAct")) {
			supActPager(req, resp);
			req.getRequestDispatcher("/classification/businessman/all_supAct.jsp").forward(req, resp);
		} else if(path.equals("allPro")) {
			proPager(req, resp);
			req.getRequestDispatcher("/classification/businessman/all_product.jsp").forward(req, resp);
		} else if(path.equals("pro")) {
			proShow(req, resp);
			supActTop3(req, resp);
			proTop3(req, resp);
			req.getRequestDispatcher("/classification/businessman/supply_product.jsp").forward(req, resp);
		} else if(path.equals("supActMore")) {
			supActPagerMore(req, resp);
			req.getRequestDispatcher("/classification/businessman/supply_actives.jsp").forward(req, resp);
		} else if(path.equals("proMore")) {
			proPagerMore(req, resp);
			req.getRequestDispatcher("/classification/businessman/supply_products.jsp").forward(req, resp);
		} else if(path.equals("save_sup")) {
			saveSup(req, resp);
		} else if(path.equals("save_pro")) {
			savePro(req, resp);
		}
	}
	/**
	 * 收藏建材
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void savePro(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
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
		ProductCol proCol = new ProductCol();
		proCol.setCustomer_id(cus.getId());
		proCol.setProduct_id(id);
		proCol.setCreated_time(Calendar.getInstance().getTime());
		proColImpl.add(proCol);
		out.write("{\"save\":\"成功\"}");
	}
	/**
	 * 收藏建材商
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void saveSup(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
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
		SupplyCol supCol = new SupplyCol();
		supCol.setCustomer_id(cus.getId());
		supCol.setSupply_id(id);
		supCol.setCreated_time(Calendar.getInstance().getTime());
		supColImpl.add(supCol);
		out.write("{\"save\":\"成功\"}");
	}
	/**
	 * 单个建材商更多建材的分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proPagerMore(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		int count  = proImpl.countCheck(id);
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("desCasePageSize");// 每页显示数目
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
		List<Product> proList = proImpl.queryByPagerCheck(id, pageNo, pageSize);
		req.setAttribute("proList", proList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
		Supply sup = supImpl.queryById(id);
		req.setAttribute("sup", sup);
	}
	/**
	 * 单个建材商更多活动的分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supActPagerMore(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		int count  = actImpl.countCheck(id);
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("desCasePageSize");// 每页显示数目
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
		List<SupplyActivity> supActList = actImpl.PagerCheck(id, pageNo, pageSize);
		req.setAttribute("supActList", supActList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
		Supply sup = supImpl.queryById(id);
		req.setAttribute("sup", sup);
	}
	/**
	 * 单个建材的展示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proShow(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Product pro = proImpl.queryById(id);
		Supply sup = supImpl.queryById(pro.getSupply_id());
		req.setAttribute("sup", sup);
		req.setAttribute("pro", pro);
		HttpSession session  = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			String desColId = proColImpl.saveCheck(cus.getId(), id);
			if(desColId != null) {
				req.setAttribute("saved", true);
			} else {
				req.setAttribute("saved", false);
			}
		} else {
			req.setAttribute("saved", false);
		}
	}
	/**
	 * 所有建材分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = proImpl.countCheck();
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("desCasePageSize");// 每页显示数目
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
		List<Product> proList = proImpl.queryByPagerCheck(pageNo, pageSize);
		req.setAttribute("proList", proList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 所有建材活动分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supActPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = actImpl.countCheck();
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("desCasePageSize");// 每页显示数目
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
		List<SupplyActivity> supActList = actImpl.queryByPagerCheck(pageNo, pageSize);
		req.setAttribute("supActList", supActList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 单个建材商活动的展示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supActShow(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		SupplyActivity supAct = actImpl.queryById(id);
		Supply sup = supImpl.queryById(supAct.getSupply_id());
		req.setAttribute("sup", sup);
		req.setAttribute("supAct", supAct);
		List<SupplyActivity> actTopList = actImpl.actTop3(sup.getId());
		req.setAttribute("actTopList", actTopList);
		List<Product> proTopList = proImpl.desTop3(sup.getId());
		req.setAttribute("proTopList", proTopList);
	}
	/**
	 * 单个建材商的展示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supShow(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Supply sup = supImpl.queryById(id);
		List<SupplyActivity> actTopList = actImpl.actTop3(id);
		req.setAttribute("actTopList", actTopList);
		List<Product> proTopList = proImpl.desTop3(id);
		req.setAttribute("proTopList", proTopList);
		req.setAttribute("sup", sup);
		HttpSession session  = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			String desColId = supColImpl.saveCheck(cus.getId(), id);
			if(desColId != null) {
				req.setAttribute("saved", true);
			} else {
				req.setAttribute("saved", false);
			}
		}
	}
	/**
	 * top5建材商
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supTop6(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<Supply> supTopList = supImpl.desTop6();
		req.setAttribute("supTopList", supTopList);
	}
	/**
	 * top3精选建材商活动
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supActTop3(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<SupplyActivity> actTopList = actImpl.actTop3();
		req.setAttribute("actTopList", actTopList);
	}
	/**
	 * top3精选建材
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proTop3(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<Product> proTopList = proImpl.desTop3();
		req.setAttribute("proTopList", proTopList);
	}
	/**
	 * 所有建材商分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = supImpl.countCheck();
		GetPropUtil prop = new GetPropUtil(PathUtil.getSRCPath(req) + "com/gs/prop/pager.properties");
		int pageSize = prop.getInt("BHpageSize");// 每页显示数目
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
		List<Supply> supList = supImpl.queryByPagerCheck(pageNo, pageSize);
		req.setAttribute("supList", supList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
}
