package com.gs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.Company;
import com.gs.bean.CompanyActivity;
import com.gs.bean.CompanyCase;
import com.gs.bean.Designer;
import com.gs.bean.DesignerCase;
import com.gs.bean.Product;
import com.gs.bean.SupplyActivity;
import com.gs.service.com_act.CompanyActivityService;
import com.gs.service.com_act.CompanyActivityServiceImpl;
import com.gs.service.comp.CompanyBaseService;
import com.gs.service.comp.CompanyBaseServiceImpl;
import com.gs.service.company_case.CompanyCaseBaseService;
import com.gs.service.company_case.CompanyCaseBaseServiceImpl;
import com.gs.service.des.DesignerService;
import com.gs.service.des.DesignerServiceImpl;
import com.gs.service.des_case.DesignerCaseService;
import com.gs.service.des_case.DesignerCaseServiceImpl;
import com.gs.service.pro.ProductService;
import com.gs.service.pro.ProductServiceImpl;
import com.gs.service.sup_act.SupplyActivityService;
import com.gs.service.sup_act.SupplyActivityServiceImpl;
import com.gs.util.WebUtil;
/**
 * 
 * @author 曾创
 *专门显示首页内容的servlet
 */
public class BHIndexServlet extends HttpServlet{
	
	private static final long serialVersionUID = -6633824472926204672L;
	
	private DesignerService desImpl;
	private CompanyBaseService companyService;
	private ProductService proImpl;
	private DesignerCaseService desCaseImpl;
	private	CompanyCaseBaseService companyCaseService;
	private SupplyActivityService actImpl;
	private CompanyActivityService companyActiveService;
	
	public BHIndexServlet() {
		desImpl = new DesignerServiceImpl();
		companyService = new CompanyBaseServiceImpl();
		proImpl = new ProductServiceImpl();
		desCaseImpl = new DesignerCaseServiceImpl();
		companyCaseService = new CompanyCaseBaseServiceImpl();
		actImpl = new SupplyActivityServiceImpl();
		companyActiveService = new CompanyActivityServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = WebUtil.getUriMethod(req);
		if(path.equals("home")) {
			desTop5(req, resp);
			company(req,resp);
			proTop3(req, resp);
			desCaseTop3(req, resp);
			supActTop3(req, resp);
			casepage(req, resp);
			companyCases(req,resp);
			companyActives(req, resp);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} else if(path.equals("404")) {
			req.getRequestDispatcher("/404.jsp").forward(req, resp);
		}
	}
	/**
	 * top3精选建材商活动
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supActTop3(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<SupplyActivity> actTopList = actImpl.actTop3();
		req.setAttribute("actTopList", actTopList);
	}
	/**
	 * 前3个案例的分页
	 * @throws ServletException 
	 */
	public void casepage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		int total = companyCaseService.count();
		int pageSize = 3;
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
	 * 获取前3个装修公司的活动案例
	 * @param req
	 * @param resp
	 */
	public void companyActives(HttpServletRequest req,HttpServletResponse resp){
		List<CompanyActivity> companyActive = companyActiveService.queryActiveTop3();
		 req.setAttribute("companyActives", companyActive);
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
	 * 获取前6个装修公司
	 * @param req
	 * @param resp
	 */
	public void company(HttpServletRequest req,HttpServletResponse resp){
		 List<Company> companys = companyService.queryCompanyTop6();
		 req.setAttribute("companys", companys);
	}
	/**
	 * top3精选建材
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proTop3(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<Product> proTopList = proImpl.desTop3();
		req.setAttribute("proTopList", proTopList);
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
}
