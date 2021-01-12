package com.ujiuye.service;

import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.Tb_User;

public class UserService {

	UserDao ud = new UserDao();

	// �����˺Ų�ѯ��Ϣ
	public boolean getUserByUname(String uname) {
		return ud.getUserByUname(uname) == null ? true : false;
	}
	
	//���������ѯ��Ϣ
	public boolean getUserByUemail(String uemail) {
		return ud.getUserByUemail(uemail) == null ? true : false;
	}

	//ע�Ṧ��
	public boolean regist(Tb_User user) {
		return ud.regist(user) != 0 ? true : false;
	}
	
	//��¼����
	public boolean login(String uname, String pwd) {
		return ud.login(uname, pwd) == null ? false :true;
	}
}
