package com.ujiuye.service;

import java.util.List;

import com.ujiuye.dao.RegisterDao;
import com.ujiuye.entity.Tb_Register;
import com.ujiuye.utils.PageTool;

public class RegisterService {
	RegisterDao rd = new RegisterDao();

	// ��ȡ��������
	public int totalCount() {
		return rd.totalCount();
	}

	// ��ҳ��ѯ
	public List<Tb_Register> getRegisterByCurrentPage(PageTool pt) {
		return rd.getRegisterByCurrentPage(pt);
	}
	
	// �����Ϣ
	public boolean insertRegister(Tb_Register register) {
		return rd.insertRegister(register) == 0 ? false : true;
	}
	
	//����ridչʾ�Һ���Ϣ
	public Tb_Register getRegisterByRid(int rid) {
		return rd.getRegisterByRid(rid);
	}
	
	//�༭����
	public boolean updateRegister(Tb_Register register) {
		return rd.updateRegister(register) == 0 ? false : true;
	}
}
