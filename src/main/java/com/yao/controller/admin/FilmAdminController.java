package com.yao.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yao.entity.Film;
import com.yao.run.StartupRunner;
import com.yao.service.FilmService;
import com.yao.service.WebSiteInfoService;
import com.yao.util.DateUtil;
import com.yao.util.StringUtil;

@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {
	
	@Resource
	private StartupRunner startupRunner;

	private String imageFilePath;

	@Resource
	private WebSiteInfoService webSiteInfoService;
	
	@Resource
	private FilmService filmService;

	@RequestMapping("/save")
	public Map<String, Object> save(Film film, HttpServletRequest request,
			@RequestParam("imageFile") MultipartFile file) throws Exception {
		imageFilePath = request.getServletContext().getRealPath("/static/filmImage")+ File.separator;
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename(); // 获取文件名
			String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取文件的后缀
			String newFileName = DateUtil.getCurrentDateStr() + suffixName;
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					imageFilePath + newFileName));
			film.setImageName(newFileName);
		}
		film.setPublishDate(new Date());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		filmService.save(film);
		startupRunner.loadData(request.getServletContext());
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 分页查询电影信息
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public Map<String,Object> list(Film film,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows)throws Exception{
		List<Film> filmList=filmService.list(film,page-1, rows);
		Long total=filmService.getCount(film);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("rows", filmList);
		resultMap.put("total", total);
		return resultMap;
	}

	@RequestMapping("/ckeditorUpload")
	public String ckeditorUpload(@RequestParam("upload") MultipartFile file,HttpServletRequest request,
			String CKEditorFuncNum) throws Exception {

		imageFilePath = request.getServletContext().getRealPath("/static/filmImage")+ File.separator;

		String fileName = file.getOriginalFilename(); // 获取文件名
		String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取文件的后缀
		String newFileName = DateUtil.getCurrentDateStr() + suffixName;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
				imageFilePath + newFileName));

		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		// 这里用于回显数据(因为你图片传上去了,总得显示下吧),这里的路径写不对的话,图片的显示也会出错
		sb.append("window.parent.CKEDITOR.tools.callFunction("
				+ CKEditorFuncNum + ",'" + "/static/filmImage/" + newFileName
				+ "','')");
		sb.append("</script>");
		System.out.println("++++++" + CKEditorFuncNum);
		return sb.toString();

	}
	
	/**
	 * 删除电影信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids")String ids,HttpServletRequest req)throws Exception{
		String []idsStr=ids.split(",");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		boolean flag=true;
		for(int i=0;i<idsStr.length;i++){
			Integer filmId=Integer.parseInt(idsStr[i]);
			if(webSiteInfoService.getByFilmId(filmId).size()>0){
				flag=false;
			}else{
				filmService.delete(filmId);				
			}
		}
		if(flag){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
			resultMap.put("errorInfo", "电影动态信息中存在电影信息，不能删除！");
		}
		startupRunner.loadData(req.getServletContext());
		return resultMap;
	}
	
	/**
	 * 根据id查找实体
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public Film findById(Integer id)throws Exception{
		return filmService.findById(id);
	}
	
	/**
	 * 下拉框模糊查询用到
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/comboList")
	public List<Film> comboList(String q)throws Exception{
		if(StringUtil.isEmpty(q)){
			return null;
		}
		Film film=new Film();
		film.setName(q);
		return filmService.list(film, 0, 30); // 最多查询30条记录
	}
}
