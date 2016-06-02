package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.AuthorityService;
import com.atguigu.crm.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleHandler {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorityService authorityService;
	@RequestMapping("/list2")
	public String toList(@RequestParam(value="pageNo",required=false)String pageNoStr,Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		
		}
		Page<Role> page = roleService.getPage(pageNo);
		map.put("page", page);
		return "role/list";
	}
	
	
	/*	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("authorities2");
	}*/
	@RequestMapping("/assign/{id}")
	public String assign(@PathVariable("id") Integer id ,Map<String, Object> map){
		Role role = roleService.getById(id);
		List<Authority> parentNameList = authorityService.getParentAuth();
		map.put("role", role);
		map.put("parentAuthorities", parentNameList);
		return "role/auth";
	}
	
	@RequestMapping(value="/assign",method=RequestMethod.POST)
	public String save(@RequestParam("id") List<Integer> authIdList,
					@RequestParam("role_id") Integer id,
					RedirectAttributes ra){
		authorityService.delete(id);
		for (Integer authId : authIdList) {
			authorityService.insert(authId,id);
		}
		ra.addFlashAttribute("message", "保存成功");
		return "role/list";
	}
}
