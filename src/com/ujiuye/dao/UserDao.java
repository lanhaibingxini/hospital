package com.ujiuye.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ujiuye.entity.Tb_User;

public class UserDao {
	ComboPooledDataSource ds = new ComboPooledDataSource();
	QueryRunner qr = new QueryRunner(ds);
	//根据账号查询信息
	public Tb_User getUserByUname(String uname) {
		Tb_User user = null;
		String sql = "select * from tb_user where uname=?";
		try {
			user = qr.query(sql, new BeanHandler<>(Tb_User.class), uname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//根据邮箱查询信息
	public Tb_User getUserByUemail(String uemail) {
		Tb_User user = null;
		String sql = "select * from tb_user where uemail=?";
		try {
			user = qr.query(sql, new BeanHandler<>(Tb_User.class), uemail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//注册功能
	public int regist(Tb_User user) {
		int rows = 0;
		String sql = "insert into tb_user(uname,pwd,username,uemail,createdate) values(?,?,?,?,?)";
		try {
			rows = qr.update(sql, user.getUname(), user.getPwd(), user.getUsername(), user.getUemail(), user.getCreatedate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	//登录功能
	public Tb_User login(String uname, String pwd) {
		Tb_User user = null;
		String sql = "select * from tb_user where uname=? and pwd=?";
		try {
			user = qr.query(sql, new BeanHandler<>(Tb_User.class), uname, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
