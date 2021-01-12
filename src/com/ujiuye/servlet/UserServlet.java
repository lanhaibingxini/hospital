package com.ujiuye.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ujiuye.entity.Tb_User;
import com.ujiuye.service.UserService;
import com.ujiuye.utils.VerifyCodeUtils;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	UserService us = new UserService();
       
    public UserServlet() {
        super();
    }
    
	//账号校验
	protected void checkUname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		boolean res = us.getUserByUname(uname);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//邮箱校验
	protected void checkUemail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail = request.getParameter("uemail");
		boolean res = us.getUserByUemail(uemail);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}

	//注册功能
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String username = request.getParameter("username");
		String uemail = request.getParameter("uemail");
		Date createdate = new Date();
		Tb_User user = new Tb_User(0, uname, pwd, username, uemail, createdate);
		boolean res = us.regist(user);
		//由于要通过ajax进行提示，所以这里要把得到的数据发送到ajax去，使用PrintWriter接口实现
		PrintWriter out = response.getWriter();
		//发送的是Boolean类型的值
		out.print(res);
		out.close();
	}
	//生成验证码功能
	protected void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置浏览器不缓存信息，验证码功能都需要设置的信息
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        //设置响应内容类型
        response.setContentType("image/jpeg"); 
           
        //生成随机字串 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        //存入会话session，将生成的验证码存进去
        HttpSession session = request.getSession(true); 
        //删除以前的，方便看不清换一张的情况
        session.removeAttribute("verCode");
        //toLowerCase()将字符串转换为小写
        session.setAttribute("verCode", verifyCode.toLowerCase()); 
        //生成图片 
        int w = 100, h = 30; 
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
	}
	
	//验证码校验
	protected void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户输入的验证码
		String code = request.getParameter("code");
		//获取之前的验证码
		HttpSession session = request.getSession();
		String verCode = (String) session.getAttribute("verCode");//verCode必须和之前的设置的一样
		//将用户输入的验证码和之前存的验证码比较是否相等，equalsIgnoreCase()做对比忽略大小写
		boolean res = code.equalsIgnoreCase(verCode);
		//用于ajax校验
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//登录功能
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		boolean res = us.login(uname, pwd);
		HttpSession session = request.getSession();
		if(res) {
			session.setAttribute("uname", uname);
			response.sendRedirect("index.jsp");
		}else {
			session.setAttribute("msg", "账号或密码错误，请重新登录");
			response.sendRedirect("login.jsp");
		}
	}

}
