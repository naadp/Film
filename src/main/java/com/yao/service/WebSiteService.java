package com.yao.service;

import java.util.List;

import com.yao.entity.WebSite;

/**
 * 收录电影网址Service接口
 * @author Administrator
 *
 */
public interface WebSiteService {

	/**
	 * 分页查询收录电影网址
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize);
	
	/**
	 * 获取最新收录网址
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<WebSite> newestList(Integer page, Integer pageSize);
	
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount(WebSite webSite);
	
	/**
	 * 添加或者修改收录电影网址
	 * @param link
	 */
	public void save(WebSite webSite);
	
	/**
	 * 根据id删除收录电影网址
	 * @param id
	 */
	public void delete(Integer id);
	
}
