package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerContributionService;
import com.atguigu.crm.utils.MyParseUtils;

@Controller
@RequestMapping("/contribution")
public class ContributionHandler {

	
	@Autowired
	private CustomerContributionService contributionService;
	
	@RequestMapping("/list2")
	public String list(HttpServletRequest request, 
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) throws ParseException{
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
		
		Page<Customer> page = contributionService.getPage(pageNo, mapperMap);
		map.put("page", page);
		map.put("queryString", requestParamStr);
		
		return  "contribution/list";
	}
}
