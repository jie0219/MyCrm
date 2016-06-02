package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;

@RequestMapping("/contact")
@Controller
public class ContactHandler {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/list/{cust.id}")
	public String list(@PathVariable("cust.id") Integer id,@RequestParam(value="pageNo",required=false) String pageNoStr,Map<String, Object> map){
		int pageNo=1;
		try {
			Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Page<Contact> page = contactService.getPage(pageNo, id);
		map.put("page", page);
		return "contact/list";
	}
}
