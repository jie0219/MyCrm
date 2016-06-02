<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户开发计划</title>
</head>

<body class="main">
	<form action="${ctp}/plan/chance/list2" method="post">
		<div class="page_title">
			客户开发计划
		</div>
		<div class="button_bar">
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
		<br />
		
			<c:if test="${empty page.content }">
			没有数据
		</c:if>
		
		<c:if test="${not empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_number">${item.id }</td>
						<td class="list_data_text">${item.custName }</td>
						<td class="list_data_text">${item.title }</td>
						<td class="list_data_text">${item.contact }</td>
						<td class="list_data_text">${item.contactTel }</td>
						<td class="list_data_text">
							<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/>
						</td>
							 <c:if test="${item.status=='2'}">
								<td class="list_data_text">
									 开发中
								 </td>
							 </c:if>
							 <c:if test="${item.status=='3'}">
									<td class="list_data_text" style="color:red" > 开发成功</td>
							 </c:if>
							 <c:if test="${item.status =='4'}">
									<td class="list_data_text" style="color:red"> 开发失败</td>
							 </c:if>
						
					 <c:if test="${item.status =='2'}">
						<td class="list_data_op">
								<img
									onclick="window.location.href='${ctp}/plan/make/${item.id}'"
									title="制定计划" src="${ctp}/static/images/bt_plan.gif" class="op_button" />
								<img
									onclick="window.location.href='${ctp}/plan/execute/${item.id} '"
									title="执行计划" src="${ctp}/static/images/bt_feedback.gif" class="op_button" />
								<img 
									onclick="window.location.href='${ctp}/chance/finish/${item.id}'"
									title="开发成功" src="${ctp}/static/images/bt_yes.gif" class="op_button" />
						</td>
					</c:if>
					
					<c:if test="${item.status== '3'|| item.status=='4'}">
						<td><a href="${ctp}/plan/details/${item.id} ">查看操作</a></td>
					</c:if>
					
					
					
					</tr>
				</c:forEach>
				</table>
			</c:if>
				<atguigu:page page="${page }" queryString="${queryString }" toUrl="plan/chance"/>
			
		</form>
</body>
</html>