package com.atguigu.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.utils.MyParseUtils;

@Controller
@RequestMapping("/product")
public class ProductHandler {
	
	@Autowired
	private ProductService productService;
	//查出列表
	@RequestMapping("/list2")
	public String list(@RequestParam(value="pageNo",required=false)String pageNoStr,HttpServletRequest request,Map<String,Object> map) throws Exception{
		int pageNo = 1 ;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		Page<Product> page =  productService.getList(params,pageNo);
		
		String queryString = MyParseUtils.parseRequestParams2QueryString(params);
		
		map.put("page", page);
		
		map.put("queryString", queryString);
		
		return "product/list";
	}
	@RequestMapping("/update")
	public String toEditPage(@RequestParam("id") Integer id,Map<String,Object> map) {
		
		//System.out.println(id);
	
		Product product =  productService.getProduct(id);
		
		map.put("product", product);
		
		return "product/edit";
	}
	@RequestMapping("/create")
	public String create(Product product) {
	
		//System.out.println(product);
		productService.update(product);
		
		return "redirect:/product/list2";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")Integer id) {
		System.out.println(id);
		productService.delete(id);
		
		return "redirect:/product/list2";
	}
	@RequestMapping("/add")
	public String add(Map<String,Object> map) {
		
		Product pro = new Product();
		map.put("product", pro);
		
		return "product/add";
	}
	@RequestMapping("/add1")
	public String add1(Product product) {
		
		productService.save(product);
		
		return "redirect:/product/list2";
	}
	
}
