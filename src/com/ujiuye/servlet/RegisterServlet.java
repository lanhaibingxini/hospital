package com.ujiuye.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ujiuye.entity.Tb_Doctor;
import com.ujiuye.entity.Tb_Register;
import com.ujiuye.entity.Tb_Register;
import com.ujiuye.service.DoctorService;
import com.ujiuye.service.RegisterService;
import com.ujiuye.utils.DateUtil;
import com.ujiuye.utils.PageTool;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

    RegisterService rs = new RegisterService();
    DoctorService ds = new DoctorService();
    //分页查询
	protected void getRegisterByCurrentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页面
		String currentPage = request.getParameter("currentPage");
		//设置每页显示条数
		int pageSize = 3;
		//获取数据总条数
		int totalCount = rs.totalCount();
		PageTool pt = new PageTool(pageSize, totalCount, currentPage);
		List<Tb_Register> list = rs.getRegisterByCurrentPage(pt);
		request.setAttribute("pt", pt);
		request.setAttribute("list", list);
		request.getRequestDispatcher("register/index.jsp").forward(request, response);
	}
	//挂号功能
	protected void insertRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rname = request.getParameter("rname");
		String idCard = request.getParameter("idCard");
		String siNumber = request.getParameter("siNumber");
		double registerMoney = Double.parseDouble(request.getParameter("registerMoney"));
		String phone = request.getParameter("phone");
		int isPay =  Integer.parseInt(request.getParameter("isPay"));
		int sex = Integer.parseInt(request.getParameter("sex"));
		int age = Integer.parseInt(request.getParameter("age"));
		int consultation = Integer.parseInt(request.getParameter("consultation"));
		int did = Integer.parseInt(request.getParameter("did"));
		String remark = request.getParameter("remark");
		Date rtime = new Date();
		int statu = 0;
		Tb_Register register = new Tb_Register(0, rname, idCard, siNumber, registerMoney, phone, isPay, sex, age, consultation, did, remark, rtime, statu);
		boolean res = rs.insertRegister(register);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//详情功能
	protected void getRegisterByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		Tb_Register r = rs.getRegisterByRid(rid);
		request.setAttribute("r", r);
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		List<Tb_Doctor> list = ds.getDoctorByDepartment(r.getDoctor().getDepartment());
		request.setAttribute("list", list);
		request.getRequestDispatcher("register/"+type+".jsp").forward(request, response);
	}
	
	//编辑功能
	protected void updateRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		String rname = request.getParameter("rname");
		String idCard = request.getParameter("idCard");
		String siNumber = request.getParameter("siNumber");
		double registerMoney = Double.parseDouble(request.getParameter("registerMoney"));
		String phone = request.getParameter("phone");
		int isPay =  Integer.parseInt(request.getParameter("isPay"));
		int sex = Integer.parseInt(request.getParameter("sex"));
		int age = Integer.parseInt(request.getParameter("age"));
		int consultation = Integer.parseInt(request.getParameter("consultation"));
		int did = Integer.parseInt(request.getParameter("did"));
		String remark = request.getParameter("remark");
		Date rtime = new Date();
		int statu = 0;
		Tb_Register register = new Tb_Register(rid, rname, idCard, siNumber, registerMoney, phone, isPay, sex, age, consultation, did, remark, rtime, statu);
		boolean res = rs.updateRegister(register);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
}
