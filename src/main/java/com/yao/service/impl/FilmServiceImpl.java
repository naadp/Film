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

import com.yao.entity.Film;
import com.yao.repository.FilmRepository;
import com.yao.service.FilmService;
import com.yao.util.StringUtil;

/**
 * 电影Service接口实现类
 * @author Administrator
 *
 */
@Service("filmService")
public class FilmServiceImpl implements FilmService{

	@Resource
	private FilmRepository filmRepository;
	
	@Override
	public void save(Film film) {
		filmRepository.save(film);
	}
	@Override
	public List<Film> list(final Film film, Integer page, Integer pageSize) {
		Pageable pageable=new PageRequest(page, pageSize,Sort.Direction.DESC,"publishDate");
		Page<Film> pageWebSite=filmRepository.findAll(new Specification<Film>() {
			
			@Override
			public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				if(film!=null){
					if(StringUtil.isNotEmpty(film.getName())){
						predicate.getExpressions().add(cb.like(root.<String>get("name"), "%"+film.getName().trim()+"%"));
					}
					if(film.getHot()!=null && film.getHot()==1){
						predicate.getExpressions().add(cb.equal(root.get("hot"), 1));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageWebSite.getContent();
	}

	@Override
	public Long getCount(final Film film) {
		Long count=filmRepository.count(new Specification<Film>() {

			@Override
			public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				if(film!=null){
					if(StringUtil.isNotEmpty(film.getName())){
						predicate.getExpressions().add(cb.like(root.<String>get("name"), "%"+film.getName().trim()+"%"));
					}
				}
				return predicate;
			}
		});
		return count;
	}
	
	@Override
	public Film findById(Integer id) {
		return filmRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		filmRepository.delete(id);
	}
	
	@Override
	public Film getLast(Integer id) {
		return filmRepository.getLast(id);
	}

	@Override
	public Film getNext(Integer id) {
		return filmRepository.getNext(id);
	}
	
	@Override
	public List<Film> randomList(Integer n) {
		return filmRepository.randomList(n);
	}
}
