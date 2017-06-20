package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.bean.Company;
import com.gs.bean.CompanyCase;
import com.gs.bean.Customer;
import com.gs.bean.Designer;
import com.gs.bean.DesignerCase;
import com.gs.bean.Product;
import com.gs.bean.Supply;
import com.gs.service.comp_case_col.CompanyCaseColService;
import com.gs.service.comp_case_col.CompanyCaseColServiceImpl;
import com.gs.service.comp_col.CompanyColService;
import com.gs.service.comp_col.CompanyColServiceImpl;
import com.gs.service.des_case_col.DesignerCaseColService;
import com.gs.service.des_case_col.DesignerCaseColServiceImpl;
import com.gs.service.des_col.DesignerColService;
import com.gs.service.des_col.DesignerColServiceImpl;
import com.gs.service.pro_col.ProductColService;
import com.gs.service.pro_col.ProductColServiceImpl;
import com.gs.service.sup_col.SupplyColService;
import com.gs.service.sup_col.SupplyColServiceImpl;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
import com.gs.util.PathUtil;

/**
 * 
 * @author 曾创
 *用户收藏列表的显示和管理
 */
public class CustomerManageServlet extends HttpServlet{

	private static final long serialVersionUID = 3482322236533857927L;
	
	private DesignerColService desColImpl;
	private DesignerCaseColService desCaseColImpl;
	private CompanyColService comColImpl;
	private CompanyCaseColService comCaseColImpl;
	private SupplyColService supColImpl;
	private ProductColService proColImpl;

	public CustomerManageServlet() {
		desColImpl = new DesignerColServiceImpl();
		desCaseColImpl = new DesignerCaseColServiceImpl();
		comColImpl = new CompanyColServiceImpl();
		comCaseColImpl = new CompanyCaseColServiceImpl();
		supColImpl = new SupplyColServiceImpl();
		proColImpl = new ProductColServiceImpl();
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
		 	if(path.equals("desSave")) {
				desSvaePager(req, resp);
				req.getRequestDispatcher("/classification/user/des_save.jsp").forward(req, resp);
			} else if(path.equals("desCaseSave")) {
				desCaseSvaePager(req, resp);
				req.getRequestDispatcher("/classification/user/desCase_save.jsp").forward(req, resp);
			} else if(path.equals("comSave")) {
				comSvaePager(req, resp);
				req.getRequestDispatcher("/classification/user/com_save.jsp").forward(req, resp);
			} else if(path.equals("comCaseSave")) {
				comCaseSvaePager(req, resp);
				req.getRequestDispatcher("/classification/user/comCase_save.jsp").forward(req, resp);
			} else if(path.equals("supSave")) {
				supSvaePager(req, resp);
				req.getRequestDispatcher("/classification/user/sup_save.jsp").forward(req, resp);
			} else if(path.equals("proSave")) {
				proSvaePager(req, resp);
				req.getRequestDispatcher("/classification/user/pro_save.jsp").forward(req, resp);
			} else if(path.equals("del_des")) {
				delDesSave(req, resp);
			} else if(path.equals("del_desCase")) {
				delDesCaseSave(req, resp);
			} else if(path.equals("del_com")) {
				delComSave(req, resp);
			} else if(path.equals("del_comCase")) {
				delComCaseSave(req, resp);
			} else if(path.equals("del_sup")) {
				delSupSave(req, resp);
			} else if(path.equals("del_pro")) {
				delProSave(req, resp);
			}
		}
	}
	/**
	 * 通过id来删除建材收藏
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delProSave(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			proColImpl.deleteById(proColImpl.queryById(cus.getId(), id));
		}
		resp.sendRedirect("proSave");
	}
	/**
	 * 建材收藏表显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proSvaePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			int count  = proColImpl.count(cus.getId());
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
			List<Product> proList = proColImpl.queryByPager(cus.getId(), pageNo, pageSize);
			req.setAttribute("proList", proList);
			req.setAttribute("pageNo", pageNo);
			req.setAttribute("pageCount", pageCount);
		}
	}
	/**
	 * 通过id来删除建材商收藏
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delSupSave(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			supColImpl.deleteById(supColImpl.queryById(cus.getId(), id));
		}
		resp.sendRedirect("supSave");
	}
	/**
	 * 装修公司收藏表显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supSvaePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			int count  = supColImpl.count(cus.getId());
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
			List<Supply> supList = supColImpl.queryByPager(cus.getId(), pageNo, pageSize);
			req.setAttribute("supList", supList);
			req.setAttribute("pageNo", pageNo);
			req.setAttribute("pageCount", pageCount);
		}
	}
	/**
	 * 通过id来删除装修公司案例收藏
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delComCaseSave(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			comCaseColImpl.deleteById(comCaseColImpl.queryById(cus.getId(), id));
		}
		resp.sendRedirect("comCaseSave");
	}
	/**
	 * 装修公司案例收藏表显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comCaseSvaePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			int count  = comCaseColImpl.count(cus.getId());
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
			List<CompanyCase> comCaseList = comCaseColImpl.queryByPager(cus.getId(), pageNo, pageSize);
			req.setAttribute("comCaseList", comCaseList);
			req.setAttribute("pageNo", pageNo);
			req.setAttribute("pageCount", pageCount);
		}
	}
	/**
	 * 通过id来删除装修公司收藏
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delComSave(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			comColImpl.deleteById(comColImpl.queryById(cus.getId(), id));
		}
		resp.sendRedirect("comSave");
	}
	/**
	 * 装修公司收藏表显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comSvaePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			int count  = comColImpl.count(cus.getId());
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
			List<Company> comList = comColImpl.queryByPager(cus.getId(), pageNo, pageSize);
			req.setAttribute("comList", comList);
			req.setAttribute("pageNo", pageNo);
			req.setAttribute("pageCount", pageCount);
		}
	}
	/**
	 * 通过id来删除设计师案例收藏
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delDesCaseSave(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			desCaseColImpl.deleteById(desCaseColImpl.queryById(cus.getId(), id));
		}
		resp.sendRedirect("desCaseSave");
	}
	/**
	 * 设计师案例收藏表显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desCaseSvaePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			int count  = desCaseColImpl.count(cus.getId());
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
			List<DesignerCase> desCaseList = desCaseColImpl.queryByPager(cus.getId(), pageNo, pageSize);
			req.setAttribute("desCaseList", desCaseList);
			req.setAttribute("pageNo", pageNo);
			req.setAttribute("pageCount", pageCount);
		}
	}
	/**
	 * 通过id来删除设计师收藏
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delDesSave(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			desColImpl.deleteById(desColImpl.queryById(cus.getId(), id));
		}
		resp.sendRedirect("desSave");
	}
	/**
	 * 设计师收藏表显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desSvaePager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			int count  = desColImpl.count(cus.getId());
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
			List<Designer> desList = desColImpl.queryByPager(cus.getId(), pageNo, pageSize);
			req.setAttribute("desList", desList);
			req.setAttribute("pageNo", pageNo);
			req.setAttribute("pageCount", pageCount);
		}
	}
}
