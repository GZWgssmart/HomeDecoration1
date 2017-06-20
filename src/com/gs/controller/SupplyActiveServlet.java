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

import com.gs.bean.Supply;
import com.gs.bean.SupplyActivity;
import com.gs.service.sup_act.SupplyActivityService;
import com.gs.service.sup_act.SupplyActivityServiceImpl;
import com.gs.util.FileUtil;
import com.gs.util.GetPropUtil;
import com.gs.util.WebUtil;
import com.gs.util.common.Constants;
/**
 * 
 * @author 曾创
 *建材商活动
 */
public class SupplyActiveServlet extends HttpServlet{

	private static final long serialVersionUID = 4406053268852445762L;
	
	private SupplyActivityService supActImpl;
	
	public SupplyActiveServlet() {
		supActImpl = new SupplyActivityServiceImpl();
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
				req.getRequestDispatcher("/classification/businessman/add_act.jsp").forward(req, resp);
			} else if (path.equals("acts")) {
				actPager(req, resp);
				req.getRequestDispatcher("/classification/businessman/act_pager.jsp").forward(req, resp);
			} else if(path.equals("update")) {
				update(req, resp);
			} else if(path.equals("updatepage")) {
				change(req, resp);
				req.getRequestDispatcher("/classification/businessman/act_update.jsp").forward(req, resp);
			} else if(path.equals("show")) {
				change(req, resp);
				req.getRequestDispatcher("/classification/businessman/act_show.jsp").forward(req, resp);
			} else if(path.equals("actpass")) {
				updateStatus(req, resp);
			} else if(path.equals("actstop")) {
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
		SupplyActivity act = supActImpl.queryById(id);
		req.setAttribute("active", act);
	}
	/**
	 * 更改活动状态
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		SupplyActivity act = supActImpl.queryById(id);
		if(act.getStatus().equals("Y")) {
			supActImpl.updateStatus("N", id);
		} else {
			supActImpl.updateStatus("Y", id);
		}
		resp.sendRedirect("acts");
	}
	/**
	 * 建材页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void actPager(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("supply");
		Supply sup = null;
		if(obj != null) {
			sup = (Supply)obj;
		} else {
			resp.sendRedirect("../index/home");
		}
		int count  = supActImpl.countChecked(sup.getId());// 总建材数目
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
		List<SupplyActivity> actList = supActImpl.PagerChecked(sup.getId(), pageNo, pageSize);
		req.setAttribute("actList", actList);
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
			SupplyActivity act = new SupplyActivity();
			try {
				List<FileItem> items = upload.parseRequest(req); // 开始解析request请求, 把表单域和文件域的内容都获取到
				for (FileItem item : items) {
					if (item.isFormField()) { // 判断是否为表单域
						String name = item.getFieldName(); // 获取jsp页面中表单域的name值 
						if (name.equals("name")) { // 如果这个表单域是name表单域
							if(item.getString("utf-8")==null || item.getString("utf-8").trim().equals("")){
								out.write("{\"error\":\"请输入正确的商品名称!!!\"}");
								return;
							}
							act.setName(item.getString("utf-8"));
						} else if(name.equals("des")) {
							act.setDes(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("file")) {
							FileUtil.save(req, item);
							act.setImage("uploads/" + item.getName());
						}
					}
				}
				HttpSession session = req.getSession();
				Object obj = session.getAttribute("supply");
				if(obj != null) {
					Supply sup = (Supply)obj;
					act.setSupply_id(sup.getId());
				}
				if(act.getImage()==null){
					act.setImage("uploads/timg.jpg");
				}
				act.setCreated_time(Calendar.getInstance().getTime());
				supActImpl.add(act);
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
	 * 修改活动
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
			SupplyActivity act = new SupplyActivity();
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
							act.setName(item.getString("utf-8"));
						} else if(name.equals("des")) {
							act.setDes(item.getString("utf-8"));
						} else if(name.equals("id")) {
							act.setId(item.getString("utf-8"));
						}
					} else { // 文件
						String name = item.getFieldName();
						if (name.equals("file")) {
							if(item.getName() != null && !item.getName().equals("")) {
								FileUtil.save(req, item);
								act.setImage("uploads/" + item.getName());
							}
						}
					}
				}
				supActImpl.update(act);
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
