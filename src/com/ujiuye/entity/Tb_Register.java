package com.ujiuye.entity;

import java.util.Date;

public class Tb_Register {
	private int rid;
	private String rname;
	private String idCard;
	private String siNumber;
	private double registerMoney;
	private String phone;
	private int isPay;
	private int sex;
	private int age;
	private int consultation;
	private int did;
	private String remark;
	private Date rtime;
	private int statu;
	private Tb_Doctor doctor;

	public Tb_Register() {
		super();
	}

	public Tb_Register(int rid, String rname, String idCard, String siNumber, double registerMoney, String phone,
			int isPay, int sex, int age, int consultation, int did, String remark, Date rtime, int statu) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.idCard = idCard;
		this.siNumber = siNumber;
		this.registerMoney = registerMoney;
		this.phone = phone;
		this.isPay = isPay;
		this.sex = sex;
		this.age = age;
		this.consultation = consultation;
		this.did = did;
		this.remark = remark;
		this.rtime = rtime;
		this.statu = statu;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getSiNumber() {
		return siNumber;
	}

	public void setSiNumber(String siNumber) {
		this.siNumber = siNumber;
	}

	public double getRegisterMoney() {
		return registerMoney;
	}

	public void setRegisterMoney(double registerMoney) {
		this.registerMoney = registerMoney;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getConsultation() {
		return consultation;
	}

	public void setConsultation(int consultation) {
		this.consultation = consultation;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public Tb_Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Tb_Doctor doctor) {
		this.doctor = doctor;
	}

}
