package com.ujiuye.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ujiuye.entity.Tb_Doctor;
import com.ujiuye.entity.Tb_Register;
import com.ujiuye.utils.PageTool;

public class RegisterDao {
	ComboPooledDataSource ds = new ComboPooledDataSource();
	QueryRunner qr = new QueryRunner(ds);
	DoctorDao dd = new DoctorDao();

	// 获取数据条数
	public int totalCount() {
		int totalcount = 0;
		String sql = "select count(*) from tb_register";
		try {
			// 必须先转换成long类型，再转换成int类型
			totalcount = (int) (long) qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalcount;
	}

	// 分页查询
	public List<Tb_Register> getRegisterByCurrentPage(PageTool pt) {
		List<Tb_Register> list = null;
		String sql = "select * from tb_register limit ?,?";
		try {
			list = qr.query(sql, new BeanListHandler<>(Tb_Register.class), pt.getStartIndex(), pt.getPageSize());
			//利用已经查到的挂号信息中的did来查询对应的医生的信息
			for (Tb_Register register : list) {
				int did = register.getDid();
				Tb_Doctor doctor = dd.getDoctorByDid(did);
				register.setDoctor(doctor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 添加信息
	public int insertRegister(Tb_Register register) {
		int rows = 0;
		String sql = "insert into tb_register(rname,idCard,siNumber,registerMoney,phone,isPay,sex,age,consultation,did,"
				+ "remark,rtime,statu) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			rows = qr.update(sql, register.getRname(), register.getIdCard(), register.getSiNumber(), 
					register.getRegisterMoney(), register.getPhone(), register.getIsPay(), register.getSex(), 
					register.getAge(), register.getConsultation(), register.getDid(), register.getRemark(), 
					register.getRtime(), register.getStatu());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	//根据rid展示挂号信息
	public Tb_Register getRegisterByRid(int rid) {
		Tb_Register register = null;
		String sql = "select * from tb_register where rid=?";
		try {
			register = qr.query(sql, new BeanHandler<>(Tb_Register.class), rid);
			//获取did
			int did = register.getDid();
			//调用方法，获取doctor对象
			Tb_Doctor doctor = dd.getDoctorByDid(did);
			//将doctor对象存储在register中，这样就可以通过register调用到doctor中的变量
			register.setDoctor(doctor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return register;
	}
	
	//编辑功能
	public int updateRegister(Tb_Register register) {
		int rows = 0;
		String sql = "update tb_register set rname=?,idCard=?,siNumber=?,registerMoney=?,phone=?,isPay=?,sex=?,age=?,"
				+ "consultation=?,did=?,remark=?,rtime=?,statu=? where rid=?";
		try {
			rows = qr.update(sql, register.getRname(), register.getIdCard(), register.getSiNumber(), 
					register.getRegisterMoney(), register.getPhone(), register.getIsPay(), register.getSex(), 
					register.getAge(), register.getConsultation(), register.getDid(), register.getRemark(), 
					register.getRtime(), register.getStatu(), register.getRid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
}
