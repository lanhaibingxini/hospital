package com.ujiuye.entity;

import java.util.Date;

public class Tb_Doctor {
	private int did;
	private String dname;
	private String cardno;
	private String phone;
	private int sex;
	private Date birthday;
	private int age;
	private String email;
	private int department;
	private int education;
	private String remark;
	public Tb_Doctor() {
		super();
	}
	public Tb_Doctor(int did, String dname, String cardno, String phone, int sex, Date birthday, int age, String email,
			int department, int education, String remark) {
		super();
		this.did = did;
		this.dname = dname;
		this.cardno = cardno;
		this.phone = phone;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.email = email;
		this.department = department;
		this.education = education;
		this.remark = remark;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
