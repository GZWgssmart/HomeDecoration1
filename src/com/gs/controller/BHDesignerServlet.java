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
import com.gs.bean.Designer;
import com.gs.bean.DesignerCase;
import com.gs.bean.DesignerCaseCol;
import com.gs.bean.DesignerCol;
import com.gs.service.des.DesignerService;
import com.gs.service.des.DesignerServiceImpl;
import com.gs.service.des_case.DesignerCaseService;
import com.gs.service.des_case.DesignerCaseServiceImpl;
import com.gs.service.des_case_col.DesignerCaseColService;
import com.gs.service.des_case_col.DesignerCaseColServiceImpl;
import com.gs.service.des_col.DesignerColService;
import com.gs.service.des_col.DesignerColServiceImpl;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
import com.gs.util.PathUtil;

/**
 * 
 * @author 曾创
 *前端显示设计师数据的servlet
 */
public class BHDesignerServlet extends HttpServlet{

	private static final long serialVersionUID = 6425307877837777882L;
	
	private DesignerService desImpl;
	private DesignerCaseService desCaseImpl;
	private DesignerColService desColImpl;
	private DesignerCaseColService desCaseColImpl;
	
	public BHDesignerServlet() {
		desImpl = new DesignerServiceImpl();
		desCaseImpl = new DesignerCaseServiceImpl();
		desColImpl = new DesignerColServiceImpl();
		desCaseColImpl = new DesignerCaseColServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String path = WebUtil.getUriMethod(req);
		if(path.equals("all_des")) {
			desTop5(req, resp);
			desCaseTop3(req, resp);
			desPager(req, resp);
			req.getRequestDispatcher("/classification/designer/all_designer.jsp").forward(req, resp);
		} else if(path.equals("desCase")) {
			desCaseShow(req, resp);
			req.getRequestDispatcher("/classification/designer/designer_case.jsp").forward(req, resp);
		} else if(path.equals("des")) {
			desShow(req, resp);
			req.getRequestDispatcher("/classification/designer/designer.jsp").forward(req, resp);
		} else if(path.equals("save_des")) {
			saveDes(req, resp);
		} else if(path.equals("save_desCase")) {
			saveDesCase(req, resp);
		} else if(path.equals("allCase")) {
			desCasePager(req, resp);
			req.getRequestDispatcher("/classification/designer/all_desCase.jsp").forward(req, resp);
		} else if(path.equals("desCaseMore")) {
			desCasePagerMore(req, resp);
			req.getRequestDispatcher("/classification/designer/designer_cases.jsp").forward(req, resp);
		}
	}
	/**
	 * 单个设计师更多案例的分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desCasePagerMore(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		int count  = desCaseImpl.countCheck(id);
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
		List<DesignerCase> desCaseList = desCaseImpl.PagerCheck(id, pageNo, pageSize);
		req.setAttribute("desCaseList", desCaseList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
		Designer des = desImpl.queryById(id);
		req.setAttribute("des", des);
	}
	/**
	 * 所有设计师案例分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desCasePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = desCaseImpl.countCheck();
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
		List<DesignerCase> desCaseList = desCaseImpl.queryByPagerCheck(pageNo, pageSize);
		req.setAttribute("desCaseList", desCaseList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 收藏设计师案例
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void saveDesCase(HttpServletRequest req, HttpServletResponse resp) 
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
		DesignerCaseCol desCaseCol = new DesignerCaseCol();
		desCaseCol.setCase_id(id);
		desCaseCol.setCustomer_id(cus.getId());
		desCaseCol.setCreated_time(Calendar.getInstance().getTime());
		desCaseColImpl.add(desCaseCol);
		out.write("{\"save\":\"成功\"}");
	}
	/**
	 * 收藏设计师
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void saveDes(HttpServletRequest req, HttpServletResponse resp) 
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
			out.write("{\"save\":\"您没有权限！请登录用户账号\"}");
			return;
		}
		DesignerCol desCol = new DesignerCol();
		desCol.setDesigner_id(id);
		desCol.setCustomer_id(cus.getId());
		desCol.setCreated_time(Calendar.getInstance().getTime());
		desColImpl.add(desCol);
		out.write("{\"save\":\"成功\"}");
	}
	/**
	 * top5精选设计师
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desTop5(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<Designer> desTopList = desImpl.desTop5();
		req.setAttribute("desTopList", desTopList);
	}
	/**
	 * top3精选设计师案例
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desCaseTop3(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<DesignerCase> desCaseTopList = desCaseImpl.desCaseTop3();
		req.setAttribute("desCaseTopList", desCaseTopList);
	}
	
	/**
	 * 所有设计师分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = desImpl.countCheck();
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
		List<Designer> desList = desImpl.queryByPagerCheck(pageNo, pageSize);
		req.setAttribute("desList", desList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 单个设计师的展示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desShow(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Designer des = desImpl.queryById(id);
		List<DesignerCase> desCaseTopList = desCaseImpl.desCaseTop3(id);
		req.setAttribute("desCaseTopList", desCaseTopList);
		req.setAttribute("des", des);
		HttpSession session  = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			String desColId = desColImpl.saveCheck(cus.getId(), id);
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
	 * 单个设计师案例的展示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desCaseShow(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		DesignerCase desCase = desCaseImpl.queryById(id);
		Designer des = desImpl.queryById(desCase.getDesigner_id());
		req.setAttribute("des", des);
		req.setAttribute("desCase", desCase);
		List<DesignerCase> desCaseTopList = desCaseImpl.desCaseTop3(des.getId());
		req.setAttribute("desCaseTopList", desCaseTopList);
		HttpSession session  = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			String desColId = desCaseColImpl.saveCheck(cus.getId(), id);
			if(desColId != null) {
				req.setAttribute("saved", true);
			} else {
				req.setAttribute("saved", false);
			}
		}
	}
}
