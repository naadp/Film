package com.yao.service;

import java.util.List;

import com.yao.entity.Film;


/**
 * 电影Service接口
 * @author Administrator
 *
 */
public interface FilmService {
	
	/**
	 * 添加或者修改电影
	 * @param film
	 */
	public void save(Film film);
	
	/**
	 * 分页查询电影信息
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Film> list(Film film, Integer page, Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount(Film film);
	
	/**
	 * 根据id查找实体
	 * @param id
	 * @return
	 */
	public Film findById(Integer id);
	
	/**
	 * 根据id删除电影
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 获取上一个电影
	 * @param id
	 * @return
	 */
	public Film getLast(Integer id);
	
	/**
	 * 获取下一个电影
	 * @param id
	 * @return
	 */
	public Film getNext(Integer id);
	
	/**
	 * 随机获取n个电影
	 * @param n
	 * @return
	 */
	public List<Film> randomList(Integer n);
}

