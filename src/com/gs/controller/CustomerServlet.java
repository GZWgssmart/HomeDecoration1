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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gs.bean.Appointment;
import com.gs.bean.Customer;
import com.gs.service.app.AppointmentService;
import com.gs.service.app.AppointmentServiceImpl;
import com.gs.service.cust.CustomerService;
import com.gs.service.cust.CustomerServiceImpl;
import com.gs.util.FileUtil;
import com.gs.util.MD5Util;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
/**
 * 
 * @author 曾创
 *用户servlet
 */
public class CustomerServlet extends HttpServlet{

	private static final long serialVersionUID = 7182605812494079280L;
	
	private CustomerService cusImpl;
	private AppointmentService appImpl;
	
	public CustomerServlet() {
		cusImpl = new CustomerServiceImpl();
		appImpl = new AppointmentServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = WebUtil.getUriMethod(req);
		if(path.equals("loginpage")) {
			loginPage(req, resp);
		} else if(path.equals("regpage")) {
			regPage(req, resp);
		} else if(path.equals("404")) {
			req.getRequestDispatcher("/404.jsp").forward(req, resp);
		} else if(path.equals("login")) {
			login(req, resp);
		} else if(path.equals("reg")) {
			reg(req, resp);
		} else {
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("customer");
			if(obj == null) {
				resp.sendRedirect("loginpage");
			} else {
				if(path.equals("update")) {
					update(req, resp);
				} else if(path.equals("update_pwd")) {
					updatePwd(req, resp);
				} else if(path.equals("home")) {
					req.getRequestDispatcher("/classification/user/Backstage.jsp").forward(req, resp);
				} else if(path.equals("updatepage")) {
					req.getRequestDispatcher("/classification/user/update.jsp").forward(req, resp);
				} else if(path.equals("pwdpage")) {
					req.getRequestDispatcher("/classification/user/password.jsp").forward(req, resp);
				} else if(path.equals("headpage")) {
					req.getRequestDispatcher("/classification/user/head_update.jsp").forward(req, resp);
				} else if(path.equals("headupdate")) {
					updateHeadIcon(req, resp);
				} else if(path.equals("exit")) {
					session.removeAttribute("customer");
					resp.sendRedirect("loginpage");
				} else if(path.equals("app")) {
					appPager(req, resp);
					req.getRequestDispatcher("/classification/user/cus_appPager.jsp").forward(req, resp);
				} else if(path.equals("del_app")) {
					deleteAppoint(req, resp);
				}
			}
		}
	}
	/**
	 * 删除预约
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void deleteAppoint(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		String id = req.getParameter("id");
		appImpl.deleteById(id);
		resp.sendRedirect("app");
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
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		Customer cus = null;
		if(obj != null){
			cus = (Customer)obj;
		}
		int total = appImpl.countCus(cus.getId());
		int pageSize = 5;
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
		List<Appointment> cusApp = appImpl.queryByCusPager(cus.getId(), pageNo, pageSize);
		req.setAttribute("cusApp", cusApp);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 改头像
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateHeadIcon(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			String headIcon = null;
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					String name = item.getFieldName();
					if (name.equals("file")) {
						if(item.getName() == null || item.getName().trim().equals("")) {
							req.setAttribute("error", "请选择新图片");
							req.getRequestDispatcher("/classification/user/head_update.jsp").forward(req, resp);
							return;
						}
						FileUtil.save(req, item);
						headIcon = ("uploads/" + item.getName());
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("customer");
				String id = null;
				if(obj != null) {
					Customer cus = (Customer)obj;
					id = (cus.getId());
				}
				cusImpl.updateHeadIcon(headIcon, id);
				Customer customer = cusImpl.queryById(id);
				session.setAttribute("customer", customer);
				resp.sendRedirect("home");
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 用户修改密码
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatePwd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String passwords = req.getParameter("passwords");
		// 后台验证
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("")
				||password == null || password.trim().equals("")
				||passwords == null || passwords.trim().equals("")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的信息!!!\"}");
			return;
		}
		if(password.equals(passwords)) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"两次密码输入一致\"}");
			return;
		}
		Customer cus = cusImpl.queryByEmailPwd(email, MD5Util.encrypt(password));
		if(cus == null) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"密码输入错误\"}");
			return;
		}
		cusImpl.updatePwd(MD5Util.encrypt(passwords), cus);
		resp.setContentType(Constants.JSON_CONTENT_TYPE);
		out.write("{\"error\":\"修改成功\"}");
	}
	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void reg(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String passwords = req.getParameter("passwords");
		String phone = req.getParameter("phone");
		// 后台验证
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("")
				||name == null || name.trim().equals("")
				||password == null || password.trim().equals("")
				||passwords == null || passwords.trim().equals("")
				||phone == null || phone.trim().equals("")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的注册信息!!!\"}");
			return;
		}
		if(!password.equals(passwords)) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"两次密码输入不一致\"}");
			return;
		}
		if(cusImpl.queryEmail(email) == true) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"该邮箱已被使用\"}");
			return;
		}
		if(phone.length() != 11) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"电话输入错误！\"}");
			return;
		}
		Customer cus = new Customer();
		cus.setEmail(email);
		cus.setPassword(MD5Util.encrypt(password));
		cus.setName(name);
		cus.setHeadIcon("uploads/12.jpg");
		cus.setPhone(phone);
		cus.setCreated_time(Calendar.getInstance().getTime());
		cusImpl.add(cus);
		out.write("{\"error\":\"注册成功\"}");
	}
	/**
	 * 用户修改个人资料
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String plot = req.getParameter("plot");
		String phone = req.getParameter("phone");
		// 后台验证
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("")
				||name == null || name.trim().equals("")
				||address == null || address.trim().equals("")
				||plot == null || plot.trim().equals("")
				||phone == null || phone.trim().equals("")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的信息!!!\"}");
			return;
		}
		Customer cues = cusImpl.queryById(id);
		if(!cues.getEmail().equals(email)) {
			if(cusImpl.queryEmail(email) == true) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"该邮箱已被使用\"}");
				return;
			}
		}
		if(phone.length() != 11) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"电话输入错误！\"}");
			return;
		}
		Customer cus = new Customer();
		cus.setHeadIcon(cues.getHeadIcon());
		cus.setEmail(email);
		cus.setName(name);
		cus.setPhone(phone);
		cus.setPlot_name(plot);
		cus.setAddress(address);
		cus.setId(id);
		cusImpl.update(cus);
		Customer customer = cusImpl.queryById(id);
		HttpSession session = req.getSession();
		session.setAttribute("customer", customer);
		out.write("{\"error\":\"修改成功\"}");
	}
	/**
	 * 转到登录界面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void loginPage(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/classification/user/login.jsp").forward(req, resp);
	}
	/**
	 * 转到注册界面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void regPage(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/classification/user/registered.jsp").forward(req, resp);
	}
	/**
	 * 用户登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("password");
		PrintWriter out = resp.getWriter();
		// 后台验证
		if(email == null || email.trim().equals("") 
				|| pwd == null || pwd.trim().equals("")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入邮箱和密码！\"}");
			return;
		}
		Customer cus = cusImpl.queryByEmailPwd(email, MD5Util.encrypt(pwd));
		if(cus == null) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"邮箱密码输入错误！\"}");
			return;
		}
		HttpSession session = req.getSession();
		if(session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
		} else if(session.getAttribute("customer") != null) {
			session.removeAttribute("customer");
		} else if(session.getAttribute("supply") != null) {
			session.removeAttribute("supply");
		} else if(session.getAttribute("company") != null) {
			session.removeAttribute("company");
		} else if(session.getAttribute("designer") != null) {
			session.removeAttribute("designer");
		}
		session.setAttribute("customer", cus);
		cusImpl.updateLoginTime(Calendar.getInstance().getTime(), cus);
		// 登录成功，转到个人中心
		out.write("{\"error\":\"登录成功\"}");
	}
}
