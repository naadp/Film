package com.yao.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yao.entity.Link;
import com.yao.run.StartupRunner;
import com.yao.service.LinkService;

/**
 * 友情链接Controller类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/link/")
public class LinkAdminController {
	
	@Resource
	private StartupRunner startupRunner;

	@Resource
	private LinkService linkService;
	
	/**
	 * 分页查询友情链接
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public Map<String,Object> list(@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows)throws Exception{
		List<Link> linkList=linkService.list(page-1, rows);
		Long total=linkService.getCount();
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("rows", linkList);
		resultMap.put("total", total);
		return resultMap;
	}
	
	/**
	 * 添加或者修改友情链接
	 * @param link
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public Map<String,Object> save(Link link, HttpServletRequest request)throws Exception{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		linkService.save(link);
		startupRunner.loadData(request.getServletContext());
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 删除友情链接
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids")String ids, HttpServletRequest request)throws Exception{
		String []idsStr=ids.split(",");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		for(int i=0;i<idsStr.length;i++){
			linkService.delete(Integer.parseInt(idsStr[i]));
		}
		startupRunner.loadData(request.getServletContext());
		resultMap.put("success", true);
		return resultMap;
	}
}
