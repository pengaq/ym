package com.yunmai.page;


public class PageInfo {
	private int pageCurrent; //当前页码
	private int pageSize = 10; //页面大小
	//private T filters; //分页实体对象
	private String sortColumn;
	private String sortDirection = "asc";
	
	public PageInfo() {}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param filters
	 * @param sortColumn
	 * @param sortDirection
	 */
	public PageInfo(int pageCurrent, int pageSize, String sortColumn, String sortDirection) {
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.sortColumn = sortColumn;
		this.sortDirection = sortDirection;
	}
	
	public PageInfo(int pageCurrent, int pageSize) {
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
	}
	
	public int getOffset() {
        return (pageCurrent - 1) * pageSize;
    }

	
	
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	
	
}
