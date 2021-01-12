package com.ujiuye.service;

import java.util.List;

import com.ujiuye.dao.medicineDao;
import com.ujiuye.entity.Tb_Medicine;
import com.ujiuye.utils.PageTool;

public class medicineService {
	medicineDao md = new medicineDao();

	// ��ȡ��������
	public int totalCount() {
		return md.totalCount();
	}

	// ��ҳ��ѯ
	public List<Tb_Medicine> getMedicineByCurrentPage(PageTool pt) {
		return md.getMedicineByCurrentPage(pt);
	}
	
	// �����Ϣ
	public boolean insertMedicine(Tb_Medicine medicine) {
		return md.insertMedicine(medicine) == 0 ? false : true;
	}
	
	// ����id��ѯ��Ϣ
	public Tb_Medicine getMedicineByMid(int mid) {
		return md.getMedicineByMid(mid);
	}
	
	// ������Ϣ
	public boolean updateMedicine(Tb_Medicine medicine) {
		return md.updateMedicine(medicine) == 0 ? false : true;
	}
	
	// ��ȡ������ѯ����������
	public int totalCountBySth(String mname, int type) {
		return md.totalCountBySth(mname, type);
	}
	
	// ������ѯ
	public List<Tb_Medicine> getMedicineBySth(String mname, int type, PageTool pt) {
		return md.getMedicineBySth(mname, type, pt);
	}
	
	//����ɾ��
	public boolean delMedicineByMid(int mid) {
		return md.delMedicineByMid(mid) == 0 ? false : true;
	}
	
	//����ɾ��
	public boolean delMedicineByMids(String mids){
		return md.delMedicineByMids(mids) == 0 ? false : true;
	}
}
