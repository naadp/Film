package com.yao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yao.entity.WebSiteInfo;

/**
 * 电影动态信息Repository接口
 * @author Administrator
 *
 */
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Integer>,JpaSpecificationExecutor<WebSiteInfo>{

	/**
	 * 根据电影id查询动态信息
	 * @param filmId
	 * @return
	 */
	@Query(value="select * from t_info where film_id=?1",nativeQuery=true)
	public List<WebSiteInfo> getByFilmId(Integer filmId);
	
	/**
	 * 根据电影网址id查询电影动态信息
	 * @param webSiteId
	 * @return
	 */
	@Query(value="select * from t_info where web_site_id=?1",nativeQuery=true)
	public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
}
