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

import com.gs.bean.Appointment;
import com.gs.bean.Company;
import com.gs.bean.CompanyActivity;
import com.gs.bean.CompanyCase;
import com.gs.bean.CompanyCaseCol;
import com.gs.bean.CompanyCol;
import com.gs.bean.Customer;
import com.gs.service.app.AppointmentService;
import com.gs.service.app.AppointmentServiceImpl;
import com.gs.service.com_act.CompanyActivityService;
import com.gs.service.com_act.CompanyActivityServiceImpl;
import com.gs.service.comp.CompanyBaseService;
import com.gs.service.comp.CompanyBaseServiceImpl;
import com.gs.service.comp_case_col.CompanyCaseColService;
import com.gs.service.comp_case_col.CompanyCaseColServiceImpl;
import com.gs.service.comp_col.CompanyColService;
import com.gs.service.comp_col.CompanyColServiceImpl;
import com.gs.service.company_case.CompanyCaseBaseService;
import com.gs.service.company_case.CompanyCaseBaseServiceImpl;
import com.gs.util.DateUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;

public class BHCompanyServlet extends HttpServlet{

	private static final long serialVersionUID = -825780701980133453L;

	private CompanyBaseService companyService;
	private	CompanyCaseBaseService companyCaseService;
	private CompanyActivityService companyActiveService;
	private AppointmentService appointService;
	private CompanyColService companyColService;
	private CompanyCaseColService companyCaseColService;
	
	public BHCompanyServlet() {
		companyService = new CompanyBaseServiceImpl();
		companyCaseService = new CompanyCaseBaseServiceImpl();
		companyActiveService = new CompanyActivityServiceImpl();
		appointService = new AppointmentServiceImpl();
		companyColService = new CompanyColServiceImpl();
		companyCaseColService = new CompanyCaseColServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		if(method.equals("company")) {
			company(req, resp);
			companyCases(req, resp);
			page(req, resp);
			req.getRequestDispatcher("/classification/company/all_company.jsp").forward(req, resp);
		} else if(method.equals("comp")) {
			comp(req, resp);
			collect(req,resp);
			companyCase(req, resp);
			companyActive(req, resp);
			req.getRequestDispatcher("/classification/company/single_comp.jsp").forward(req, resp);
		} else if(method.equals("case")) {
			singleCase(req, resp);
			companyCase(req, resp);
			req.getRequestDispatcher("/classification/company/single_case.jsp").forward(req, resp);
		}  else if(method.equals("case2")) {
			singleCase(req, resp);
			companyCase1(req, resp);
			req.getRequestDispatcher("/classification/company/single_case.jsp").forward(req, resp);
		} else if(method.equals("case1")) {
			singleCase(req, resp);
			companyCase1(req, resp);
			collectcase(req, resp);
			req.getRequestDispatcher("/classification/company/single_case.jsp").forward(req, resp);
		} else if(method.equals("active")) {
			active(req, resp);
			companyCase2(req, resp);
			companyActive1(req, resp);
			req.getRequestDispatcher("/classification/company/single_active.jsp").forward(req, resp);
		} else if(method.equals("allcase")) {
			casepage(req, resp);
			allCase(req, resp);
			req.getRequestDispatcher("/classification/company/all_case.jsp").forward(req, resp);
		} else if(method.equals("single_allcase")) {
			singleCasepage(req, resp);
			req.getRequestDispatcher("/classification/company/single_allcase.jsp").forward(req, resp);
		} else if(method.equals("single_allactive")) {
			singleActivepage(req, resp);
			req.getRequestDispatcher("/classification/company/single_allactive.jsp").forward(req, resp);
		} else if(method.equals("appointment")) {
			id(req, resp);
			req.getRequestDispatcher("/classification/company/app.jsp").forward(req, resp);
		} else if(method.equals("app")) {
			appointment(req, resp);
		} else if(method.equals("collect_comp")) {
			collectCompany(req,resp);
		} else if(method.equals("collect_case")) {
			collectCase(req,resp);
		}
	}

	/**
	 * 获取id
	 * @param req
	 * @param resp
	 */
	public void id(HttpServletRequest req,HttpServletResponse resp){
		 String company_id =req.getParameter("id");
		 Company company = companyService.queryById(company_id);
		 req.setAttribute("company", company);
	}
	/**
	 * 获取前6个装修公司
	 * @param req
	 * @param resp
	 */
	public void company(HttpServletRequest req,HttpServletResponse resp){
		 List<Company> companys = companyService.queryCompanyTop6();
		 req.setAttribute("companys", companys);
	}
	
	/**
	 * 获取前3个装修公司案例
	 * @param req
	 * @param resp
	 */
	public void companyCases(HttpServletRequest req,HttpServletResponse resp){
		 List<CompanyCase> companys = companyCaseService.queryCompanyTop3();
		 req.setAttribute("companycases", companys);
	}
	
	/**
	 * 根据公司id获取获取单个公司的前3个案例
	 * @param req
	 * @param resp
	 */
	public void companyCase(HttpServletRequest req,HttpServletResponse resp){
		 String id = req.getParameter("id");
		 List<CompanyCase> companys = companyCaseService.queryByCompId(id);
		 req.setAttribute("companycase", companys);
	}
	
	/**
	 * 根据公司案例id获取获取单个公司的前3个案例
	 * @param req
	 * @param resp
	 */
	public void companyCase1(HttpServletRequest req,HttpServletResponse resp){
		 String id = req.getParameter("id");
		 CompanyCase company = companyCaseService.queryById(id);
		 List<CompanyCase> companys = companyCaseService.queryByCompId(company.getCompany_id());
		 req.setAttribute("companycase", companys);
	}
	
	/**
	 * 根据公司活动id获取获取单个公司的前3个案例
	 * @param req
	 * @param resp
	 */
	public void companyCase2(HttpServletRequest req,HttpServletResponse resp){
		 String id = req.getParameter("id");
		 CompanyActivity company = companyActiveService.queryById(id);
		 List<CompanyCase> companys = companyCaseService.queryByCompId(company.getCompany_id());
		 req.setAttribute("companycase", companys);
	}
	
	/**
	 * 获取前3个装修公司的活动案例
	 * @param req
	 * @param resp
	 */
	public void companyActives(HttpServletRequest req,HttpServletResponse resp){
		List<CompanyActivity> companyActive = companyActiveService.queryActiveTop3();
		 req.setAttribute("companyActives", companyActive);
	}
	
	/**
	 * 获取单个装修公司的前3个活动案例
	 * @param req
	 * @param resp
	 */
	public void companyActive(HttpServletRequest req,HttpServletResponse resp){
		 String id = req.getParameter("id");
		 List<CompanyActivity> companyActive = companyActiveService.queryByCompId(id);
		 companyActive = companyActiveService.queryActiveTop3(id);
		 req.setAttribute("companyActive", companyActive);
	}
	
	/**
	 * 根据活动id获取单个装修公司的前3个活动案例
	 * @param req
	 * @param resp
	 */
	public void companyActive1(HttpServletRequest req,HttpServletResponse resp){
		 String id = req.getParameter("id");
		 CompanyActivity active = companyActiveService.queryById(id);
		 List<CompanyActivity> companyActive = companyActiveService.queryByCompId(active.getCompany_id());
		 req.setAttribute("companyActive", companyActive);
	}
	
	/**
	 * 获取前所有装修公司
	 * @param req
	 * @param resp
	 */
	public void allCompany(HttpServletRequest req,HttpServletResponse resp){
		 List<Company> companys = companyService.queryAll();
		 req.setAttribute("allCompanys", companys);
	}
	/**
	 * 获取前所有装修公司的案例
	 * @param req
	 * @param resp
	 */
	public void allCase(HttpServletRequest req,HttpServletResponse resp){
		 List<CompanyCase> companycases = companyCaseService.queryAll();
		 req.setAttribute("allCases", companycases);
	}
	/**
	 * 所有装修公司的分页
	 * @throws ServletException 
	 */
	public void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		int total = companyService.count();
		int pageSize = 10;
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
		List<Company> compCase = companyService.queryByPager(pageNo, pageSize);
		req.setAttribute("comp", compCase);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 所有案例的分页
	 * @throws ServletException 
	 */
	public void casepage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		int total = companyCaseService.count();
		int pageSize = 12;
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
		List<CompanyCase> compCase = companyCaseService.queryByPager(pageNo, pageSize);
		req.setAttribute("allCase", compCase);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 单个公司的所有案例的分页
	 * @throws ServletException 
	 */
	public void singleCasepage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		int total = companyCaseService.count(id);
		int pageSize = 12;
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
		List<CompanyCase> compCase = companyCaseService.queryByPager(id, pageNo, pageSize);
		Company com = companyService.queryById(id);
		req.setAttribute("com", com);
		req.setAttribute("allCase", compCase);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 单个公司的所有活动的分页
	 * @throws ServletException 
	 */
	public void singleActivepage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		int total = companyActiveService.count(id);
		int pageSize = 12;
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
		List<CompanyActivity> compCase = companyActiveService.queryByPager(id, pageNo, pageSize);
		Company com = companyService.queryById(id);
		req.setAttribute("com", com);
		req.setAttribute("allCase", compCase);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageNo", pageNo);
	}
	/**
	 * 获取点击的那个公司的信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void comp(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		Company company = companyService.queryById(id);
		req.setAttribute("company", company);
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			String app_id = appointService.appCheck(cus.getId(), id);
			if(app_id != null) {
				req.setAttribute("apped", true);
			} else {
				req.setAttribute("apped", false);
			}
		} else {
			req.setAttribute("apped", false);
		}
		
	}
	/**
	 * 获取点击的那个公司案例的信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void singleCase(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyCase company = companyCaseService.queryById(id); //此id是company_id
		Company comp = companyService.queryById(company.getCompany_id());
		req.setAttribute("singleCase", company);
		req.setAttribute("company", comp);
	}
	/**
	 * 获取点击的那个公司案例的信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void allSingleCase(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyCase company = companyCaseService.queryById(id);
		Company comp = companyService.queryById(company.getCompany_id());
		req.setAttribute("singleCase", company);
		req.setAttribute("company", comp);
	}
	/**
	 * 获取点击的那个公司的活动信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void active(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");
		CompanyActivity active = companyActiveService.queryById(id);
		String compId = active.getCompany_id();
		Company company = companyService.queryById(compId);
		req.setAttribute("active", active);
		req.setAttribute("company", company);
	}
	
	/**
	 * 提交预约时的验证
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void appointment(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String plot_name = req.getParameter("plot_name");
		String area = req.getParameter("area");
		String way = req.getParameter("way");
		String budget = req.getParameter("budget");
		PrintWriter out = resp.getWriter();
		if(name == null || name.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入您的名字!!!\"}");
			return;
		}
		if(phone == null || phone.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入您的手机号!!!\"}");
			return;
		}
		if(plot_name == null || plot_name.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入小区名称!!!\"}");
			return;
		}
		if(area == null || area.trim().equals("")){
			resp.setContentType(Constants.JSON_CONTENT_TYPE);
			out.write("{\"error\":\"请输入建筑面积!!!\"}");
			return;
		}
		Appointment appoint = new Appointment();
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			appoint.setUser_id(cus.getId());
		}
		String company_id =req.getParameter("id");
		Company company = companyService.queryById(company_id);
		appoint.setPhone(phone);
		appoint.setCompany_id(company.getId());
		appoint.setName(name);
		appoint.setCompany_name(company.getName());
		appoint.setPlot_name(plot_name);
		appoint.setArea(Float.valueOf(area));
		appoint.setWay(way);
		appoint.setBudget(budget);
		appoint.setCreated_time(DateUtil.convert(Calendar.getInstance().getTime()));
		appointService.add(appoint);
		out.write("{\"error\":\"预约成功\"}");
	}
	
	/**
	 * 收藏公司
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void collectCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");  //传入公司的id
		HttpSession session = req.getSession();  //通过session来获取用户的id
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
		CompanyCol companyCol = new CompanyCol();
		companyCol.setCompany_id(id);
		companyCol.setCustomer_id(cus.getId());
		companyCol.setCreated_time(Calendar.getInstance().getTime());
		companyColService.add(companyCol);
		out.write("{\"save\":\"成功\"}");
	}
	
	/**
	 * 收藏公司案例
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void collectCase(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String id = req.getParameter("id");  //传入公司案例的id
		HttpSession session = req.getSession();  //通过session来获取用户的id
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
		CompanyCaseCol companyCaseCol = new CompanyCaseCol();
		companyCaseCol.setCase_id(id);
		companyCaseCol.setCustomer_id(cus.getId());
		companyCaseCol.setCreated_time(Calendar.getInstance().getTime());
		companyCaseColService.add(companyCaseCol);
		out.write("{\"save\":\"成功\"}");
	}
	
	/**
	 * 单个公司的收藏按钮
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void collect(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		CompanyCol companyCol = companyColService.queryById(id);
		req.setAttribute("companyCol", companyCol);
		HttpSession session  = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			String compColId = companyColService.saveCheck(cus.getId(), id);
			if(compColId != null) {
				req.setAttribute("saved", true);
			} else {
				req.setAttribute("saved", false);
			}
		} else {
			req.setAttribute("saved", false);
		}
	}
	/**
	 * 单个公司案例的收藏按钮
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void collectcase(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");  //点击这个案例后将案例id传入进来
		CompanyCaseCol companyCaseCol = companyCaseColService.queryById(id);
		req.setAttribute("companyCaseCol", companyCaseCol);
		HttpSession session  = req.getSession();
		Object obj = session.getAttribute("customer");
		if(obj != null) {
			Customer cus = (Customer)obj;
			String compCaseColId = companyCaseColService.saveCheck(cus.getId(), id);
			if(compCaseColId != null) {
				req.setAttribute("saved", true);
			} else {
				req.setAttribute("saved", false);
			}
		} else {
			req.setAttribute("saved", false);
		}
	}
}
