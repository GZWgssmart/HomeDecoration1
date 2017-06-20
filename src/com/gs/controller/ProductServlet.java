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

import com.gs.util.PathUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gs.bean.Product;
import com.gs.bean.Supply;
import com.gs.service.pro.ProductService;
import com.gs.service.pro.ProductServiceImpl;
import com.gs.util.FileUtil;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
/**
 * 
 * @author 曾创
 *商品的servlet
 */
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = -7286533973913166154L;
	
	private ProductService proImpl;
	
	public ProductServlet() {
		proImpl = new ProductServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("supply");
		if(obj == null) {
			resp.sendRedirect("../index/home");
		} else {
			String path = WebUtil.getUriMethod(req);
			if(path.equals("add")) {
				add(req, resp);
			} else if (path.equals("addpage")) {
				req.getRequestDispatcher("/classification/businessman/add_pro.jsp").forward(req, resp);
			} else if (path.equals("pros")) {
				proPager(req, resp);
				req.getRequestDispatcher("/classification/businessman/sup_pros.jsp").forward(req, resp);
			} else if(path.equals("update")) {
				update(req, resp);
			} else if(path.equals("updatepage")) {
				change(req, resp);
				req.getRequestDispatcher("/classification/businessman/pro_update.jsp").forward(req, resp);
			} else if(path.equals("show")) {
				change(req, resp);
				req.getRequestDispatcher("/classification/businessman/pro_show.jsp").forward(req, resp);
			} else if(path.equals("propass")) {
				updateStatus(req, resp);
			} else if(path.equals("prostop")) {
				updateStatus(req, resp);
			}
		}
	}
	/**
	 * 页面跳转传值
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void change(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Product pro = proImpl.queryById(id);
		req.setAttribute("product", pro);
	}
	/**
	 * 更改商品状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Product act = proImpl.queryById(id);
		if(act.getStatus().equals("Y")) {
			proImpl.updateStatus("N", id);
		} else {
			proImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("pros");
	}
	/**
	 * 建材页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void proPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("supply");
		Supply sup = null;
		if(obj != null) {
			sup = (Supply)obj;
		} else {
			resp.sendRedirect("../index/home");
		}
		int count  = proImpl.countChecked(sup.getId());// 总建材数目
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
		List<Product> proList = proImpl.queryByPagerChecked(sup.getId(), pageNo, pageSize);
		req.setAttribute("proList", proList);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageCount", pageCount);
	}
	/**
	 * 添加商品
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("hiding")
	private void add(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 判断表单是否有文件上传
		PrintWriter out = resp.getWriter();
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			Product pro = new Product();
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					if (item.isFormField()) { // 判断是否为表单域
						String name = item.getFieldName(); // 获取jsp页面中表单域的name值 
						if (name.equals("name")) { // 如果这个表单域是name表单域
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入商品名称!!!\"}");
								return;
							}
							pro.setName(item.getString("utf-8"));
						} else if (name.equals("price")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入商品价格!!!\"}");
								return;
							}
							Float f = 0.0f;
							try{
								f = Float.valueOf(item.getString("utf-8"));
							} catch(NumberFormatException e) {
								out.write("{\"error\":\"请输入正确商品价格!!!\"}");
								return;
							}
							pro.setPrice(f);
						} else if(name.equals("sale_price")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入商品价格!!!\"}");
								return;
							}
							Float f = 0.0f;
							try{
								f = Float.valueOf(item.getString("utf-8"));
							} catch(NumberFormatException e) {
								out.write("{\"error\":\"请输入正确商品价格!!!\"}");
								return;
							}
							pro.setSale_price(f);
						} else if(name.equals("des")) {
							pro.setDes(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("file")) {
							if(item.getName() != null && !item.getName().equals("")) {
								FileUtil.save(req, item);
								pro.setImage("uploads/" + item.getName());
							}
						}
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("supply");
				if(obj != null) {
					Supply sup = (Supply)obj;
					pro.setSupply_id(sup.getId());
				}
				if(pro.getImage()==null){
					pro.setImage("uploads/timg.jpg");
				}
				pro.setCreated_time(Calendar.getInstance().getTime());
				proImpl.add(pro);
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"添加成功\"}");
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 修改商品
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("hiding")
	private void update(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 判断表单是否有文件上传
		PrintWriter out = resp.getWriter();
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 文件上传的工厂类，工厂类可以指定文件上传的临时目录
			ServletFileUpload upload = new ServletFileUpload(factory); // ServletFileUpload是用来解析request请求，并把表单域和文件输入获取到
			upload.setHeaderEncoding("utf-8");
			Product pro = new Product();
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					if (item.isFormField()) { // 判断是否为表单域
						String name = item.getFieldName(); // 获取jsp页面中表单域的name值 
						if (name.equals("name")) { // 如果这个表单域是name表单域
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入商品名称!!!\"}");
								return;
							}
							pro.setName(item.getString("utf-8"));
						} else if (name.equals("price")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入商品价格!!!\"}");
								return;
							}
							Float f = 0.0f;
							try{
								f = Float.valueOf(item.getString("utf-8"));
							} catch(NumberFormatException e) {
								out.write("{\"error\":\"请输入正确商品价格!!!\"}");
								return;
							}
							pro.setPrice(f);
						} else if(name.equals("sale_price")) {
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入商品价格!!!\"}");
								return;
							}
							Float f = 0.0f;
							try{
								f = Float.valueOf(item.getString("utf-8"));
							} catch(NumberFormatException e) {
								out.write("{\"error\":\"请输入正确商品价格!!!\"}");
								return;
							}
							pro.setSale_price(f);
						} else if(name.equals("des")) {
							pro.setDes(item.getString("utf-8"));
						} else if(name.equals("id")) {
							pro.setId(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("file")) {
							FileUtil.save(req, item);
							pro.setImage("uploads/" + item.getName());
						}
					}
				}
				pro.setCreated_time(Calendar.getInstance().getTime());
				proImpl.update(pro);
				resp.setContentType(Constants.JSON_CONTENT_TYPE);
				out.write("{\"error\":\"修改成功\"}");
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
