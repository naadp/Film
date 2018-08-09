package com.yao.service;

import java.util.List;

import com.yao.entity.Link;
/**
 * 友情链接Service接口
 * @author Administrator
 *
 */
public interface LinkService {

	/**
	 * 分页查询友情链接
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Link> list(Integer page, Integer pageSize);
	
	/**
	 * 查询所有友情链接
	 * @return
	 */
	public List<Link> listAll();
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount();
	
	//添加或修改操作
	public void save(Link link);
	
	//删除操作
	public void delete(Integer id);
}
