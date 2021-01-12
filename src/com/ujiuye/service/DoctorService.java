package com.ujiuye.service;

import java.util.List;

import com.ujiuye.dao.DoctorDao;
import com.ujiuye.entity.Tb_Doctor;
import com.ujiuye.utils.PageTool;

public class DoctorService {
	DoctorDao dd = new DoctorDao();

	// 获取数据条数
	public int totalCount() {
		return dd.totalCount();
	}

	// 分页查询
	public List<Tb_Doctor> getDoctorByCurrentPage(PageTool pt) {
		return dd.getDoctorByCurrentPage(pt);
	}

	// 单条删除
	public boolean delDoctorByDid(int did) {
		return dd.delDoctorByDid(did) == 0 ? false : true;
	}

	// 批量删除
	public boolean delDoctorByDids(String dids) {
		return dd.delDoctorByDids(dids) == 0 ? false : true;
	}

	// 根据id查询信息
	public Tb_Doctor getDoctorByDid(int did) {
		return dd.getDoctorByDid(did);
	}

	// 更改信息
	public boolean updateDoctor(Tb_Doctor doctor) {
		return dd.updateDoctor(doctor) == 0 ? false : true;
	}

	// 添加信息
	public boolean insertDoctor(Tb_Doctor doctor) {
		return dd.insertDoctor(doctor) == 0 ? false : true;
	}

	// 条件查询的数据条数
	public int totalCountBySth(String dname, int department) {
		return dd.totalCountBySth(dname, department);
	}

	// 条件查询
	public List<Tb_Doctor> getDoctorBySth(String dname, int department, PageTool pt) {
		return dd.getDoctorBySth(dname, department, pt);
	}
	
	//根据部门获取医生信息
	public List<Tb_Doctor> getDoctorByDepartment(int department){
		return dd.getDoctorByDepartment(department);
	}
}
