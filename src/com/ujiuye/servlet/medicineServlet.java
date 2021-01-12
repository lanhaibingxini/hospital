package com.ujiuye.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ujiuye.entity.Tb_Doctor;
import com.ujiuye.entity.Tb_Medicine;
import com.ujiuye.service.medicineService;
import com.ujiuye.utils.PageTool;
@MultipartConfig
@WebServlet("/medicineServlet")
public class medicineServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public medicineServlet() {
        super();
    }
    
    medicineService ms = new medicineService();

	protected void getMedicineByCurrentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页面
		String currentPage = request.getParameter("currentPage");
		//设置每页显示条数
		int pageSize = 3;
		//获取数据总条数
		int totalCount = ms.totalCount();
		PageTool pt = new PageTool(pageSize, totalCount, currentPage);
		List<Tb_Medicine> list = ms.getMedicineByCurrentPage(pt);
		request.setAttribute("pt", pt);
		request.setAttribute("list", list);
		request.getRequestDispatcher("medicine/index.jsp").forward(request, response);
	}
	
	//新增功能
	protected void insertMedicine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double inPrice = Double.parseDouble(request.getParameter("inPrice"));
		double salPrice = Double.parseDouble(request.getParameter("salPrice"));
		String mname = request.getParameter("mname");
		int type = Integer.parseInt(request.getParameter("type"));
		String descs = request.getParameter("descs");
		int qualityDate = Integer.parseInt(request.getParameter("qualityDate"));
		String description = request.getParameter("description");
		String produceFirm = request.getParameter("produceFirm");
		String readme = request.getParameter("readme");
		String remark = request.getParameter("remark");
		
		String picture = "";
		//上传图片
		Part part = request.getPart("picture");
		String msg = part.getHeader("Content-Disposition");
		picture = UUID.randomUUID() +  msg.substring(msg.indexOf("filename") + 10, msg.length() - 1);
		//上传路径
		String path = "D:/hospital_picture/medicine";
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		part.write(path + "/" + picture);
		
		Tb_Medicine medicine = new Tb_Medicine(0, picture, inPrice, salPrice, mname, type, descs, qualityDate, description, produceFirm, readme, remark);
		boolean res = ms.insertMedicine(medicine);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//详情功能
	protected void getMedicineByMid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.parseInt(request.getParameter("mid"));
		Tb_Medicine medicine = ms.getMedicineByMid(mid);
		request.setAttribute("medicine", medicine);
		String type = request.getParameter("type");
		request.getRequestDispatcher("medicine/"+type+".jsp").forward(request, response);
	}

	// 编辑功能
	protected void updateMedicine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int mid = Integer.parseInt(request.getParameter("mid"));
		double inPrice = Double.parseDouble(request.getParameter("inPrice"));
		double salPrice = Double.parseDouble(request.getParameter("salPrice"));
		String mname = request.getParameter("mname");
		int type = Integer.parseInt(request.getParameter("type"));
		String descs = request.getParameter("descs");
		int qualityDate = Integer.parseInt(request.getParameter("qualityDate"));
		String description = request.getParameter("description");
		String produceFirm = request.getParameter("produceFirm");
		String readme = request.getParameter("readme");
		String remark = request.getParameter("remark");

		String picture = "";
		// 上传图片
		Part part = request.getPart("picture");
		String msg = part.getHeader("Content-Disposition");
		picture = UUID.randomUUID() + msg.substring(msg.indexOf("filename") + 10, msg.length() - 1);
		// 上传路径
		String path = "D:/hospital_picture/medicine";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		part.write(path + "/" + picture);

		Tb_Medicine medicine = new Tb_Medicine(mid, picture, inPrice, salPrice, mname, type, descs, qualityDate,
				description, produceFirm, readme, remark);
		boolean res = ms.updateMedicine(medicine);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	//条件查询
	protected void getMedicineBySth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mname = request.getParameter("mname");
		int type = 0;
		if(request.getParameter("type") != null && !"".equals(request.getParameter("type"))) {
			type = Integer.parseInt(request.getParameter("type"));
		}
		//获取当前页面
		String currentPage = request.getParameter("currentPage");
		//设置每页显示条数
		int pageSize = 3;
		//获取数据总条数
		int totalCount = ms.totalCountBySth(mname, type);
		PageTool pt = new PageTool(pageSize, totalCount, currentPage);
		List<Tb_Medicine> list = ms.getMedicineBySth(mname, type, pt);
		request.setAttribute("pt", pt);
		request.setAttribute("list", list);
		request.setAttribute("mname", mname);
		request.setAttribute("type", type);
		request.getRequestDispatcher("medicine/index.jsp").forward(request, response);
	}
	
	//单条删除功能
	protected void delMedicineByMid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.parseInt(request.getParameter("mid"));
		boolean res = ms.delMedicineByMid(mid);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//批量删除功能
	protected void delMedicineByMids(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mids = request.getParameter("mids");
		boolean res = ms.delMedicineByMids(mids);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}

}
