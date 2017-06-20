package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
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
import com.gs.bean.Company;
import com.gs.service.app.AppointmentService;
import com.gs.service.app.AppointmentServiceImpl;
import com.gs.service.comp.CompanyBaseService;
import com.gs.service.comp.CompanyBaseServiceImpl;
import com.gs.util.DateUtil;
import com.gs.util.FileUtil;
import com.gs.util.MD5Util;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;

/**
 * 装修公司表t_company				
 * @author ID.LQF
 *
 */
public class CompanyServlet extends HttpServlet{

	private static final long serialVersionUID = -2279547774949318737L;

	private CompanyBaseService companyService;
	private AppointmentService appointmentService;
	
	public CompanyServlet(){
		companyService = new CompanyBaseServiceImpl();
		appointmentService = new AppointmentServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		if(method.equals("login")) {
			login(req, resp);
		} else if (method.equals("loginpage")) {
			loginPage(req, resp);
		} else if (method.equals("register")) {
			register(req, resp);
		} else if (method.equals("regpage")) {
			registerPage(req, resp);
		} else if (method.equals("wait")) {
			wait(req, resp);
		} else {
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("company");
			if(obj == null) {
				resp.sendRedirect("loginpage");
			} else {
				if (method.equals("home")) {
					home(req, resp);
				} else if (method.equals("update")) {
					update(req, resp);
				} else if (method.equals("updatepage")) {
					updatePage(req, resp);
				} else if (method.equals("pwd")) {
					pwd(req, resp);
				} else if (method.equals("pwdpage")) {
					pwdPage(req, resp);
				} else if(method.equals("headpage")) {
					req.getRequestDispatcher("/classification/company/head_update.jsp").forward(req, resp);
				} else if(method.equals("headupdate")) {
					updateLogo(req, resp);
				} else if(method.equals("exit")) {
					session.removeAttribute("company");
					resp.sendRedirect("loginpage");
				} else if(method.equals("appoint")) {
					appoint(req,resp);
					req.getRequestDispatcher("/classification/company/appoint_record.jsp").forward(req, resp);
				} else if(method.equals("delete_appoint")) {
					deleteAppoint(req,resp);
					appoint(req,resp);
					req.getRequestDispatcher("/classification/company/appoint_record.jsp").forward(req, resp);
				}
			}
		}
	}
	
	/**
	 * 改头像
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateLogo(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			String logo = null;
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					String name = item.getFieldName();
					if (name.equals("file")) {
						if(item.getName() == null || item.getName().trim().equals("")) {
							req.setAttribute("error", "请选择新图片");
							req.getRequestDispatcher("/classification/company/head_update.jsp").forward(req, resp);
							return;
						}
						FileUtil.save(req, item);
						logo = ("uploads/" + item.getName());
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("company");
				String id = null;
				if(obj != null) {
					Company com = (Company)obj;
					id = (com.getId());
				}
				companyService.updateLogo(logo, id);
				Company coms = companyService.queryById(id);
				session.setAttribute("company", coms);
				resp.sendRedirect("home");
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void wait(HttpServletRequest req ,HttpServletResponse resp) throws IOException, ServletException{
		req.getRequestDispatcher("/classification/company/wait.jsp").forward(req, resp);
	}
	
	public void login(HttpServletRequest req ,HttpServletResponse resp) throws IOException{
		String email = req.getParameter("email");
		String pwd = req.getParameter("password");
		PrintWriter out = resp.getWriter();
		// 后台验证
		if(email == null || email.trim().equals("") 
				|| pwd == null || pwd.trim().equals("")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入邮箱和密码！\"}");
			return;
		} else {
			Company comp = companyService.queryByEmailPwd(email, MD5Util.encrypt(pwd));
			if(comp==null) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"邮箱密码输入错误！\"}");
				return;
			}
			if(comp.getChecked().equals("N")) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"等待审核\"}");
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
			session.setAttribute("company", comp);
			companyService.updateLoginTime(Calendar.getInstance().getTime(), comp);
			out.write("{\"error\":\"登入成功\"}");
		}
	}
	
	public void loginPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/classification/company/login.jsp").forward(req, resp);
	}
	
	public void register(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String compname = req.getParameter("compname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String passwords = req.getParameter("passwords");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("") || name==null || name.trim().equals("") || compname==null || compname.trim().equals("") || phone==null || phone.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的注册信息!!!\"}");
			return;
		} else {
			Company comp = new Company();
			comp.setName(compname);
			if(companyService.queryByEmail(email)==true){
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"此邮箱已被注册!!!\"}");
				return;
			}
			if(phone.length() != 11) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"电话输入错误！\"}");
				return;
			}
			comp.setEmail(email);
			comp.setPassword(MD5Util.encrypt(password));
			comp.setPrincipal(name);
			comp.setLogo("uploads/12.jpg");
			comp.setPhone(phone);
			comp.setCreated_time(Calendar.getInstance().getTime());
			comp.setChecked_time(Calendar.getInstance().getTime());
			comp.setLast_login_time(Calendar.getInstance().getTime());
			if( !password.equals(passwords)){
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"你的密码不一致，请重新输入\"}");
				return;
			} else {
				comp.setPassword(MD5Util.encrypt(password));
			}
			companyService.add(comp);
			out.write("{\"error\":\"注册成功\"}");
		}
	}
	
	public void registerPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/classification/company/registered.jsp").forward(req, resp);
	}
	
	public void home(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/classification/company/Backstage.jsp").forward(req, resp);
	}
	
	public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String compname = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String principal = req.getParameter("principal");
		String opendate = req.getParameter("opendate");
		String longitude = req.getParameter("longitude");
		String latitude = req.getParameter("latitude");
		String tel = req.getParameter("tel");
		String des = req.getParameter("des");
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("") || principal==null || principal.trim().equals("") || compname==null || compname.trim().equals("") || phone==null || phone.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的注册信息!!!\"}");
			return;
		} else {
			Company comp = new Company();
			comp.setId(id);
			comp.setName(compname);
			Company c = companyService.queryById(id);
			if(!c.getEmail().equals(email)) {
				if(companyService.queryByEmail(email) == true) {
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
			comp.setLogo(c.getLogo());
			comp.setEmail(email);
			comp.setAddress(address);
			comp.setPhone(phone);
			if(tel == null || tel.trim().equals("")) {
				comp.setTel(null);
			} else {
				if(tel.length() != 11) {
					resp.setContentType(Constants.JSON_CONTENT_TYPE);
					out.write("{\"error\":\"电话输入错误！\"}");
					return;
				}
				comp.setTel(tel);
			}
			if(des.trim().equals("") || des == null) {
				comp.setDes("这个人很懒~没有添加简介");
			} else {
				comp.setDes(des);
			}
			comp.setPrincipal(principal);
			comp.setLongitude(Float.valueOf(longitude));
			comp.setLatitude(Float.valueOf(latitude));
			comp.setCreated_time(Calendar.getInstance().getTime());
			comp.setChecked_time(Calendar.getInstance().getTime());
			comp.setLast_login_time(Calendar.getInstance().getTime());
			Date now = Calendar.getInstance().getTime();
			Date open = DateUtil.parseDate(opendate, Constants.USE_PATTERN);
			if(now.getTime()<open.getTime()){
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"请输入正确的成立时间!!!\"}");
				return;
			} else {
				comp.setOpenDate(DateUtil.parseDate(opendate, Constants.USE_PATTERN));
			}
			companyService.update(comp);
			Company com = companyService.queryById(id);
			HttpSession session = req.getSession();
			session.setAttribute("company", com);
			out.write("{\"error\":\"更改成功\"}");
		}
	}
	
	public void updatePage(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.getRequestDispatcher("/classification/company/update.jsp").forward(req, resp);
	}
	
	public void pwdPage(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.getRequestDispatcher("/classification/company/password.jsp").forward(req, resp);
	}
	
	public void pwd(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		String password = req.getParameter("password");
		String passwords = req.getParameter("passwords");
		String email = req.getParameter("email");
		PrintWriter out = resp.getWriter();
		if(password == null || password.trim().equals("") || passwords == null || passwords.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的密码!!!\"}");
			return;
		} else {
			Company company = companyService.queryByEmailPwd(email, MD5Util.encrypt(password));
			company.setPassword(passwords);
			companyService.updatePwd(MD5Util.encrypt(passwords), company);
			out.write("{\"error\":\"密码更改成功\"}");
		}
	}
	
	/**
	 * 预约管理的分页显示
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void appoint(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("company");
		Company comp = null;
		if(obj != null){
			comp = (Company)obj;
		}
		int total = appointmentService.count(comp.getId());
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
		List<Appointment> compAppoint = appointmentService.queryByPager(comp.getId(), pageNo, pageSize);
		req.setAttribute("compAppoint", compAppoint);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
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
		appointmentService.deleteById(id);
	}
	
}
