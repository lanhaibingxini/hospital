package com.ujiuye.service;

import java.util.List;

import com.ujiuye.dao.DoctorDao;
import com.ujiuye.entity.Tb_Doctor;
import com.ujiuye.utils.PageTool;

public class DoctorService {
	DoctorDao dd = new DoctorDao();

	// ��ȡ��������
	public int totalCount() {
		return dd.totalCount();
	}

	// ��ҳ��ѯ
	public List<Tb_Doctor> getDoctorByCurrentPage(PageTool pt) {
		return dd.getDoctorByCurrentPage(pt);
	}

	// ����ɾ��
	public boolean delDoctorByDid(int did) {
		return dd.delDoctorByDid(did) == 0 ? false : true;
	}

	// ����ɾ��
	public boolean delDoctorByDids(String dids) {
		return dd.delDoctorByDids(dids) == 0 ? false : true;
	}

	// ����id��ѯ��Ϣ
	public Tb_Doctor getDoctorByDid(int did) {
		return dd.getDoctorByDid(did);
	}

	// ������Ϣ
	public boolean updateDoctor(Tb_Doctor doctor) {
		return dd.updateDoctor(doctor) == 0 ? false : true;
	}

	// �����Ϣ
	public boolean insertDoctor(Tb_Doctor doctor) {
		return dd.insertDoctor(doctor) == 0 ? false : true;
	}

	// ������ѯ����������
	public int totalCountBySth(String dname, int department) {
		return dd.totalCountBySth(dname, department);
	}

	// ������ѯ
	public List<Tb_Doctor> getDoctorBySth(String dname, int department, PageTool pt) {
		return dd.getDoctorBySth(dname, department, pt);
	}
	
	//���ݲ��Ż�ȡҽ����Ϣ
	public List<Tb_Doctor> getDoctorByDepartment(int department){
		return dd.getDoctorByDepartment(department);
	}
}
