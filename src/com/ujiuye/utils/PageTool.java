package com.ujiuye.utils;

public class PageTool {
	private int pageSize;//每一页显示的条数
	private int totalCount;//总条数
	private int totalPages;//总页数
	private int currentPage;//当前页码
	private int prePage;//上一页
	private int nextPage;//下一页
	private int startIndex;//每一页第一条数据的索引
	public PageTool() {
		
	}
	public PageTool(int pageSize, int totalCount, String currentPage) {
		
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		initCurrentPage(currentPage);
		initTotalPages();
		initPrePage();
		initNextPage();
		initStartIndex();
	}
	
	//给当前页面初始化
	private void initCurrentPage(String currentPage) {
		if(currentPage == null || "".equals(currentPage)) {
			this.currentPage = 1;
		}else {
			//将String类型转换成integer类型
			this.currentPage = Integer.parseInt(currentPage);
		}
	}
	
	//计算总页数
	private void initTotalPages() {
		this.totalPages = (this.totalCount % this.pageSize == 0) ? (this.totalCount/this.pageSize) : (this.totalCount/this.pageSize + 1);
	}
	
	//上一页
	private void initPrePage() {
		if(currentPage == 1) {
			this.prePage = 1;
		}else {
			this.prePage = this.currentPage - 1;
		}
	}
	
	//下一页
	private void initNextPage() {
		if(this.currentPage == this.totalPages) {
			this.nextPage = this.totalPages;
		}else {
			this.nextPage = this.currentPage + 1;
		}
	}
	
	//每一页第一条数据的索引
	private void initStartIndex() {
		this.startIndex = this.pageSize * (this.currentPage - 1);
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
}
