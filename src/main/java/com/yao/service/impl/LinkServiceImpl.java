package com.yao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yao.entity.Link;
import com.yao.repository.LinkRepository;
import com.yao.service.LinkService;

/**
 * 友情链接Service实现类
 * 
 * @author Administrator
 * 
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

	@Resource
	private LinkRepository linkRepository;

	@Override
	public List<Link> list(Integer page, Integer pageSize) {
		return linkRepository.findAll(new PageRequest(page, pageSize))
				.getContent();
	}

	@Override
	public Long getCount() {
		return linkRepository.count();
	}

	@Override
	public void save(Link link) {
		linkRepository.save(link);
	}

	@Override
	public void delete(Integer id) {
		linkRepository.delete(id);
	}
	
	@Override
	public List<Link> listAll() {
		return linkRepository.findAll();
	}
	
}
