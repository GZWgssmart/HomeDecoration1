package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.bean.Admin;
import com.gs.bean.Appointment;
import com.gs.bean.Company;
import com.gs.bean.Customer;
import com.gs.bean.Designer;
import com.gs.bean.Supply;
import com.gs.service.adm.AdminService;
import com.gs.service.adm.AdminServiceImpl;
import com.gs.service.app.AppointmentService;
import com.gs.service.app.AppointmentServiceImpl;
import com.gs.service.comp.CompanyBaseService;
import com.gs.service.comp.CompanyBaseServiceImpl;
import com.gs.service.cust.CustomerService;
import com.gs.service.cust.CustomerServiceImpl;
import com.gs.service.des.DesignerService;
import com.gs.service.des.DesignerServiceImpl;
import com.gs.service.supply.SupplyBaseService;
import com.gs.service.supply.SupplyBaseServiceImpl;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
import com.gs.util.PathUtil;

/**
 * 
 * @author 曾创
 *管理员查看及更改账户状态（不包括审核）
 */
public class AdminManageServlet extends HttpServlet{

	private static final long serialVersionUID = -5416547588681727766L;
	
	private AdminService admImpl;
	private DesignerService desImpl;
	private CompanyBaseService comImpl;
	private SupplyBaseService supImpl;
	private CustomerService cusImpl;
	private AppointmentService appImpl;
	
	public AdminManageServlet() {
		admImpl = new AdminServiceImpl();
		desImpl = new DesignerServiceImpl();
		comImpl = new CompanyBaseServiceImpl();
		supImpl = new SupplyBaseServiceImpl();
		cusImpl = new CustomerServiceImpl();
		appImpl = new AppointmentServiceImpl();
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
			if(path.equals("admpager")) {
				admPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/adm_pager.jsp").forward(req, resp);
			} else if(path.equals("admstop")) {
				updateStatus(req, resp);
			} else if(path.equals("admpass")) {
				updateStatus(req, resp);
			} else if(path.equals("show")) {
				change(req, resp);
				req.getRequestDispatcher("/classification/administrator/adm_show.jsp").forward(req, resp);
			} else if(path.equals("despager")) {
				desPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/des_pager.jsp").forward(req, resp);
			} else if(path.equals("desstop")) {
				updateDesStatus(req, resp);
			} else if(path.equals("despass")) {
				updateDesStatus(req, resp);
			} else if(path.equals("compager")) {
				comPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/com_pager.jsp").forward(req, resp);
			} else if(path.equals("comstop")) {
				updateComStatus(req, resp);
			} else if(path.equals("compass")) {
				updateComStatus(req, resp);
			} else if(path.equals("suppager")) {
				supPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/sup_pager.jsp").forward(req, resp);
			} else if(path.equals("supstop")) {
				updateSupStatus(req, resp);
			} else if(path.equals("suppass")) {
				updateSupStatus(req, resp);
			} else if(path.equals("userpager")) {
				userPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/user_pager.jsp").forward(req, resp);
			} else if(path.equals("userstop")) {
				updateUserStatus(req, resp);
			} else if(path.equals("userpass")) {
				updateUserStatus(req, resp);
			} else if(path.equals("app")) {
				appPager(req, resp);
				req.getRequestDispatcher("/classification/administrator/adm_appPager.jsp").forward(req, resp);
			}
		}
	}
	/**
	 * 用户预约显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void appPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int total = appImpl.count();
		int pageSize = 6;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = req.getParameter("pageNo");
		if(pageStr != null){
			pageNo = Integer.valueOf(pageStr);
			try{
				if(pageNo <= 0){
					pageNo = 1;
				} else if (pageNo >= totalPage){
					pageNo = totalPage;
				}
			} catch(NumberFormatException e){
				
			}
		}
		List<Appointment> cusApp = appImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("cusApp", cusApp);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 更改业主状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUserStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Customer cus = cusImpl.queryById(id);
		if(cus.getStatus().equals("Y")) {
			cusImpl.updateStatus("N", id);
		} else {
			cusImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("compager");
	}
	/**
	 * 业主分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = cusImpl.count();// 总建材数目
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
		List<Customer> userList = cusImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("userList", userList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 更改公司状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateComStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Company com = comImpl.queryById(id);
		if(com.getStatus().equals("Y")) {
			comImpl.updateStatus("N", id);
		} else {
			comImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("compager");
	}
	/**
	 * 公司分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = comImpl.count();// 总建材数目
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
		List<Company> comList = comImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("comList", comList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 更改建材商状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateSupStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Supply sup = supImpl.queryById(id);
		if(sup.getStatus().equals("Y")) {
			supImpl.updateStatus("N", id);
		} else {
			supImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("suppager");
	}
	/**
	 * 建材商分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = supImpl.count();// 总建材数目
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
		List<Supply> supList = supImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("supList", supList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}

	/**
	 * 更改设计师状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateDesStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Designer des = desImpl.queryById(id);
		if(des.getStatus().equals("Y")) {
			desImpl.updateStatus("N", id);
		} else {
			desImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("despager");
	}
	/**
	 * 设计师分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = desImpl.count();// 总建材数目
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
		List<Designer> desList = desImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("desList", desList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 页面跳转传值
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void change(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Admin adm = admImpl.queryById(id);
		req.setAttribute("admin", adm);
	}
	/**
	 * 更改管理员状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Admin adm = admImpl.queryById(id);
		if(adm.getStatus().equals("Y")) {
			admImpl.updateStatus("N", id);
		} else {
			admImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("admpager");
	}
	/**
	 * 管理员分页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void admPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = admImpl.count();// 总建材数目
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
		List<Admin> admList = admImpl.queryByPager(pageNo, pageSize);
		req.setAttribute("admList", admList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
}
