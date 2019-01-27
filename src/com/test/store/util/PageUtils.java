package com.test.store.util;

public class PageUtils {

	private int pageSize;// 每页显示多少行
	private int totalCount;// 一共多少行
	private int currentPage;// 当前页
	private int totalPage;// 总页数

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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
