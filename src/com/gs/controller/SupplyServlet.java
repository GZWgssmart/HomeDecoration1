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

import com.gs.bean.Supply;
import com.gs.service.supply.SupplyBaseService;
import com.gs.service.supply.SupplyBaseServiceImpl;
import com.gs.util.DateUtil;
import com.gs.util.FileUtil;
import com.gs.util.MD5Util;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;

/**
 * 建材商表t_supply				
 * @author ID.LQF
 *
 */
public class SupplyServlet extends HttpServlet{

	private static final long serialVersionUID = -9096945554321818142L;

	private SupplyBaseService supplyService;
	
	public SupplyServlet(){
		supplyService = new SupplyBaseServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		if (method.equals("login")) {
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
			Object obj = session.getAttribute("supply");
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
					req.getRequestDispatcher("/classification/businessman/head_update.jsp").forward(req, resp);
				} else if(method.equals("headupdate")) {
					updateLogo(req, resp);
				} else if(method.equals("exit")) {
					session.removeAttribute("supply");
					resp.sendRedirect("loginpage");
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
							req.getRequestDispatcher("/classification/businessman/head_update.jsp").forward(req, resp);
							return;
						}
						FileUtil.save(req, item);
						logo = ("uploads/" + item.getName());
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("supply");
				String id = null;
				if(obj != null) {
					Supply com = (Supply)obj;
					id = (com.getId());
				}
				supplyService.updateLogo(logo, id);
				Supply sup = supplyService.queryById(id);
				session.setAttribute("supply", sup);
				resp.sendRedirect("home");
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}

	public void wait(HttpServletRequest req ,HttpServletResponse resp) throws IOException, ServletException{
		req.getRequestDispatcher("/classification/businessman/wait.jsp").forward(req, resp);
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
		} else {
			Supply supply = supplyService.queryByEmailPwd(email, MD5Util.encrypt(pwd));
			if(supply == null) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"邮箱密码输入错误！\"}");
				return;
			} 
			if(supply.getChecked().equals("N")) {
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
			session.setAttribute("supply", supply);
			supplyService.updateLoginTime(Calendar.getInstance().getTime(), supply);
			out.write("{\"error\":\"登入成功\"}");
		}
	}
	
	public void loginPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/classification/businessman/login.jsp").forward(req, resp);
	}
	
	public void register(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String supename = req.getParameter("supename");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String passwords = req.getParameter("passwords");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("") || name==null || name.trim().equals("") || supename==null || supename.trim().equals("") || phone==null || phone.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的注册信息!!!\"}");
			return;
		} else {
			Supply supply = new Supply();
			supply.setName(supename);
			if(supplyService.queryByEmail(email)==true){
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"此邮箱已被注册!!!\"}");
				return;
			}
			if(phone.length() != 11) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"电话输入错误！\"}");
				return;
			}
			supply.setEmail(email);
			supply.setPassword(MD5Util.encrypt(password));
			supply.setPrincipal(name);
			supply.setLogo("uploads/12.jpg");
			supply.setPhone(phone);
			supply.setCreated_time(Calendar.getInstance().getTime());
			supply.setChecked_time(Calendar.getInstance().getTime());
			supply.setLast_login_time(Calendar.getInstance().getTime());
			if( !password.equals(passwords)){
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"你的密码不一致，请重新输入\"}");
				return;
			} else {
				supply.setPassword(MD5Util.encrypt(password));
			}
			supplyService.add(supply);
			out.write("{\"error\":\"注册成功\"}");
		}
	}
	
	public void registerPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/classification/businessman/registered.jsp").forward(req, resp);
	}
	
	public void home(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/classification/businessman/Backstage.jsp").forward(req, resp);
	}
	
	public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String tel = req.getParameter("tel");
		String principal = req.getParameter("principal");
		String opendate = req.getParameter("opendate");
		String longitude = req.getParameter("longitude");
		String latitude = req.getParameter("latitude");
		String des = req.getParameter("des");
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("") || principal==null || principal.trim().equals("") || name==null || name.trim().equals("") || phone==null || phone.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的注册信息!!!\"}");
			return;
		} else {
			Supply supply = new Supply();
			supply.setId(id);
			supply.setName(name);
			Supply supp = supplyService.queryById(id);
			if(!supp.getEmail().equals(email)) {
				if(supplyService.queryByEmail(email) == true) {
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
			supply.setLogo(supp.getLogo());
			supply.setEmail(email);
			supply.setAddress(address);
			supply.setPhone(phone);
			if(tel == null || tel.trim().equals("")) {
				supply.setTel(null);
			} else {
				if(tel.length() != 11) {
					resp.setContentType(Constants.JSON_CONTENT_TYPE);
					out.write("{\"error\":\"电话输入错误！\"}");
					return;
				}
				supply.setTel(tel);
			}
			supply.setPrincipal(principal);
			supply.setLatitude(Float.valueOf(latitude));
			supply.setLongitude(Float.valueOf(longitude));
			supply.setChecked_time(Calendar.getInstance().getTime());
			supply.setLast_login_time(Calendar.getInstance().getTime());
			Date now = Calendar.getInstance().getTime();
			supply.setDes(des);
			Date open = DateUtil.parseDate(opendate, Constants.USE_PATTERN);
			if(now.getTime()<open.getTime()){
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"请输入正确的成立时间!!!\"}");
				return;
			} else {
				supply.setOpen_date(DateUtil.parseDate(opendate, Constants.USE_PATTERN));
			}
			supplyService.update(supply);
			Supply sup = supplyService.queryById(id);
			HttpSession session = req.getSession();
			session.setAttribute("supply", sup);
			out.write("{\"error\":\"更改成功\"}");
		}
	}
	
	public void updatePage(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.getRequestDispatcher("/classification/businessman/update.jsp").forward(req, resp);
	}
	
	public void pwdPage(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.getRequestDispatcher("/classification/businessman/password.jsp").forward(req, resp);
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
			Supply supply = supplyService.queryByEmailPwd(email, MD5Util.encrypt(password));
			supply.setPassword(passwords);
			supplyService.updatePwd(MD5Util.encrypt(passwords), supply);
			out.write("{\"error\":\"密码更改成功\"}");
		}
	}
	
}
