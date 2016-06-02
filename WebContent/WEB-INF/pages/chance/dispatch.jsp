<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include  file="/commons/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>指派销售机会</title>
    <script type="text/javascript">
	    $(function(){
	    	var val = $("#designeeDate").val();

	    	if(val == null || val == ""){
	    		$("#designeeDate").val(new Date().format("yyyy-MM-dd"));
	    	}
	    })
    </script>
  </head>

  <body class="main">
  <span class="page_title">指派销售机会</span>

  <div class="button_bar">
	<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
	<button class="common_button" onclick="document.forms[0].submit();">保存</button>
  </div>
  <form:form id="chance" action="${ctp}/chance/dispatch/${chance.id}" method="post" modelAttribute="chance" >
		<input name="_method" value="put" type="hidden">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${chance.id }</td>
				<th>机会来源</th>
				<td>${chance.source }</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName }</td>
				<th>成功机率%</th>
				<td>${chance.rate }</td>
			</tr>
			<tr>
				<th>概要</th>
				<td colspan="2">${chance.title }</td>
			</tr>
			<tr>
				<th>联系人</th>
				<td>${chance.contact }</td>
				<th>联系人电话</th>
				<td>${chance.contactTel }</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description }</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name }</td>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.createDate }"/>
				</td>
			</tr>
		</table>
		<br />
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">				
			<tr>					
				<th>指派给</th>
				<td>
					<form:select path="designee.id" itemLabel="name" itemValue="id" items="${list }">
					</form:select>
					<span class="red_star">*</span>
				</td>
				<th>指派时间</th>
				<td>
					<input id="designeeDate" name="designeeDate" readonly="readonly" type="text" value=""/><span class="red_star">*</span>
				</td>
			</tr>
		</table>
	 </form:form>
  </body>
</html>