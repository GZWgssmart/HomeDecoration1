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

import com.gs.bean.Company;
import com.gs.bean.CompanyActivity;
import com.gs.bean.CompanyCase;
import com.gs.service.com_act.CompanyActivityService;
import com.gs.service.com_act.CompanyActivityServiceImpl;
import com.gs.service.company_case.CompanyCaseBaseService;
import com.gs.service.company_case.CompanyCaseBaseServiceImpl;
import com.gs.util.FileUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;

/**
 * 装修公司案例表t_company_case				
 * @author ID.LQF
 *
 */
public class Company_caseServlet extends HttpServlet{

	private static final long serialVersionUID = 3149478151249709535L;

	private CompanyCaseBaseService companyCaseService;
	private CompanyActivityService companyActiveService;
	
	public Company_caseServlet() {
		companyCaseService = new CompanyCaseBaseServiceImpl();
		companyActiveService = new CompanyActivityServiceImpl();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("company");
		if(obj == null) {
			resp.sendRedirect("../company/loginpage");
		} else {
			String method = WebUtil.getUriMethod(req);
			if(method.equals("comp_casepage")) {
				page(req,resp);
				req.getRequestDispatcher("/classification/company/com_case.jsp").forward(req, resp);
			} else if(method.equals("add_compage")) {
				req.getRequestDispatcher("/classification/company/addcom_case.jsp").forward(req, resp);
			} else if(method.equals("add_comp")) {
				add_comp(req,resp);
			} else if(method.equals("stopActive")) {
				stopActive(req, resp);
			} else if(method.equals("stop")) {
				stop(req,resp);
			} else if(method.equals("show")) {
				show(req,resp);
			} else if(method.equals("showactive")) {
				showactive(req,resp);
			} else if(method.equals("edit")) {
				edit(req,resp);
			} else if(method.equals("editactive")) {
				editactive(req,resp);
			} else if(method.equals("editpage")) {
				editpage(req, resp);
			} else if(method.equals("editactivepage")) {
				editActivePage(req, resp);
			} else if(method.equals("activepage")) {
				activepage(req, resp);
				req.getRequestDispatcher("/classification/company/comp_active.jsp").forward(req, resp);
			} else if(method.equals("add_activepage")) {
				req.getRequestDispatcher("/classification/company/add_active.jsp").forward(req, resp);
			} else if(method.equals("add_active")) {
				add_active(req,resp);
			}
		}
	}
	
	/**
	 * 装修公司添加案例
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add_comp(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		// 判断表单是否有文件上传
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			CompanyCase compCase = new CompanyCase();
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
							compCase.setName(item.getString("utf-8"));
						} else if (name.equals("polt_name")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的小区名称!!!\"}");
								return;
							}
							compCase.setPlot_name(item.getString("utf-8"));
						} else if(name.equals("style")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的装修风格!!!\"}");
								return;
							}
							compCase.setStyle(item.getString("utf-8"));
						} else if(name.equals("des")) {
							compCase.setDes(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("image_1")) {
							FileUtil.save(req, item);
							compCase.setImage_1("uploads/" + item.getName());
						} else if(name.equals("image_2")) {
							FileUtil.save(req, item);
							compCase.setImage_2("uploads/" + item.getName());
						} else if(name.equals("image_3")) {
							FileUtil.save(req, item);
							compCase.setImage_3("uploads/" + item.getName());
						} else if(name.equals("image_4")) {
							FileUtil.save(req, item);
							compCase.setImage_4("uploads/" + item.getName());
						} else if(name.equals("image_5")) {
							FileUtil.save(req, item);
							compCase.setImage_5("uploads/" + item.getName());
						}
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("company");
				Company comp = null;
				if(obj != null){
					comp = (Company)obj;
				}
				if(compCase.getImage_1()==null){
					compCase.setImage_1("uploads/timg.jpg");
				}
				if(compCase.getImage_2()==null){
					compCase.setImage_2("uploads/timg.jpg");
				}
				if(compCase.getImage_3()==null){
					compCase.setImage_3("uploads/timg.jpg");
				}
				if(compCase.getImage_4()==null){
					compCase.setImage_4("uploads/timg.jpg");
				}
				if(compCase.getImage_5()==null){
					compCase.setImage_5("uploads/timg.jpg");
				}
				compCase.setCompany_id(comp.getId());
				compCase.setCreated_time(Calendar.getInstance().getTime());
				companyCaseService.add(compCase);
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
	/**
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("company");
		Company comp = null;
		if(obj != null){
			comp = (Company) obj;
		}
		int total = companyCaseService.count(comp.getId());
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
		List<CompanyCase> compCase = companyCaseService.queryByPager(comp.getId(),pageNo, pageSize);
		req.setAttribute("compCase", compCase);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 案例的状态修改
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void stop(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyCase desCase = companyCaseService.queryById(id);
		if(desCase.getStatus().equals("Y")){
			companyCaseService.updateStatus("N", id);
		} else {
			companyCaseService.updateStatus("Y", id);
		}
		resp.sendRedirect("comp_casepage");
	}
	
	/**
	 * 活动的状态修改
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void stopActive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyActivity desCase = companyActiveService.queryById(id);
		if(desCase.getStatus().equals("Y")){
			companyActiveService.updateStatus("N", id);
		} else {
			companyActiveService.updateStatus("Y", id);
		}
		resp.sendRedirect("activepage");
	}
	
	/**
	 * 查看这个案例
	 */
	private void show(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyCase compCase = companyCaseService.queryById(id);
		req.setAttribute("show", compCase);
		req.getRequestDispatcher("/classification/company/comp_show.jsp").forward(req, resp); 
	}
	/**
	 * 查看这个活动
	 */
	private void showactive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyActivity compCase = companyActiveService.queryById(id);
		req.setAttribute("show", compCase);
		req.getRequestDispatcher("/classification/company/active_show.jsp").forward(req, resp); 
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
			CompanyCase compCase = new CompanyCase();
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
							compCase.setName(item.getString("utf-8"));
						} else if (name.equals("polt_name")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的小区名称!!!\"}");
								return;
							}
							compCase.setPlot_name(item.getString("utf-8"));
						} else if(name.equals("style")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的装修风格!!!\"}");
								return;
							}
							compCase.setStyle(item.getString("utf-8"));
						} else if(name.equals("des")) {
							compCase.setDes(item.getString("utf-8"));
						} else if(name.equals("id")) {
							compCase.setId(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("image_1")) {
							FileUtil.save(req, item);
							compCase.setImage_1("uploads/" + item.getName());
						} else if(name.equals("image_2")) {
							FileUtil.save(req, item);
							compCase.setImage_2("uploads/" + item.getName());
						} else if(name.equals("image_3")) {
							FileUtil.save(req, item);
							compCase.setImage_3("uploads/" + item.getName());
						} else if(name.equals("image_4")) {
							FileUtil.save(req, item);
							compCase.setImage_4("uploads/" + item.getName());
						} else if(name.equals("image_5")) {
							FileUtil.save(req, item);
							compCase.setImage_5("uploads/" + item.getName());
						}
					}
				}
				CompanyCase desc = companyCaseService.queryById(compCase.getId());
				if(compCase.getImage_1() == null) {
					compCase.setImage_1(desc.getImage_1());
				}
				if(compCase.getImage_2() == null) {
					compCase.setImage_2(desc.getImage_2());
				}
				if(compCase.getImage_3() == null) {
					compCase.setImage_3(desc.getImage_3());
				}
				if(compCase.getImage_4() == null) {
					compCase.setImage_4(desc.getImage_4());
				}
				if(compCase.getImage_5() == null) {
					compCase.setImage_5(desc.getImage_5());
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("company");
				Company comp = null;
				if(obj != null){
					comp = (Company)obj;
				}
				compCase.setCompany_id(comp.getId());
				companyCaseService.update(compCase);
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
	 * 编辑这个活动
	 */
	private void editactive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		PrintWriter out = resp.getWriter();
		// 判断表单是否有文件上传
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			CompanyActivity compCase = new CompanyActivity();
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
							compCase.setName(item.getString("utf-8"));
						} else if(name.equals("des")) {
							compCase.setDes(item.getString("utf-8"));
						} else if(name.equals("id")) {
							compCase.setId(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("image_1")) {
							FileUtil.save(req, item);
							compCase.setImage("uploads/" + item.getName());
						} 
					}
				}
				CompanyActivity desc = companyActiveService.queryById(compCase.getId());
				if(compCase.getImage() == null) {
					compCase.setImage(desc.getImage());
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("company");
				Company comp = null;
				if(obj != null){
					comp = (Company)obj;
				}
				compCase.setCompany_id(comp.getId());
				companyActiveService.update(compCase);
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
		CompanyCase compCase = companyCaseService.queryById(id);
		req.setAttribute("edit", compCase);
		req.getRequestDispatcher("/classification/company/comp_edit.jsp").forward(req, resp); 
	}
	
	/**
	 * 编辑这个活动的页面
	 */
	private void editActivePage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyActivity compCase = companyActiveService.queryById(id);
		req.setAttribute("edit", compCase);
		req.getRequestDispatcher("/classification/company/active_edit.jsp").forward(req, resp); 
	}
	
	/**
	 * 装修公司添加活动案例
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add_active(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		// 判断表单是否有文件上传
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			CompanyActivity compCase = new CompanyActivity();
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					if (item.isFormField()) { // 判断是否为表单域
						String name = item.getFieldName(); // 获取jsp页面中表单域的name值 
						if (name.equals("name")) { // 如果这个表单域是name表单域
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的活动名称!!!\"}");
								return;
							}
							compCase.setName(item.getString("utf-8"));
						} else if(name.equals("des")) {
							compCase.setDes(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("image_1")) {
							FileUtil.save(req, item);
							compCase.setImage("uploads/" + item.getName());
						} 
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("company");
				Company comp = null;
				if(obj != null){
					comp = (Company)obj;
				}
				if(compCase.getImage()==null){
					compCase.setImage("uploads/12.jpg");
				}
				compCase.setCompany_id(comp.getId());
				compCase.setCreated_time(Calendar.getInstance().getTime());
				companyActiveService.add(compCase);
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
	private void activepage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("company");
		Company comp = null;
		if(obj != null){
			comp = (Company) obj;
		}
		int total = companyActiveService.count(comp.getId());
		int pageSize = 5;
		int pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = req.getParameter("pageNo");
		if(pageStr != null){
			pageNo = Integer.valueOf(pageStr);
			try{
				if(pageNo <= 0){
					pageNo = 1;
				} else if (pageNo >= pageCount){
					pageNo = pageCount;
				}
			} catch(NumberFormatException e){
				
			}
		}
		List<CompanyActivity> compActive = companyActiveService.queryByPager(comp.getId(),pageNo, pageSize);
		req.setAttribute("compActive", compActive);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageNo", pageNo);
	}
}
