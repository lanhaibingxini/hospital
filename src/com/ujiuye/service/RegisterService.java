package com.ujiuye.service;

import java.util.List;

import com.ujiuye.dao.RegisterDao;
import com.ujiuye.entity.Tb_Register;
import com.ujiuye.utils.PageTool;

public class RegisterService {
	RegisterDao rd = new RegisterDao();

	// 获取数据条数
	public int totalCount() {
		return rd.totalCount();
	}

	// 分页查询
	public List<Tb_Register> getRegisterByCurrentPage(PageTool pt) {
		return rd.getRegisterByCurrentPage(pt);
	}
	
	// 添加信息
	public boolean insertRegister(Tb_Register register) {
		return rd.insertRegister(register) == 0 ? false : true;
	}
	
	//根据rid展示挂号信息
	public Tb_Register getRegisterByRid(int rid) {
		return rd.getRegisterByRid(rid);
	}
	
	//编辑功能
	public boolean updateRegister(Tb_Register register) {
		return rd.updateRegister(register) == 0 ? false : true;
	}
}
