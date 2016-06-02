package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.StorageService;
import com.atguigu.crm.utils.MyParseUtils;

@Controller
@RequestMapping("/storage")
public class StorageHandler {
	@Autowired
	private StorageService storageService;
	@RequestMapping("/list2")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,HttpServletRequest request,Map<String,Object> map) throws Exception {
		int pageNo =1 ;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		Page<Storage> page =  storageService.list(pageNo,params);
		
		String queryString = MyParseUtils.parseRequestParams2QueryString(params);
		
		map.put("queryString", queryString);
		map.put("page", page);
		
		
		return "storage/list";
	}
	
	@RequestMapping("/create")
	public String create(Map<String,Object> map) {
		
		Storage storage =   new Storage();
		
		List<Product> products = storageService.getProductList();
		System.out.println("添加。。。。。");
		System.out.println(storage.getId());
		//storage.setProduct(product);
		map.put("storage", storage);
		map.put("products", products);
		return "storage/addOrEdit";
	}
	@RequestMapping("/create1")
	public String edit(@RequestParam("id")Integer id,Map<String,Object> map) {
		System.out.println("编辑。。。。。");
		Storage storage =  storageService.getStorage(id);
		System.out.println(storage.getId());
		map.put("storage", storage);
		
		return "storage/addOrEdit";
	}
	@RequestMapping("/add")
	public String add(Storage storage) {
		
	
		storageService.save(storage);
		
		return "redirect:/storage/list2";
	}
	@RequestMapping("/update")
	public String update(@RequestParam(value="addStockCount",required=false) String countStr ,Storage storage) {
		int count  = 0 ;
		try {
			count = Integer.parseInt(countStr);
		} catch (NumberFormatException e) {}
		
		storageService.update(storage,count);
		
		return "redirect:/storage/list2";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") Integer id ) {
		
		try {
			storageService.delete(id);
		} catch (Exception e) {
			return "excption/delete";
		}
		
		return "redirect:/storage/list2";
	}
}
