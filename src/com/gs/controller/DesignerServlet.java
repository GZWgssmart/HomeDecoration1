package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.gs.bean.Designer;
import com.gs.bean.DesignerCase;
import com.gs.service.des.DesignerService;
import com.gs.service.des.DesignerServiceImpl;
import com.gs.service.des_case.DesignerCaseService;
import com.gs.service.des_case.DesignerCaseServiceImpl;
import com.gs.util.FileUtil;
import com.gs.util.MD5Util;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
/**
 * 
 * @author 曾创
 *设计师的servlet
 */
public class DesignerServlet extends HttpServlet{

	private static final long serialVersionUID = -5800435778403196055L;
	
	private DesignerService desImpl;
	private DesignerCaseService desCaseImpl;
	
	public DesignerServlet() {
		desImpl = new DesignerServiceImpl();
		desCaseImpl = new DesignerCaseServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = WebUtil.getUriMethod(req);
		if(path.equals("regpage")) {
			regPage(req, resp);
		} else if(path.equals("loginpage")) {
			loginPage(req, resp);
		} else if(path.equals("wait")) {
			req.getRequestDispatcher("/classification/designer/wait.jsp").forward(req, resp);
		} else if(path.equals("404")) {
			req.getRequestDispatcher("/404.jsp").forward(req, resp);
		} else if(path.equals("login")) {
			login(req, resp);
		} else if(path.equals("reg")) {
			reg(req, resp);
		} else {
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("designer");
			if(obj == null) {
				resp.sendRedirect("loginpage");
			} else {
				if(path.equals("update")) {
					update(req, resp);
				} else if(path.equals("update_pwd")) {
					updatePwd(req, resp);
				} else if(path.equals("add_des")) {
					add_des(req,resp);
				} else if(path.equals("home")) {
					req.getRequestDispatcher("/classification/designer/Backstage.jsp").forward(req, resp);
				} else if(path.equals("updatepage")) {
					req.getRequestDispatcher("/classification/designer/update.jsp").forward(req, resp);
				} else if(path.equals("pwdpage")) {
					req.getRequestDispatcher("/classification/designer/password.jsp").forward(req, resp);
				} else if(path.equals("des_casespage")) {
					page(req, resp);
					req.getRequestDispatcher("/classification/designer/des_cases.jsp").forward(req, resp); 
				} else if(path.equals("add_despage")) {
					req.getRequestDispatcher("/classification/designer/adddes_case.jsp").forward(req, resp); 
				} else if(path.equals("stop")) {
					stop(req,resp);
				} else if(path.equals("show")) {
					show(req,resp);
				} else if(path.equals("edit")) {
					edit(req,resp);
				} else if(path.equals("editpage")) {
					editpage(req, resp);
				} else if(path.equals("headpage")) {
					req.getRequestDispatcher("/classification/designer/head_update.jsp").forward(req, resp);
				} else if(path.equals("headupdate")) {
					updateHeadIcon(req, resp);
				} else if(path.equals("exit")) {
					session.removeAttribute("designer");
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
							req.getRequestDispatcher("/classification/designer/head_update.jsp").forward(req, resp);
							return;
						}
						FileUtil.save(req, item);
						headIcon = ("uploads/" + item.getName());
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("designer");
				String id = null;
				if(obj != null) {
					Designer cus = (Designer)obj;
					id = (cus.getId());
				}
				desImpl.updateHeadIcon(headIcon, id);
				Designer des = desImpl.queryById(id);
				session.setAttribute("designer", des);
				resp.sendRedirect("home");
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 设计师修改密码
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
		Designer des = desImpl.queryByEmailPwd(email, MD5Util.encrypt(password));
		if(des == null) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"密码输入错误\"}");
			return;
		}
		desImpl.updatePwd(MD5Util.encrypt(passwords), des);
		resp.setContentType(Constants.JSON_CONTENT_TYPE);
		out.write("{\"error\":\"修改成功\"}");
	}
	/**
	 * 设计师修改个人资料
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
		String exp = req.getParameter("work_exp");
		String phone = req.getParameter("phone");
		String deses = req.getParameter("des");
		String style = req.getParameter("style");
		// 后台验证
		PrintWriter out = resp.getWriter();
		if(email == null || email.trim().equals("")
				||name == null || name.trim().equals("")
				||address == null || address.trim().equals("")
				||exp == null || exp.trim().equals("")
				||phone == null || phone.trim().equals("")
				||style == null || style.trim().equals("")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入正确的信息!!!\"}");
			return;
		}
		Designer dess = desImpl.queryById(id);
		if(!dess.getEmail().equals(email)) {
			if(desImpl.queryEmail(email) == true) {
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"该邮箱已被使用\"}");
				return;
			}
		}
		Designer design = desImpl.queryById(id);
		Designer des = new Designer();
		des.setHeadicon(design.getHeadicon());
		des.setEmail(email);
		des.setAddress(address);
		des.setId(id);
		des.setPhone(phone);
		des.setExperience(exp);
		des.setStyle(style);
		des.setDes(deses);
		des.setName(name);
		desImpl.update(des);
		HttpSession session = req.getSession();
		Designer designer = desImpl.queryById(id);
		session.setAttribute("designer", designer);
		out.write("{\"error\":\"修改成功\"}");
	}
	/**
	 * 设计师注册
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
		String exp = req.getParameter("work_exp");
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
		if(desImpl.queryEmail(email) == true) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"该邮箱已被使用\"}");
			return;
		}
		Designer des = new Designer();
		des.setEmail(email);
		des.setPassword(MD5Util.encrypt(password));
		des.setPhone(phone);
		des.setName(name);
		des.setHeadicon("uploads/12.jpg");
		des.setExperience(exp);
		des.setCreated_time(Calendar.getInstance().getTime());//默认为系统时间
		des.setLast_login_time(Calendar.getInstance().getTime());
		desImpl.add(des);
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
		req.getRequestDispatcher("/classification/designer/login.jsp").forward(req, resp);
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
		req.getRequestDispatcher("/classification/designer/registered.jsp").forward(req, resp);
	}
	/**
	 * 设计师登录
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
		Designer des = desImpl.queryByEmailPwd(email, MD5Util.encrypt(pwd));
		if(des == null) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"邮箱密码输入错误！\"}");
			return;
		}
		// 审核中，转到审核页面
		if(des.getChecked().equals("N")) {
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"等待审核\"}");
			return;
		}
		// 登录成功，转到个人中心
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
		session.setAttribute("designer", des);
		desImpl.updateLoginTime(Calendar.getInstance().getTime(), des);
		out.write("{\"error\":\"登录成功\"}");
	}
	/**
	 * 设计师添加案例
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add_des(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		// 判断表单是否有文件上传
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			DesignerCase desCase = new DesignerCase();
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					if (item.isFormField()) { // 判断是否为表单域
						String name = item.getFieldName(); // 获取jsp页面中表单域的name值 
						if (name.equals("name")) { // 如果这个表单域是name表单域
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的案例名称!!!\"}");
								return;
							}
							desCase.setName(item.getString("utf-8"));
						} else if (name.equals("polt_name")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的小区名称!!!\"}");
								return;
							}
							desCase.setPlot_name(item.getString("utf-8"));
						} else if(name.equals("style")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的装修风格!!!\"}");
								return;
							}
							desCase.setStyle(item.getString("utf-8"));
						} else if(name.equals("des")) {
							desCase.setDes(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("image_1")) {
							FileUtil.save(req, item);
							desCase.setImage_1("uploads/" + item.getName());
						} else if(name.equals("image_2")) {
							FileUtil.save(req, item);
							desCase.setImage_2("uploads/" + item.getName());
						} else if(name.equals("image_3")) {
							FileUtil.save(req, item);
							desCase.setImage_3("uploads/" + item.getName());
						} else if(name.equals("image_4")) {
							FileUtil.save(req, item);
							desCase.setImage_4("uploads/" + item.getName());
						} else if(name.equals("image_5")) {
							FileUtil.save(req, item);
							desCase.setImage_5("uploads/" + item.getName());
						}
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("designer");
				Designer design = null;
				if(obj != null){
					design = (Designer)obj;
				}
				if(desCase.getImage_1()==null){
					desCase.setImage_1("uploads/timg.jpg");
				}
				if(desCase.getImage_2()==null){
					desCase.setImage_2("uploads/timg.jpg");
				}
				if(desCase.getImage_3()==null){
					desCase.setImage_3("uploads/timg.jpg");
				}
				if(desCase.getImage_4()==null){
					desCase.setImage_4("uploads/timg.jpg");
				}
				if(desCase.getImage_5()==null){
					desCase.setImage_5("uploads/timg.jpg");
				}
				desCase.setDesigner_id(design.getId());
				desCase.setCreated_time(Calendar.getInstance().getTime());
				desCaseImpl.add(desCase);
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"添加成功\"}");
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 分页
	 * @throws ServletException 
	 */
	private void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("designer");
		Designer design = null;
		if(obj != null){
			design = (Designer)obj;
		}
		int total = desCaseImpl.countChecked(design.getId());
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
		List<DesignerCase> desCases = desCaseImpl.PagerChecked(design.getId(), pageNo, pageSize);
		req.setAttribute("desCases", desCases);
		req.setAttribute("pageCount", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 停用这个案例
	 */
	private void stop(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		DesignerCase desCase = desCaseImpl.queryById(id);
		if(desCase.getStatus().equals("Y")){
			desCaseImpl.updateStatus("N", id);
		} else {
			desCaseImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("des_casespage");
	}
	/**
	 * 查看这个案例
	 */
	private void show(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		DesignerCase desCase = desCaseImpl.queryById(id);
		req.setAttribute("show", desCase);
		req.getRequestDispatcher("/classification/designer/des_show.jsp").forward(req, resp); 
	}
	/**
	 * 编辑这个案例
	 */
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		PrintWriter out = resp.getWriter();
		// 判断表单是否有文件上传
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			DesignerCase desCase = new DesignerCase();
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					if (item.isFormField()) { // 判断是否为表单域
						String name = item.getFieldName(); // 获取jsp页面中表单域的name值 
						if (name.equals("name")) { // 如果这个表单域是name表单域
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的案例名称!!!\"}");
								return;
							}
							desCase.setName(item.getString("utf-8"));
						} else if (name.equals("polt_name")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的小区名称!!!\"}");
								return;
							}
							desCase.setPlot_name(item.getString("utf-8"));
						} else if(name.equals("style")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的装修风格!!!\"}");
								return;
							}
							desCase.setStyle(item.getString("utf-8"));
						} else if(name.equals("des")) {
							desCase.setDes(item.getString("utf-8"));
						} else if(name.equals("id")) {
							desCase.setId(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("image_1")) {
							FileUtil.save(req, item);
							desCase.setImage_1("uploads/" + item.getName());
						} else if(name.equals("image_2")) {
							FileUtil.save(req, item);
							desCase.setImage_2("uploads/" + item.getName());
						} else if(name.equals("image_3")) {
							FileUtil.save(req, item);
							desCase.setImage_3("uploads/" + item.getName());
						} else if(name.equals("image_4")) {
							FileUtil.save(req, item);
							desCase.setImage_4("uploads/" + item.getName());
						} else if(name.equals("image_5")) {
							FileUtil.save(req, item);
							desCase.setImage_5("uploads/" + item.getName());
						}
					}
				}
				DesignerCase desc = desCaseImpl.queryById(desCase.getId());
				if(desCase.getImage_1() == null) {
					desCase.setImage_1(desc.getImage_1());
				}
				if(desCase.getImage_2() == null) {
					desCase.setImage_2(desc.getImage_2());
				}
				if(desCase.getImage_3() == null) {
					desCase.setImage_3(desc.getImage_3());
				}
				if(desCase.getImage_4() == null) {
					desCase.setImage_4(desc.getImage_4());
				}
				if(desCase.getImage_5() == null) {
					desCase.setImage_5(desc.getImage_5());
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("designer");
				Designer design = null;
				if(obj != null){
					design = (Designer)obj;
				}
				desCase.setDesigner_id(design.getId());
				desCaseImpl.update(desCase);
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"编辑成功\"}");
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 编辑这个案例的页面
	 */
	private void editpage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		DesignerCase desCase = desCaseImpl.queryById(id);
		req.setAttribute("edit", desCase);
		req.getRequestDispatcher("/classification/designer/des_edit.jsp").forward(req, resp); 
	}
}
