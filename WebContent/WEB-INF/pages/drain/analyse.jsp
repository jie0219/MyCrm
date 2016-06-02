<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户流失分析</title>
</head>
<body>

	<div class="page_title">
		客户流失分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/drain/analyse/list2" method="post"> 
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户名称
				</th>
				<td>
					<input type="text" name="search_LIKE_customerName" />
				</td>
				<th>
					客户经理
				</th>
				<td>
					<input type="text" name="search_LIKE_customerManagerName" />
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
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
						客户经理
					</th>
					<th>
						确认流失时间
					</th>
					<th>
						流失原因
					</th>
					<th>
						状态
					</th>
				</tr>
				<c:forEach var="drain" items="${page.content }">
					<tr>
						<td class="list_data_number">
							${drain.id}
						</td>
						<td class="list_data_ltext">
							${drain.customer.name}
						</td>
						<td class="list_data_text">
							${drain.customer.manager.name}
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${drain.drainDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_ltext">
							${drain.reason}
						</td>
						<td class="list_data_text">
							${drain.status}
						</td>
					</tr>
				</c:forEach>
			</table>
			<atguigu:page page="${page }" toUrl="drain" queryString="${queryString }"></atguigu:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form>
</body>
</html>
