package com.ujiuye.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ujiuye.entity.Tb_Medicine;
import com.ujiuye.entity.Tb_Medicine;
import com.ujiuye.utils.PageTool;

public class medicineDao {
	ComboPooledDataSource ds = new ComboPooledDataSource();
	QueryRunner qr = new QueryRunner(ds);

	// ��ȡ��������
	public int totalCount() {
		int totalcount = 0;
		String sql = "select count(*) from tb_medicine";
		try {
			// ������ת����long���ͣ���ת����int����
			totalcount = (int) (long) qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalcount;
	}

	// ��ҳ��ѯ
	public List<Tb_Medicine> getMedicineByCurrentPage(PageTool pt) {
		List<Tb_Medicine> list = null;
		String sql = "select * from tb_medicine limit ?,?";
		try {
			list = qr.query(sql, new BeanListHandler<>(Tb_Medicine.class), pt.getStartIndex(), pt.getPageSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// �����Ϣ
	public int insertMedicine(Tb_Medicine medicine) {
		int rows = 0;
		String sql = "insert into tb_medicine(picture,inPrice,salPrice,mname,type,descs,qualityDate,description,"
				+ "produceFirm,readme,remark) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			rows = qr.update(sql, medicine.getPicture(), medicine.getInPrice(), medicine.getSalPrice(), 
					medicine.getMname(), medicine.getType(), medicine.getDescs(), medicine.getQualityDate(), 
					medicine.getDescription(), medicine.getProduceFirm(), medicine.getReadme(), medicine.getRemark());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	// ����id��ѯ��Ϣ
	public Tb_Medicine getMedicineByMid(int mid) {
		Tb_Medicine medicine = null;
		String sql = "select * from tb_medicine where mid=?";
		try {
			medicine = qr.query(sql, new BeanHandler<>(Tb_Medicine.class), mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicine;
	}

	// ������Ϣ
	public int updateMedicine(Tb_Medicine medicine) {
		int rows = 0;
		String sql = "update tb_medicine set picture=?,inPrice=?,salPrice=?,mname=?,type=?,descs=?,qualityDate=?,"
				+ "description=?,produceFirm=?,readme=?,remark=? where mid=?";
		try {
			rows = qr.update(sql, medicine.getPicture(), medicine.getInPrice(), medicine.getSalPrice(), 
					medicine.getMname(), medicine.getType(), medicine.getDescs(), medicine.getQualityDate(), 
					medicine.getDescription(), medicine.getProduceFirm(), medicine.getReadme(), medicine.getRemark(), medicine.getMid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	// ��ȡ������ѯ����������
	public int totalCountBySth(String mname, int type) {
		int rows = 0;
		String sql = "select count(*) from tb_medicine where 1=1";
		if(mname != null && !"".equals(mname)) {
			sql += " and mname like '%"+mname+"%'";
		}
		if(type != 0) {
			sql += " and type="+type;
		}
		try {
			rows = (int)(long)qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	// ������ѯ
	public List<Tb_Medicine> getMedicineBySth(String mname, int type, PageTool pt) {
		List<Tb_Medicine> list = null;
		String sql = "select * from tb_medicine where 1=1";
		if(mname != null && !"".equals(mname)) {
			sql += " and mname like '%"+mname+"%'";
		}
		if(type != 0) {
			sql += " and type="+type;
		}
		sql += " limit ?,?";
		try {
			list = qr.query(sql, new BeanListHandler<>(Tb_Medicine.class), pt.getStartIndex(), pt.getPageSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//����ɾ��
	public int delMedicineByMid(int mid) {
		int rows = 0;
		String sql = "delete from tb_medicine where mid=?";
		try {
			rows = qr.update(sql, mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	//����ɾ��
	public int delMedicineByMids(String mids){
		int rows = 0;
		String sql = "delete from tb_medicine where mid in ("+mids+")";
		try {
			rows = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
}
