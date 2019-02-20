package com.yunmai.commons;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;


public interface BaseDao<T extends Serializable> {
	
	/**
	 * 增加数据
	 */
	Integer insert(Object eo);
	
	/**
	 * 删除结果
	 * @param eo
	 */
	void delete(Object eo);
	
	/**
	 * 更新结果表
	 * @param eo
	 */
	Integer update(Object eo);
	
	/**
	 * 分页统计表
	 * @param eo
	 */
	Integer count(Object eo);
	
	
	/**
	 * 查询
	 * 
	 * @param query : 交易表-QUERY
	 * @return List<T>
	 */
	@SuppressWarnings("hiding")
	<T extends Serializable> List<T> query(T query);
	
	/**
	 * 分页查询
	 * 
	 * @param query : 交易表-QUERY
	 * @param rowBounds
	 * @return List<T>
	 */
	@SuppressWarnings("hiding")
	<T extends Serializable> List<T> query(T query, RowBounds rowBounds);
	
}
