<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script type="text/javascript">

		
	$(function () {
		
		$("button[id^='save']").click(function(){
			
			var url = "${ctp}/plan/make/saveResult";
			
			var id =  $(this).attr("id");
			
			id = id.split("-")[1];
			var result = $("#result-"+id).val();
			var args = {"id":id, "result":result,"_method":"put"};
			 $.post(url,args,function(data){
				if (data=="1") {
					$("#result-"+id).attr("disabled","disabled");
					alert("保存成功");
				}
			});
			return false;
		});
		
	});
	


</script> 


</head>
<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/chance/stop/${chance.id}'">终止开发</button>
		<button class="common_button" onclick="window.location.href='${ctp}/plan/make/${chance.id}'">制定计划</button>
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>			
		<button class="common_button" onclick="window.location.href='${ctp}/chance/finish/${chance.id}'">开发成功</button>
	</div>
	
		<form:form action="${ctp}/plan/make/${chance.id}" method="post" modelAttribute="chance">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${chance.id }
				</td>
				<th>
					来源
				</th>

				<td>
					${chance.source }
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${chance.custName }
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${chance.rate }
				</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					${chance.title }
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${chance.contact }
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${chance.contactTel }
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${chance.description }
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${chance.createBy.name }
				</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }"/>
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${sessionScope.user.name }
				</td>

			</tr>
		</table>

		<br />
		<c:if test="${!empty planList }">
		
		
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th width="200px">
					日期
				</th>
				<th>
					计划
				</th>
				<th>
					执行效果
				</th>
			</tr>
			<c:forEach var="plan" items="${planList }">
					<tr id="plan-${plan.id }">
						<td class="list_data_text">
							<fmt:formatDate value="${plan.date }"/>
							&nbsp;
						</td>
						
						<td class="list_data_ltext">
								<input type="text" size="50"
									value="${plan.todo }" id="todo-${plan.id }"/>
								
								
						</td>
						
						<c:if test="${plan.result ==null }">
							<td class="list_data_ltext">
							<input type="text" size="50"
										name="result" id="result-${plan.id }"/>
								<button class="common_button" id="save-${plan.id}">
									保存
								</button>
							</td>
						</c:if>
						<c:if test="${plan.result !=null }">
						<td>
							<input type="text" size="50"
									value="${plan.result }" id="todo-${plan.id }" disabled="disabled"/>
								
						</td>
						</c:if>
					</tr>
			</c:forEach>
		</table>
			</c:if>
		<input type="hidden" name="chance.id" value="${chance.id }" />
	</form:form>
</body>
</html>