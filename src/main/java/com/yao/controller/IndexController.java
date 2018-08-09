package com.yao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 根路径以及其他请求处理
 * @author Administrator
 *
 */
@Controller
public class IndexController {

	/**
	 * 网页根目录请求
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView root(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "首页");
		mav.addObject("mainPage", "film/indexFilm");
		mav.addObject("mainPageKey", "#f");
		mav.setViewName("index");
		return mav;
	}


	/**
	 * 登录请求
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("login");
		return mav;
		//return "/login";
	}
	
	/**
	 * 进入后台管理请求
	 * @return
	 */
	@RequestMapping("/admin")
	public String toAdmin(){
		return "/admin/main";
	}
	
	/**
	 * 关于本站
	 * @return
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "关于本站");
		mav.addObject("mainPage", "common/aboutMe");
		mav.addObject("mainPageKey", "#a");
		mav.setViewName("index");
		return mav;
	}
	
}
