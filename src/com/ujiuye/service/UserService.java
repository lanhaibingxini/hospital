package com.ujiuye.service;

import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.Tb_User;

public class UserService {

	UserDao ud = new UserDao();

	// 根据账号查询信息
	public boolean getUserByUname(String uname) {
		return ud.getUserByUname(uname) == null ? true : false;
	}
	
	//根据邮箱查询信息
	public boolean getUserByUemail(String uemail) {
		return ud.getUserByUemail(uemail) == null ? true : false;
	}

	//注册功能
	public boolean regist(Tb_User user) {
		return ud.regist(user) != 0 ? true : false;
	}
	
	//登录功能
	public boolean login(String uname, String pwd) {
		return ud.login(uname, pwd) == null ? false :true;
	}
}
