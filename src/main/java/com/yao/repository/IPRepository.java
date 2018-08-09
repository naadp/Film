package com.yao.repository;

import com.yao.entity.Film;
import com.yao.entity.IP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 电影Repository接口
 * @author Administrator
 *
 */
public interface IPRepository extends JpaRepository<IP, Integer>,JpaSpecificationExecutor<Film>{
	
}
