package com.ujiuye.entity;

import java.util.Date;

public class Tb_User {
	private int uid;
	private String uname;
	private String pwd;
	private String username;
	private String uemail;
	private Date createdate;
	public Tb_User() {
		super();
	}
	public Tb_User(int uid, String uname, String pwd, String username, String uemail, Date createdate) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.pwd = pwd;
		this.username = username;
		this.uemail = uemail;
		this.createdate = createdate;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
}
