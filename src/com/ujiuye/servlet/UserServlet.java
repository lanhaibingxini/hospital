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
    
	//�˺�У��
	protected void checkUname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		boolean res = us.getUserByUname(uname);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//����У��
	protected void checkUemail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail = request.getParameter("uemail");
		boolean res = us.getUserByUemail(uemail);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}

	//ע�Ṧ��
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String username = request.getParameter("username");
		String uemail = request.getParameter("uemail");
		Date createdate = new Date();
		Tb_User user = new Tb_User(0, uname, pwd, username, uemail, createdate);
		boolean res = us.regist(user);
		//����Ҫͨ��ajax������ʾ����������Ҫ�ѵõ������ݷ��͵�ajaxȥ��ʹ��PrintWriter�ӿ�ʵ��
		PrintWriter out = response.getWriter();
		//���͵���Boolean���͵�ֵ
		out.print(res);
		out.close();
	}
	//������֤�빦��
	protected void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //�����������������Ϣ����֤�빦�ܶ���Ҫ���õ���Ϣ
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        //������Ӧ��������
        response.setContentType("image/jpeg"); 
           
        //��������ִ� 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        //����Ựsession�������ɵ���֤����ȥ
        HttpSession session = request.getSession(true); 
        //ɾ����ǰ�ģ����㿴���廻һ�ŵ����
        session.removeAttribute("verCode");
        //toLowerCase()���ַ���ת��ΪСд
        session.setAttribute("verCode", verifyCode.toLowerCase()); 
        //����ͼƬ 
        int w = 100, h = 30; 
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
	}
	
	//��֤��У��
	protected void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û��������֤��
		String code = request.getParameter("code");
		//��ȡ֮ǰ����֤��
		HttpSession session = request.getSession();
		String verCode = (String) session.getAttribute("verCode");//verCode�����֮ǰ�����õ�һ��
		//���û��������֤���֮ǰ�����֤��Ƚ��Ƿ���ȣ�equalsIgnoreCase()���ԱȺ��Դ�Сд
		boolean res = code.equalsIgnoreCase(verCode);
		//����ajaxУ��
		PrintWriter out = response.getWriter();
		out.print(res);
		out.close();
	}
	
	//��¼����
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		boolean res = us.login(uname, pwd);
		HttpSession session = request.getSession();
		if(res) {
			session.setAttribute("uname", uname);
			response.sendRedirect("index.jsp");
		}else {
			session.setAttribute("msg", "�˺Ż�������������µ�¼");
			response.sendRedirect("login.jsp");
		}
	}

}
