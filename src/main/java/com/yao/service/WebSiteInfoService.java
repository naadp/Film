package com.yao.service;

import java.util.List;

import com.yao.entity.WebSiteInfo;

/**
 * 电影动态信息Service接口
 * @author Administrator
 *
 */
public interface WebSiteInfoService {

	/**
	 * 分页查询电影动态信息
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount(WebSiteInfo webSiteInfo);
	
	/**
	 * 根据电影id查询动态信息
	 * @param filmId
	 * @return
	 */
	public List<WebSiteInfo> getByFilmId(Integer filmId);
	
	/**
	 * 根据电影网址id查询电影动态信息
	 * @param webSiteId
	 * @return
	 */
	public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
	
	/**
	 * 添加或者修改电影动态信息
	 * @param link
	 */
	public void save(WebSiteInfo webSiteInfo);
	
	/**
	 * 根据id删除电影动态信息
	 * @param id
	 */
	public void delete(Integer id);
	
}
