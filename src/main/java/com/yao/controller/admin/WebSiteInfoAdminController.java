package com.yao.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yao.entity.WebSiteInfo;
import com.yao.run.StartupRunner;
import com.yao.service.WebSiteInfoService;

/**
 * 电影动态信息管理Controller类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {
	
	@Resource
	private StartupRunner startupRunner;

	@Resource
	private WebSiteInfoService webSiteInfoService;
	
	/**
	 * 分页查询电影动态信息
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public Map<String,Object> list(WebSiteInfo webSiteInfo,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows)throws Exception{
		List<WebSiteInfo> webSiteInfoList=webSiteInfoService.list(webSiteInfo,page-1, rows);
		Long total=webSiteInfoService.getCount(webSiteInfo);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("rows", webSiteInfoList);
		resultMap.put("total", total);
		return resultMap;
	}
	
	/**
	 * 删除电影动态信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids")String ids, HttpServletRequest request)throws Exception{
		String []idsStr=ids.split(",");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		for(int i=0;i<idsStr.length;i++){
			webSiteInfoService.delete(Integer.parseInt(idsStr[i]));
		}
		startupRunner.loadData(request.getServletContext());
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 添加电影动态信息
	 * @param link
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public Map<String,Object> save(WebSiteInfo webSiteInfo, HttpServletRequest request)throws Exception{
		webSiteInfo.setPublishDate(new Date());
		Map<String,Object> resultMap=new HashMap<String,Object>();
		webSiteInfoService.save(webSiteInfo);
		startupRunner.loadData(request.getServletContext());
		resultMap.put("success", true);
		return resultMap;
	}
	
	
}
