package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.RoleService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.MyParseUtils;

@RequestMapping("/user")
@Controller
public class UserHandler {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password,
			HttpSession session,
			RedirectAttributes attributes, 
			Locale locale){
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				attributes.addFlashAttribute("message", "密码或用户名不正确");
				attributes.addFlashAttribute("username", username);
				return "redirect:/index";
			}
		}
		User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			session.setAttribute("user",user);
			for (Authority a : user.getRole().getAuthorities()) {
				System.out.println(a.getName());
			}
			System.out.println(user.getRole().getAuthorities().size());
			return "redirect:/success";
	}
	
	@RequestMapping("/list2")
	public String list(@RequestParam(value="pageNo",required=false)String pageNoStr,
									Map<String, Object> map,HttpServletRequest request) throws ParseException{
		//请求参数 LIKE_name EQ_status
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//解析成字符串
		String queryString = MyParseUtils.parseRequestParams2QueryString(params);
		
		int pageNo = 1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
			
		} catch (Exception e) {
		
		}
		Page<User> page = userService.getPage(pageNo,params);
		map.put("page", page);
		map.put("queryString", queryString);
		
		return "user/list";
	}
	
	@RequestMapping("create")
	public String toCreatePage(Map<String, Object> map){
		map.put("user", new User());
		List<Role> list = roleService.getAllRole();
		map.put("roleList", list);
		return "user/create";
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String saveUser(Map<String, Object> map,User user){
		userService.saveUser(user);
		
		return "redirect:/user/list2";
	}
	
	@RequestMapping(value="create",method=RequestMethod.PUT)
	public String update(Map<String, Object> map,User user,RedirectAttributes rs){
		userService.update(user);
		rs.addFlashAttribute("message", "修改成功");
		return "redirect:/user/list2";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id,
						RedirectAttributes rs){
		userService.updateEnabled(id);
		rs.addFlashAttribute("message", "删除成功");
		return "redirect:/user/list2";
	}
	
	@RequestMapping("create/{id}")
	public String edit(@PathVariable("id") Integer id,Map<String, Object> map){
		User user = userService.getById(id);
		map.put("user", user);
		List<Role> list = roleService.getAllRole();
		map.put("roleList", list);
		return "user/create";
	}
	
}
