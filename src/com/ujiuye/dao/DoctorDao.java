package com.ujiuye.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ujiuye.entity.Tb_Doctor;
import com.ujiuye.utils.PageTool;

public class DoctorDao {
	ComboPooledDataSource ds = new ComboPooledDataSource();
	QueryRunner qr = new QueryRunner(ds);

	// 获取数据条数
	public int totalCount() {
		int totalcount = 0;
		String sql = "select count(*) from tb_doctor";
		try {
			// 必须先转换成long类型，再转换成int类型
			totalcount = (int) (long) qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalcount;
	}

	// 分页查询
	public List<Tb_Doctor> getDoctorByCurrentPage(PageTool pt) {
		List<Tb_Doctor> list = null;
		String sql = "select * from tb_doctor limit ?,?";
		try {
			list = qr.query(sql, new BeanListHandler<>(Tb_Doctor.class), pt.getStartIndex(), pt.getPageSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 单条删除
	public int delDoctorByDid(int did) {
		int rows = 0;
		String sql = "delete from tb_doctor where did=?";
		try {
			rows = qr.update(sql, did);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	// 批量删除
	public int delDoctorByDids(String dids) {
		int rows = 0;
		String sql = "delete from tb_doctor where did in(" + dids + ")";
		try {
			rows = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	// 根据id查询信息
	public Tb_Doctor getDoctorByDid(int did) {
		Tb_Doctor doctor = null;
		String sql = "select * from tb_doctor where did=?";
		try {
			doctor = qr.query(sql, new BeanHandler<>(Tb_Doctor.class), did);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctor;
	}

	// 更改信息
	public int updateDoctor(Tb_Doctor doctor) {
		int rows = 0;
		String sql = "update tb_doctor set dname=?,cardno=?,phone=?,sex=?,birthday=?,age=?,email=?,"
				+ "department=?,education=?,remark=? where did=?";
		try {
			rows = qr.update(sql, doctor.getDname(), doctor.getCardno(), doctor.getPhone(), doctor.getSex(),
					doctor.getBirthday(), doctor.getAge(), doctor.getEmail(), doctor.getDepartment(),
					doctor.getEducation(), doctor.getRemark(), doctor.getDid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	// 添加信息
	public int insertDoctor(Tb_Doctor doctor) {
		int rows = 0;
		String sql = "insert into tb_doctor(dname,cardno,phone,sex,birthday,age,email,department,education,remark) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		try {
			rows = qr.update(sql, doctor.getDname(), doctor.getCardno(), doctor.getPhone(), doctor.getSex(),
					doctor.getBirthday(), doctor.getAge(), doctor.getEmail(), doctor.getDepartment(),
					doctor.getEducation(), doctor.getRemark());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	// 条件查询的数据条数
	public int totalCountBySth(String dname, int department) {
		int totalCount = 0;
		String sql = "select count(*) from tb_doctor where 1=1";
		if(dname != null && !"".equals(dname)) {
			sql += " and dname like '%"+dname+"%'";
		}
		if(department != 0) {
			sql += " and department="+department;
		}
		try {
			totalCount = (int)(long)qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	// 条件查询
	public List<Tb_Doctor> getDoctorBySth(String dname, int department, PageTool pt) {
		List<Tb_Doctor> list = null;
		String sql = "select * from tb_doctor where 1=1";
		if(dname != null && !"".equals(dname)) {
			sql += " and dname like '%"+dname+"%'";
		}
		if(department != 0) {
			sql += " and department="+department;
		}
		sql += " limit ?,?";
		try {
			list = qr.query(sql, new BeanListHandler<>(Tb_Doctor.class), pt.getStartIndex(), pt.getPageSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//根据部门获取医生信息
	public List<Tb_Doctor> getDoctorByDepartment(int department){
		List<Tb_Doctor> list = null;
		String sql = "select * from tb_doctor where department=?";
		try {
			list = qr.query(sql, new BeanListHandler<>(Tb_Doctor.class), department);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
