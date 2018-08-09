package com.yao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yao.entity.Link;


/**
 * 友情链接Repository接口
 * @author Administrator
 *
 */
public interface LinkRepository extends JpaRepository<Link, Integer>{

}
