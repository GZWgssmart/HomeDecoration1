package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.bean.Admin;
import com.gs.bean.Company;
import com.gs.bean.Designer;
import com.gs.bean.Supply;
import com.gs.service.adm.AdminService;
import com.gs.service.adm.AdminServiceImpl;
import com.gs.service.comp.CompanyBaseService;
import com.gs.service.comp.CompanyBaseServiceImpl;
import com.gs.service.des.DesignerService;
import com.gs.service.des.DesignerServiceImpl;
import com.gs.service.supply.SupplyBaseService;
import com.gs.service.supply.SupplyBaseServiceImpl;
import com.gs.util.GetPropUtil;
import com.gs.util.MD5Util;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
import com.gs.util.PathUtil;

/**
 * 
 * @author 曾创
 *管理员
 */
public class AdminServlet extends HttpServlet{

	private static final long serialVersionUID = -8171186216278144521L;
	
	private AdminService admImpl;
	private DesignerService desImpl;
	private CompanyBaseService comImpl;
	private SupplyBaseService supImpl;
	
	public AdminServlet() {
		admImpl = new AdminServiceImpl();
		desImpl = new DesignerServiceImpl();
		comImpl = new CompanyBaseServiceImpl();
		supImpl = new SupplyBaseServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = WebUtil.getUriMethod(req);
		if(path.equals("loginpage")) {
			loginPage(req, resp);
		} else if(path.equals("login")) {
			login(req, resp);
		} else {
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("admin");
			if(obj == null) {
				resp.sendRedirect("loginpage");
			} else {
				if(path.equals("reg")) {
					reg(req, resp);
				} else if(path.equals("update")) {
					update(req, resp);
				} else if(path.equals("update_pwd")) {
					updatePwd(req, resp);
				} else if(path.equals("home")) {
					req.getRequestDispatcher("/classification/administrator/Backstage.jsp").forward(req, resp);
				} else if(path.equals("updatepage")) {
					req.getRequestDispatcher("/classification/administrator/update.jsp").forward(req, resp);
				} else if(path.equals("pwdpage")) {
					req.getRequestDispatcher("/classification/administrator/password.jsp").forward(req, resp);
				} else if(path.equals("regpage")) {
					req.getRequestDispatcher("/classification/administrator/registered.jsp").forward(req, resp);
				} else if(path.equals("comp_check")) {
					comCheck(req, resp);
					req.getRequestDispatcher("/classification/administrator/com_check.jsp").forward(req, resp);
				} else if(path.equals("des_check")) {
					desCheck(req, resp);
					req.getRequestDispatcher("/classification/administrator/des_check.jsp").forward(req, resp);
				} else if(path.equals("sup_check")) {
					supCheck(req, resp);
					req.getRequestDispatcher("/classification/administrator/sup_check.jsp").forward(req, resp);
				} else if(path.equals("despass")) {
					desPassCheck(req, resp);
				} else if(path.equals("compass")) {
					comPassCheck(req, resp);
				} else if(path.equals("suppass")) {
					supPassCheck(req, resp);
				} else if(path.equals("exit")) {
					session.removeAttribute("admin");
					resp.sendRedirect("loginpage");
				}
			}
		}
	}
	/**
	 * 设计师通过审核
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desPassCheck(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		desImpl.updateCheckedAndTime("Y", Calendar.getInstance().getTime(), id);
		String email = req.getParameter("email");
		//获取标题
		String title = "审核已通过";
		//获取内容
		String nr = "尊敬的用户您好！您在BeautHome注册的账号已通过审核，请通过注册时的邮箱密码进行登录~";
		//账号
		String accountt = "1151757358@qq.com";
		//密码
		String password = "kakzmkmwgnochbic";
		//读取邮箱配置信息
		Properties properties = new Properties();
		//邮件传输协议
		properties.put("mail.transport.protocol", "smtp");
		//邮件发送主机
		properties.put("mail.host","smtp.qq.com");
		//密码安全验证
		properties.put("mail.smtp.auth",true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", "465");
		//放入邮箱会话
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		//创建一个邮箱
		MimeMessage ms = new MimeMessage(session);
		Address address ;
		try {
			//查询邮箱
			address= new InternetAddress(accountt);
			//设置来源
			ms.setFrom(address);
			//接收者
			ms.setRecipients(Message.RecipientType.TO,email);
			//内容标题
			ms.setSubject(title);
			//内容
			ms.setText(nr);
			//保存
			ms.saveChanges();
			
			//创建一个发送工具
			Transport ts = session.getTransport();
			//设置邮箱和密码
			ts.connect(accountt,password);
			ts.sendMessage(ms, ms.getAllRecipients());
			ts.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("des_check");
	}
	/**
	 * 装修公司通过审核
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comPassCheck(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		comImpl.updateCheckedAndTime("Y", Calendar.getInstance().getTime(), id);
		String email = req.getParameter("email");
		//获取标题
		String title = "审核已通过";
		//获取内容
		String nr = "尊敬的用户您好！您在BeautHome注册的账号已通过审核，请通过注册时的邮箱密码进行登录~";
		//账号
		String accountt = "1151757358@qq.com";
		//密码
		String password = "kakzmkmwgnochbic";
		//读取邮箱配置信息
		Properties properties = new Properties();
		//邮件传输协议
		properties.put("mail.transport.protocol", "smtp");
		//邮件发送主机
		properties.put("mail.host","smtp.qq.com");
		//密码安全验证
		properties.put("mail.smtp.auth",true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", "465");
		//放入邮箱会话
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		//创建一个邮箱
		MimeMessage ms = new MimeMessage(session);
		Address address ;
		try {
			//查询邮箱
			address= new InternetAddress(accountt);
			//设置来源
			ms.setFrom(address);
			//接收者
			ms.setRecipients(Message.RecipientType.TO,email);
			//内容标题
			ms.setSubject(title);
			//内容
			ms.setText(nr);
			//保存
			ms.saveChanges();
			
			//创建一个发送工具
			Transport ts = session.getTransport();
			//设置邮箱和密码
			ts.connect(accountt,password);
			ts.sendMessage(ms, ms.getAllRecipients());
			ts.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("comp_check");
	}
	/**
	 * 建材商通过审核
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supPassCheck(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		supImpl.updateCheckedAndTime("Y", Calendar.getInstance().getTime(), id);
		String email = req.getParameter("email");
		//获取标题
		String title = "审核已通过";
		//获取内容
		String nr = "尊敬的用户您好！您在BeautHome注册的账号已通过审核，请通过注册时的邮箱密码进行登录~";
		//账号
		String accountt = "1151757358@qq.com";
		//密码
		String password = "kakzmkmwgnochbic";
		//读取邮箱配置信息
		Properties properties = new Properties();
		//邮件传输协议
		properties.put("mail.transport.protocol", "smtp");
		//邮件发送主机
		properties.put("mail.host","smtp.qq.com");
		//密码安全验证
		properties.put("mail.smtp.auth",true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", "465");
		//放入邮箱会话
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		//创建一个邮箱
		MimeMessage ms = new MimeMessage(session);
		Address address ;
		try {
			//查询邮箱
			address= new InternetAddress(accountt);
			//设置来源
			ms.setFrom(address);
			//接收者
			ms.setRecipients(Message.RecipientType.TO,email);
			//内容标题
			ms.setSubject(title);
			//内容
			ms.setText(nr);
			//保存
			ms.saveChanges();
			
			//创建一个发送工具
			Transport ts = session.getTransport();
			//设置邮箱和密码
			ts.connect(accountt,password);
			ts.sendMessage(ms, ms.getAllRecipients());
			ts.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("sup_check");
	}
	/**
	 * 装修公司审核页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comCheck(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = comImpl.countPass();// 总装修公司数目
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
		List<Company> comList = comImpl.queryChecked(pageNo, pageSize);
		req.setAttribute("comList", comList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 建材商审核页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supCheck(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = supImpl.countPass();// 总建材商数目
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
		List<Supply> supList = supImpl.queryChecked(pageNo, pageSize);
		req.setAttribute("supList", supList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 设计师审核页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void desCheck(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int count  = desImpl.countPass();// 总设计师数目
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
		List<Designer> desList = desImpl.queryChecked(pageNo, pageSize);
		req.setAttribute("desList", desList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 管理员修改密码
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
		Admin adm = admImpl.queryByEmailPwd(email, MD5Util.encrypt(password));
		if(adm == null) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"密码输入错误\"}");
			return;
		}
		admImpl.updatePwd(MD5Util.encrypt(passwords), adm);
		resp.setContentType(Constants.JSON_CONTENT_TYPE);
		out.write("{\"error\":\"修改成功\"}");
	}
	/**
	 * 管理员修改个人资料
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
		String phone = req.getParameter("phone");
		// 后台验证
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("")
				||name == null || name.trim().equals("")
				||phone == null || phone.trim().equals("")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的信息!!!\"}");
			return;
		}
		Admin adms = admImpl.queryById(id);
		if(!adms.getEmail().equals(email)) {
			if(admImpl.queryEmail(email) == true) {
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
		Admin adm = new Admin();
		adm.setEmail(email);
		adm.setName(name);
		adm.setPhone(phone);
		adm.setId(id);
		admImpl.update(adm);
		Admin admin = admImpl.queryById(id);
		HttpSession session = req.getSession();
		session.setAttribute("admin", admin);
		out.write("{\"error\":\"修改成功\"}");
	}
	/**
	 * 管理员创建
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
		if(admImpl.queryEmail(email) == true) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"该邮箱已被使用\"}");
			return;
		}
		if(phone.length() != 11) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"电话输入错误！\"}");
			return;
		}
		Admin adm = new Admin();
		adm.setEmail(email);
		adm.setName(name);
		adm.setPassword(MD5Util.encrypt(password));
		adm.setPhone(phone);
		adm.setRole("normal");
		adm.setCreated_time(Calendar.getInstance().getTime());
		adm.setLast_login_time(Calendar.getInstance().getTime());
		admImpl.add(adm);
		out.write("{\"error\":\"注册成功\"}");
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
		req.getRequestDispatcher("/classification/administrator/login.jsp").forward(req, resp);
	}
	/**
	 * 管理员登录
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
		Admin adm = admImpl.queryByEmailPwd(email, MD5Util.encrypt(pwd));
		if(adm == null) {
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
		session.setAttribute("admin", adm);
		admImpl.updateLoginTime(Calendar.getInstance().getTime(), adm);
		// 登录成功，转到个人中心
		out.write("{\"error\":\"登录成功\"}");
	}
}
