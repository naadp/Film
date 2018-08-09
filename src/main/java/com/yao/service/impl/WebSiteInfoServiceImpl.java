package com.yao.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yao.entity.WebSiteInfo;
import com.yao.repository.WebSiteInfoRepository;
import com.yao.service.WebSiteInfoService;
import com.yao.util.StringUtil;

/**
 * 电影动态信息Service实现类
 * @author Administrator
 *
 */
@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService{

	@Resource
	private WebSiteInfoRepository webSiteInfoRepository;
	
	@Override
	public List<WebSiteInfo> list(final WebSiteInfo webSiteInfo, Integer page, Integer pageSize) {
		Pageable pageable=new PageRequest(page, pageSize,Sort.Direction.DESC,"publishDate");
		Page<WebSiteInfo> pageWebSite=webSiteInfoRepository.findAll(new Specification<WebSiteInfo>() {
			
			@Override
			public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				if(webSiteInfo!=null){
					if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
						predicate.getExpressions().add(cb.like(root.<String>get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageWebSite.getContent();
	}

	@Override
	public Long getCount(final WebSiteInfo webSiteInfo) {
		Long count=webSiteInfoRepository.count(new Specification<WebSiteInfo>() {

			@Override
			public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				if(webSiteInfo!=null){
					if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
						predicate.getExpressions().add(cb.like(root.<String>get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));
						//predicate.getExpressions().add(cb.like(root.<String>get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));    //同上面
					}
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public List<WebSiteInfo> getByFilmId(Integer filmId) {
		return webSiteInfoRepository.getByFilmId(filmId);
	}

	@Override
	public List<WebSiteInfo> getByWebSiteId(Integer webSiteId) {
		return webSiteInfoRepository.getByWebSiteId(webSiteId);
	}

	@Override
	public void save(WebSiteInfo webSiteInfo) {
		webSiteInfoRepository.save(webSiteInfo);
	}

	@Override
	public void delete(Integer id) {
		webSiteInfoRepository.delete(id);
	}
	
}
