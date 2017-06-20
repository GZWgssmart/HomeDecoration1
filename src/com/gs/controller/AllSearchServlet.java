package com.gs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.Company;
import com.gs.bean.Designer;
import com.gs.bean.Product;
import com.gs.bean.Supply;
import com.gs.service.comp.CompanyBaseService;
import com.gs.service.comp.CompanyBaseServiceImpl;
import com.gs.service.des.DesignerService;
import com.gs.service.des.DesignerServiceImpl;
import com.gs.service.pro.ProductService;
import com.gs.service.pro.ProductServiceImpl;
import com.gs.service.supply.SupplyBaseService;
import com.gs.service.supply.SupplyBaseServiceImpl;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;

import net.sf.json.JSONArray;

public class AllSearchServlet extends HttpServlet{
	private CompanyBaseService companyService;
	private DesignerService designerService;
	private SupplyBaseService supplyBaseService;
	private ProductService productServicel;

	private static final long serialVersionUID = -302659479635298079L;

	public AllSearchServlet(){
		companyService =  new CompanyBaseServiceImpl();
		designerService = new DesignerServiceImpl();
		supplyBaseService = new SupplyBaseServiceImpl();
		productServicel = new ProductServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Mothed = WebUtil.getUriMethod(request);
		if(Mothed.equals("com")){
			com(request,response);
		}else if(Mothed.equals("combtn")){
			combtn(request,response);
		}else if(Mothed.equals("sup")){
			sup(request,response);
		}else if(Mothed.equals("supbtn")){
			supbtn(request,response);
		}else if(Mothed.equals("des")){
			des(request,response);
		}else if(Mothed.equals("desbtn")){
			desbtn(request,response);
		}else if(Mothed.equals("pro")){
			pro(request,response);
		}else if(Mothed.equals("probtn")){
			probtn(request,response);
		}
	}
	/**
	 * 按钮搜索建材商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void probtn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = productServicel.searchConInt(search);
		int pageSize = 10;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Product> Products = productServicel.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Product product : Products){
			String name = product.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("search", search);
		request.setAttribute("pro", Products);
		request.setAttribute("prototalPage", totalPage);
		request.setAttribute("propageNo", pageNo);
		request.setAttribute("prototal", total);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}
	/**
	 * 搜索框建材商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取搜索框传递过来的值
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = productServicel.searchConInt(search);
		int pageSize = 10;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Product> Products = productServicel.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Product product : Products){
			String name = product.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
			request.setAttribute("prototalPage", totalPage);
			request.setAttribute("propageNo", pageNo);
			request.setAttribute("prototal", total);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 按钮搜索设计师
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void desbtn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = designerService.searchConInt(search);
		int pageSize = 10;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Designer> Designers = designerService.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Designer designer : Designers){
			String name = designer.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("search", search);
		request.setAttribute("des", Designers);
		request.setAttribute("destotalPage", totalPage);
		request.setAttribute("despageNo", pageNo);
		request.setAttribute("destotal", total);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	/**
	 * 搜索框设计师
	 * @param request
	 * @param response
	 */
	private void des(HttpServletRequest request, HttpServletResponse response) {
		//获取搜索框传递过来的值
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = designerService.searchConInt(search);
		int pageSize = 10;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Designer> Designers = designerService.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Designer designer : Designers){
			String name = designer.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
			request.setAttribute("despageCount", totalPage);
			request.setAttribute("despageNo", pageNo);
			request.setAttribute("destotal", total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按钮搜索建材商
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void supbtn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = supplyBaseService.searchConInt(search);
		int pageSize = 10;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Supply> Supplys = supplyBaseService.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Supply supply : Supplys){
			String name = supply.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("search", search);
		request.setAttribute("sup", Supplys);
		request.setAttribute("suppageCount", totalPage);
		request.setAttribute("suppageNo", pageNo);
		request.setAttribute("suptotal", total);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	/**
	 *搜索框建材商
	 * @param request
	 * @param response
	 */
	private void sup(HttpServletRequest request, HttpServletResponse response) {
		//获取搜索框传递过来的值
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = supplyBaseService.searchConInt(search);
		int pageSize = 10;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Supply> Supplys = supplyBaseService.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Supply supply : Supplys){
			String name = supply.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
			request.setAttribute("suppageCount", totalPage);
			request.setAttribute("suppageNo", pageNo);
			request.setAttribute("suptotal", total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按钮搜索装修公司
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void combtn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = companyService.searchConInt(search);
		int pageSize = 6;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Company> Companys = companyService.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Company company : Companys){
			String name = company.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("search", search);
		request.setAttribute("com", Companys);
		request.setAttribute("compageCount", totalPage);
		request.setAttribute("compageNo", pageNo);
		request.setAttribute("comtotal", total);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	/**
	 * 搜索框装修公司
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void com(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取搜索框传递过来的值
		response.setContentType(Constants.JSON_CONTENT_TYPE);
		String search = request.getParameter("search");
		int total = companyService.searchConInt(search);
		int pageSize = 10;
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
		int pageNo = 1;
		String pageStr = request.getParameter("pageNo");
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
		List<Company> Companys = companyService.searchCom(pageNo, pageSize, search);
		List<String> lists = new ArrayList<String>();
		for(Company company : Companys){
			String name = company.getName();
			lists.add(name);
		}
		try {
			response.getWriter().write(JSONArray.fromObject(lists).toString());
			request.setAttribute("compageCount", totalPage);
			request.setAttribute("compageNo", pageNo);
			request.setAttribute("comtotal", total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
