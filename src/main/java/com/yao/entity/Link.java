package com.yao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 友情链接实体
 * @author Administrator
 *
 */
@Entity
@Table(name="t_link")
public class Link {

	@Id
	@GeneratedValue
	private Integer id; // 编号
	
	@Column(length=500)
	private String name; // 名称
	
	@Column(length=500)
	private String url; // 地址
	
	private Integer sort; // 排列序号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
