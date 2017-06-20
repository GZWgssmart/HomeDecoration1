package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.bean.CompanyActivity;
import com.gs.bean.CompanyCase;
import com.gs.bean.DesignerCase;
import com.gs.bean.Product;
import com.gs.bean.SupplyActivity;
import com.gs.service.com_act.CompanyActivityService;
import com.gs.service.com_act.CompanyActivityServiceImpl;
import com.gs.service.company_case.CompanyCaseBaseService;
import com.gs.service.company_case.CompanyCaseBaseServiceImpl;
import com.gs.service.des_case.DesignerCaseService;
import com.gs.service.des_case.DesignerCaseServiceImpl;
import com.gs.service.pro.ProductService;
import com.gs.service.pro.ProductServiceImpl;
import com.gs.service.sup_act.SupplyActivityService;
import com.gs.service.sup_act.SupplyActivityServiceImpl;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
import com.gs.util.PathUtil;

/**
 * 
 * @author 曾创
 *装修公司的活动，案例、建材商的商品，活动、设计师的案例的查看与状态的更改
 */
public class AdminControlServlet extends HttpServlet{

	private static final long serialVersionUID = 3420766654171968768L;
	
	private CompanyActivityService comActImpl;
	private CompanyCaseBaseService comCaseImpl;
	private ProductService proImpl;
	private SupplyActivityService supActImpl;
	private DesignerCaseService desCaseImpl;
	
	public AdminControlServlet() {
		comActImpl = new CompanyActivityServiceImpl();
		comCaseImpl = new CompanyCaseBaseServiceImpl();
		proImpl = new ProductServiceImpl();
		supActImpl = new SupplyActivityServiceImpl();
		desCaseImpl = new DesignerCaseServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("admin");
		if(obj == null) {
			resp.sendRedirect("../index/home");
		} else {
			String path = WebUtil.getUriMethod(req);
			if(path.equals("desCasepager")) {
				desPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/desCase_pager.jsp").forward(req, resp);
			} else if(path.equals("desCasestop")) {
				updateDesStatus(req, resp);
			} else if(path.equals("desCasepass")) {
				updateDesStatus(req, resp);
			} else if(path.equals("propager")) {
				proPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/pro_pager.jsp").forward(req, resp);
			} else if(path.equals("prostop")) {
				updateProStatus(req, resp);
			} else if(path.equals("propass")) {
				updateProStatus(req, resp);
			} else if(path.equals("supActpager")) {
				supActPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/supAct_pager.jsp").forward(req, resp);
			} else if(path.equals("supActstop")) {
				updateSupActStatus(req, resp);
			} else if(path.equals("supActpass")) {
				updateSupActStatus(req, resp);
			} else if(path.equals("comActpager")) {
				comActPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/comAct_pager.jsp").forward(req, resp);
			} else if(path.equals("comActstop")) {
				updateComActStatus(req, resp);
			} else if(path.equals("comActpass")) {
				updateComActStatus(req, resp);
			} else if(path.equals("comCasepager")) {
				comCasePager(req, resp);
				req.getRequestDispatcher("/classification/administrator/comCase_pager.jsp").forward(req, resp);
			} else if(path.equals("comCasestop")) {
				updateComCaseStatus(req, resp);
			} else if(path.equals("comCasepass")) {
				updateComCaseStatus(req, resp);
			}
		}
	}
	
	/**
	 * 更改装修公司案例状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateComCaseStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		CompanyCase comCase = comCaseImpl.queryById(id);
		if(comCase.getStatus().equals("Y")) {
			comCaseImpl.updateStatus("N", id);
		} else {
			comCaseImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("comCasepager");
	}
	/**
	 * 装修公司案例分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comCasePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = comCaseImpl.count();// 总建材数目
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
		List<CompanyCase> comCaseList = comCaseImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("comCaseList", comCaseList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 更改装修公司活动状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateComActStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		CompanyActivity comAct = comActImpl.queryById(id);
		if(comAct.getStatus().equals("Y")) {
			comActImpl.updateStatus("N", id);
		} else {
			comActImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("comActpager");
	}
	/**
	 * 装修公司活动分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comActPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = comActImpl.count();// 总建材数目
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
		List<CompanyActivity> comActList = comActImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("comActList", comActList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	
	/**
	 * 更改建材商活动状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateSupActStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		SupplyActivity supAct = supActImpl.queryById(id);
		if(supAct.getStatus().equals("Y")) {
			supActImpl.updateStatus("N", id);
		} else {
			supActImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("supActpager");
	}
	/**
	 * 建材商活动分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supActPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = supActImpl.count();// 总建材数目
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
		List<SupplyActivity> supActList = supActImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("supActList", supActList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}

	/**
	 * 更改商品状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateProStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Product pro = proImpl.queryById(id);
		if(pro.getStatus().equals("Y")) {
			proImpl.updateStatus("N", id);
		} else {
			proImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("propager");
	}
	/**
	 * 商品分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = proImpl.count();// 总建材数目
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
		List<Product> proList = proImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("proList", proList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	
	/**
	 * 更改业主状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateDesStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		DesignerCase desCase = desCaseImpl.queryById(id);
		if(desCase.getStatus().equals("Y")) {
			desCaseImpl.updateStatus("N", id);
		} else {
			desCaseImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("desCasepager");
	}
	/**
	 * 业主分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = desCaseImpl.count();// 总建材数目
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
		List<DesignerCase> desCaseList = desCaseImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("desCaseList", desCaseList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
}