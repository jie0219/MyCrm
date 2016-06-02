<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/commons/common.jsp" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#button1").click(function(){
			return false;
		});
	});

</script>
</head>
<body class="main">
  
  	<form:form id="user" action="${ctp}/user/create" method="post" modelAttribute="user">
  			<c:if test="${user.id>0 }">
  			<input type="hidden" name="_method" value="PUT"/>
  			<input type="hidden" name="id" value="${user.id }"/>
  			</c:if>
	  		<span class="page_title">用户管理　&gt;　新建用户</span>
		
		<div class="button_bar">
			<button class="common_button" id="button1" onclick="javascript:history.go(-1);">返回</button>
			<button class="common_button" onclick="document.forms[0].submit()">保存</button>
		</div>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title">用户名</th>
				<td class="input_content">
					<form:input path="name"/>
					<div id='divCheck'></div>
				</td>
				
				<th class="input_title">密码</th>
				<td class="input_content">
					<form:password path="password" />
				</td>
			</tr>
			<tr>
				<th class="input_title">角色</th>
				<td class="input_content">
				<form:select path="role.id" items="${roleList }" itemLabel="name" itemValue="id"></form:select>
				
				</td>
				<th class="input_title">状态</th>
				<td class="input_content">
				<c:if test="${user.enabled =='1'}">
					<span><input id="enabled1" name="enabled" type="radio" value="0" /><label for="enabled1">无效</label></span>
					<span><input id="enabled2" name="enabled" type="radio" value="1" checked="checked" /><label for="enabled2">有效</label></span>
				</c:if>
				<c:if test="${user.enabled =='0'}">
					<span><input id="enabled1" name="enabled" type="radio" value="0" checked="checked" /><label for="enabled1">无效</label></span>
					<span><input id="enabled2" name="enabled" type="radio" value="1"/><label for="enabled2">有效</label></span>
				</c:if>
				<c:if test="${user.enabled ==null}">
					<span><input id="enabled1" name="enabled" type="radio" value="0" /><label for="enabled1">无效</label></span>
					<span><input id="enabled2" name="enabled" type="radio" value="1"/><label for="enabled2">有效</label></span>
				</c:if>
				
				</td>
			</tr>
		</table>
	</form:form>
	
  </body>
</html>