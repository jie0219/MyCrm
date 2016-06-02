package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.utils.MyParseUtils;
@RequestMapping("/dict")
@Controller
public class DictHandler {
	@Autowired
	
	private DictService dictService;
	/*
	 * 友谊天长地久，哈哈哈
	 */
	@RequestMapping("/list2")
	public String list2(@RequestParam(value="pageNo",required=false) String pageNoStr 
			,HttpServletRequest request,Map<String,Object>map) throws ParseException{
		//批量获取请求参数，获取前端传来的请求参数,放入params中
		Map<String, Object>params=WebUtils.getParametersStartingWith(request, "search_");
		String requestParamStr = MyParseUtils.parseRequestParams2QueryString(params);
		// 解析成Mapper需要的map 类型参数
		// 把key 从"Like_XX" 改成"XX" 把value 转换为"%XX%"
		
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			
		}
		Page<Dict>page=dictService.getPage2(pageNo,params);
		System.out.println(page.toString());
		map.put("page", page);
		map.put("queryString", requestParamStr);
		return "dict/list";
	}
	
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.PUT)
	public String update(Dict dict,RedirectAttributes attributes){
		dictService.update(dict);
		
		attributes.addFlashAttribute("message", "修改成功");
		
		return "redirect:/dict/list";
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String inputUpdate(@RequestParam(value="id")String idStr,Map<String, Object>map){
		Integer id=Integer.parseInt(idStr);
		Dict dict=dictService.getById(id);
		map.put("dict", dict);
		
		return "dict/input";
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public String delete(@PathVariable(value="id") String idstr,RedirectAttributes attributes){
		Integer id=-1;
		 try {
			id=Integer.parseInt(idstr);
		} catch (NumberFormatException e) {
			
		}
		dictService.deleteById(id);
		attributes.addFlashAttribute("message","删除成功" );
		return "redirect:/dict/list";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(Dict dict,RedirectAttributes attributes){
		dictService.save(dict);
		attributes.addFlashAttribute("message", "添加成功");
		
		return "redirect:/dict/list";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String input(Map<String, Object>map){
		
		map.put("dict", new Dict());
		
		return "dict/input";
	}
	
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr, Map<String, Object>map){
		int pageNo=1;
		
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			
		}
		Page<Dict>page=dictService.getPage(pageNo);
		
		map.put("page", page);
		return "dict/list";
	}
}
