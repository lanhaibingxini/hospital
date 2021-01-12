package com.ujiuye.service;

import java.util.List;

import com.ujiuye.dao.medicineDao;
import com.ujiuye.entity.Tb_Medicine;
import com.ujiuye.utils.PageTool;

public class medicineService {
	medicineDao md = new medicineDao();

	// 获取数据条数
	public int totalCount() {
		return md.totalCount();
	}

	// 分页查询
	public List<Tb_Medicine> getMedicineByCurrentPage(PageTool pt) {
		return md.getMedicineByCurrentPage(pt);
	}
	
	// 添加信息
	public boolean insertMedicine(Tb_Medicine medicine) {
		return md.insertMedicine(medicine) == 0 ? false : true;
	}
	
	// 根据id查询信息
	public Tb_Medicine getMedicineByMid(int mid) {
		return md.getMedicineByMid(mid);
	}
	
	// 更改信息
	public boolean updateMedicine(Tb_Medicine medicine) {
		return md.updateMedicine(medicine) == 0 ? false : true;
	}
	
	// 获取条件查询的数据条数
	public int totalCountBySth(String mname, int type) {
		return md.totalCountBySth(mname, type);
	}
	
	// 条件查询
	public List<Tb_Medicine> getMedicineBySth(String mname, int type, PageTool pt) {
		return md.getMedicineBySth(mname, type, pt);
	}
	
	//单条删除
	public boolean delMedicineByMid(int mid) {
		return md.delMedicineByMid(mid) == 0 ? false : true;
	}
	
	//批量删除
	public boolean delMedicineByMids(String mids){
		return md.delMedicineByMids(mids) == 0 ? false : true;
	}
}
