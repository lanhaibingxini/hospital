package com.ujiuye.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujiuye.entity.Tb_Doctor;
import com.ujiuye.service.DoctorService;
import com.ujiuye.utils.DateUtil;
import com.ujiuye.utils.PageTool;

@WebServlet("/DoctorServlet")
public class DoctorServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public DoctorServlet() {
        super();
    }
    
    DoctorService ds = new DoctorService();

	protected void getDoctorByCurrentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页面
		String currentPage = request.getParameter("currentPage");
		//设置每页显示条数
		int pageSize = 3;
		//获取数据总条数
		int totalCount = ds.totalCount();
		PageTool pt = new PageTool(pageSize, totalCount, currentPage);
		List<Tb_Doctor> list = ds.getDoctorByCurrentPage(pt);
		request.setAttribute("pt", pt);
		request.setAttribute("list", list);
		request.getRequestDispatcher("doctor/index.jsp").forward(request, response);
	}
	
	//单条删除
	protected void delDoctorByDid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int did = Integer.parseInt(request.getParameter("did"));
		boolean res = ds.delDoctorByDid(did);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//批量删除
	protected void delDoctorByDids(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dids = request.getParameter("dids");
		boolean res = ds.delDoctorByDids(dids);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}

	//详情功能
	protected void getDoctorByDid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int did = Integer.parseInt(request.getParameter("did"));
		Tb_Doctor doctor = ds.getDoctorByDid(did);
		request.setAttribute("doctor", doctor);
		String type = request.getParameter("type");
		request.getRequestDispatcher("doctor/"+type+".jsp").forward(request, response);
	}
	
	//编辑功能
	protected void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int did = Integer.parseInt(request.getParameter("did"));
		String dname = request.getParameter("dname");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		int sex = Integer.parseInt(request.getParameter("sex"));
		Date birthday = DateUtil.stringToDate(request.getParameter("birthday"));
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		int department = Integer.parseInt(request.getParameter("department"));
		int education = Integer.parseInt(request.getParameter("education"));
		String remark = request.getParameter("remark");
		Tb_Doctor doctor = new Tb_Doctor(did, dname, cardno, phone, sex, birthday, age, email, department, education, remark);
		boolean res = ds.updateDoctor(doctor);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//新增功能
	protected void insertDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dname = request.getParameter("dname");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		int sex = Integer.parseInt(request.getParameter("sex"));
		Date birthday = DateUtil.stringToDate(request.getParameter("birthday"));
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		int department = Integer.parseInt(request.getParameter("department"));
		int education = Integer.parseInt(request.getParameter("education"));
		String remark = request.getParameter("remark");
		Tb_Doctor doctor = new Tb_Doctor(0, dname, cardno, phone, sex, birthday, age, email, department, education, remark);
		boolean res = ds.insertDoctor(doctor);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	//条件查询
	protected void getDoctorBySth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dname = request.getParameter("dname");
		int department = 0;
		if(request.getParameter("department") != null && !"".equals(request.getParameter("department"))) {
			department = Integer.parseInt(request.getParameter("department"));
		}
		//获取当前页面
		String currentPage = request.getParameter("currentPage");
		//设置每页显示条数
		int pageSize = 3;
		//获取数据总条数
		int totalCount = ds.totalCountBySth(dname, department);
		PageTool pt = new PageTool(pageSize, totalCount, currentPage);
		List<Tb_Doctor> list = ds.getDoctorBySth(dname, department, pt);
		request.setAttribute("pt", pt);
		request.setAttribute("list", list);
		request.setAttribute("dname", dname);
		request.setAttribute("department", department);
		request.getRequestDispatcher("doctor/index.jsp").forward(request, response);
	}
	
	//根据部门获取医生信息
	protected void getDoctorByDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int department = Integer.parseInt(request.getParameter("department"));
		List<Tb_Doctor> list = ds.getDoctorByDepartment(department);
		//将集合转换成json类型的字符串
		ObjectMapper mapper = new ObjectMapper();
		String res = mapper.writeValueAsString(list);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
}
