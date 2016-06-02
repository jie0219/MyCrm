package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.SalesPlanService;
import com.atguigu.crm.utils.MyParseUtils;

@RequestMapping("/plan")
@Controller
public class SalesPlanHandler {

	@Autowired
	private SalesChanceService chanceService;

	@Autowired
	private SalesPlanService planService;
	
	
	@RequestMapping("/details/{id}")
	public String details(@PathVariable("id") Integer id,
					Map<String,Object> map){
		
		SalesChance chance = chanceService.getById(id);
		List<SalesPlan> list = planService.getPlanList(id);
		map.put("chance", chance);
		map.put("planList", list);
		return "plan/details";
	}
	
	
	@RequestMapping(value="/make/saveResult",method=RequestMethod.PUT)
	@ResponseBody
	public String doSaveResult(@RequestParam("id") int id,
								@RequestParam("result") String result){
		
		SalesPlan plan = new SalesPlan();
		plan.setId((long)id);
		plan.setResult(result);;
		planService.update(plan);
		return "1";
	}
	
	
	
	/**
	 * 转到执行计划页面
	 */
	@RequestMapping(value="/execute/{id}",method=RequestMethod.GET)
	public String toExecutePage(@PathVariable("id") Integer id,
						Map<String,Object> map){
		
		SalesChance chance = chanceService.getById(id);
		List<SalesPlan> list = planService.getPlanList(id);
		map.put("chance", chance);
		map.put("planList", list);
		return "plan/execute";
	}
	
	
	/**
	 * Ajax 操作完成更新和删除
	 * @param id
	 * @param todo
	 * @return
	 */
	@RequestMapping(value="/make/ajax",method=RequestMethod.PUT)
	@ResponseBody
	public String ajax_save(@RequestParam("id") Integer id,
						@RequestParam("todo" )String todo){
		System.out.println(id+"==="+todo);
		planService.savePlan(id,todo);
		return "1";
	}
	
	@RequestMapping(value="/make/ajax",method=RequestMethod.DELETE)
	@ResponseBody
	public String ajax_del(@RequestParam("id") Integer id){
		planService.deletePlan(id);
		return "1";
	}
	
	
	
	
	/**
	 * 显示和保存计划
	 * @param map
	 * @param plan
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/make/{id}")
	public String saveOrShowPlan(Map<String,Object> map,SalesPlan plan
							,@PathVariable("id")Integer id){
		
		if (plan.getDate() != null && plan.getTodo() != null) {
			planService.savePlan(plan);
		}
		
		List<SalesPlan> list = planService.getPlanList(id);
		SalesChance salesChance = chanceService.getById(id);
		
		map.put("chance", salesChance);
		map.put("planList", list);
		return "plan/makePlan";
	}
	
	
	
	/**
	 * 到客户开发计划页面
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/list2")
	public String toPlanList(@RequestParam(value = "pageNo", required = false) String pageNoStr, HttpSession session, Map<String, Object> map, HttpServletRequest request) throws ParseException {
		// 1. 批量的获取请求参数.
		// 获取前段传来的查询参数
		System.out.println("----------------");
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
		User user = (User) session.getAttribute("user");
			int id = (int) (long) user.getId();
			mapperMap.put("id", id);
		Page<SalesChance> page = chanceService.getPlanPage2(pageNo, mapperMap);
		System.out.println(mapperMap);
		map.put("page", page);
		map.put("queryString", requestParamStr);
		return "plan/planList";
	}
}
