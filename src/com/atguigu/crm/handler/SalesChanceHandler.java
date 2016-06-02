package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
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

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.MyParseUtils;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;

	@Autowired
	private UserService userService;

	/**
	 * 开发成功
	 * @param id
	 * @return
	 */
	@RequestMapping("/finish/{id}")
	public String finish(@PathVariable("id") Integer id){
		SalesChance chance = salesChanceService.getById(id);
		salesChanceService.finishChance(chance);
		return "redirect:/plan/chance/list2";
	}
	
	/**
	 * 开发失败
	 **/
	@RequestMapping("/stop/{id}")
	public String stop(@PathVariable("id") Integer id){
		salesChanceService.stopChance(id);
		return "redirect:/plan/chance/list2";
	}
	/**
	 * 保存指派的信息
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/dispatch/{id}", method = RequestMethod.PUT)
	public String saveDesignee(@PathVariable("id") Integer id, Map<String, Object> map, SalesChance chance) {
		System.out.println(chance.getDesigneeDate());
		salesChanceService.saveDesignee(id, chance);
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/dispatch/{id}", method = RequestMethod.GET)
	public String toDispatchPage(@PathVariable("id") Integer id, Map<String, Object> map) {
		// 获取当前指派的销售机会对象 为了在指派页面回显数据
		SalesChance chance = salesChanceService.getById(id);
		// 获得所有用户
		List<User> list = userService.getAllUser();
		// 放入域对象中
		map.put("chance", chance);
		map.put("list", list);
		return "chance/dispatch";
	}

	@RequestMapping(value = "/list2")
	public String list2(HttpServletRequest request, @RequestParam(value = "pageNo", required = false) String pageNoStr, Map<String, Object> map) throws ParseException {
		// 1. 批量的获取请求参数.
		// 获取前段传来的查询参数
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String requestParamStr = MyParseUtils.parseRequestParams2QueryString(params);
		// 解析成Mapper需要的map 类型参数
		// 把key 从"Like_XX" 改成"XX" 把value 转换为"%XX%"
		Map<String, Object> mapperMap = MyParseUtils.parseRequestParams2MyBatisParams(params);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		
		Page<SalesChance> page = salesChanceService.getPage2(pageNo, mapperMap);
		map.put("page", page);
		map.put("queryString", requestParamStr);
		return "chance/list";
	}

	

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(SalesChance chance, RedirectAttributes attributes) {
		salesChanceService.update(chance);
		attributes.addFlashAttribute("message", "修改成功!");
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("chance", salesChanceService.getById(id));
		return "chance/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(SalesChance chance, RedirectAttributes attributes) {
		salesChanceService.save(chance);
		attributes.addFlashAttribute("message", "添加成功!");
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("chance", new SalesChance());
		return "chance/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") String idStr) {
		Integer id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}

		salesChanceService.delete(id);
		return "redirect:/chance/list";
	}

	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", required = false) String pageNoStr, Map<String, Object> map) {
		int pageNo = 1;

		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<SalesChance> page = salesChanceService.getPage(pageNo);
		map.put("page", page);

		return "chance/list";
	}

}
