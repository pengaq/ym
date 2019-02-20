package com.yunmai.page;

import java.util.List;

public class PageModel<T> {
	private int totalCount; //实体总数
	private List<T> datas; //实体数据集合
	
	public PageModel() {}
	
	/**
	 * @param totalCount
	 * @param datas
	 */
	public PageModel(int totalCount, List<T> datas) {
		this.totalCount = totalCount;
		this.datas = datas;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
}
