package com.ujiuye.entity;

public class Tb_Medicine {
	private int mid;
	private String picture;
	private double inPrice;
	private double salPrice;
	private String mname;
	private int type;
	private String descs;
	private int qualityDate;
	private String description;
	private String produceFirm;
	private String readme;
	private String remark;

	public Tb_Medicine() {
		super();
	}

	public Tb_Medicine(int mid, String picture, double inPrice, double salPrice, String mname, int type, String descs,
			int qualityDate, String description, String produceFirm, String readme, String remark) {
		super();
		this.mid = mid;
		this.picture = picture;
		this.inPrice = inPrice;
		this.salPrice = salPrice;
		this.mname = mname;
		this.type = type;
		this.descs = descs;
		this.qualityDate = qualityDate;
		this.description = description;
		this.produceFirm = produceFirm;
		this.readme = readme;
		this.remark = remark;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public double getInPrice() {
		return inPrice;
	}

	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}

	public double getSalPrice() {
		return salPrice;
	}

	public void setSalPrice(double salPrice) {
		this.salPrice = salPrice;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public int getQualityDate() {
		return qualityDate;
	}

	public void setQualityDate(int qualityDate) {
		this.qualityDate = qualityDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduceFirm() {
		return produceFirm;
	}

	public void setProduceFirm(String produceFirm) {
		this.produceFirm = produceFirm;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
