package com.ujiuye.utils;

public class PageTool {
	private int pageSize;//ÿһҳ��ʾ������
	private int totalCount;//������
	private int totalPages;//��ҳ��
	private int currentPage;//��ǰҳ��
	private int prePage;//��һҳ
	private int nextPage;//��һҳ
	private int startIndex;//ÿһҳ��һ�����ݵ�����
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
	
	//����ǰҳ���ʼ��
	private void initCurrentPage(String currentPage) {
		if(currentPage == null || "".equals(currentPage)) {
			this.currentPage = 1;
		}else {
			//��String����ת����integer����
			this.currentPage = Integer.parseInt(currentPage);
		}
	}
	
	//������ҳ��
	private void initTotalPages() {
		this.totalPages = (this.totalCount % this.pageSize == 0) ? (this.totalCount/this.pageSize) : (this.totalCount/this.pageSize + 1);
	}
	
	//��һҳ
	private void initPrePage() {
		if(currentPage == 1) {
			this.prePage = 1;
		}else {
			this.prePage = this.currentPage - 1;
		}
	}
	
	//��һҳ
	private void initNextPage() {
		if(this.currentPage == this.totalPages) {
			this.nextPage = this.totalPages;
		}else {
			this.nextPage = this.currentPage + 1;
		}
	}
	
	//ÿһҳ��һ�����ݵ�����
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
