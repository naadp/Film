package com.yao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yao.entity.WebSite;

/**
 * 收录电影网址Repository接口
 * @author Administrator
 *
 */
public interface WebSiteRepository extends JpaRepository<WebSite, Integer>,JpaSpecificationExecutor<WebSite>{

}
