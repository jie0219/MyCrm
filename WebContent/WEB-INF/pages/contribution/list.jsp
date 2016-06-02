<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户贡献分析</title>
</head>
<body>

	<div class="page_title">
		客户贡献分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/contribution/list2">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						客户名称
					</th>
					<td>
						<input type="text" name="search_LIKE_custName" />
					</td>
					<th>
						日期
					</th>
					<td>
						<input type="text" name="search_minOrderDate" size="10" />
						-
						<input type="text" name="search_maxOrderDate" size="10" />
					</td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />
			
			<c:if test="${empty page.content }">暂时没有数据</c:if>
			<c:if test="${!empty page.content }">
				<table class="data_list_table" border="0" cellPadding="3"
					cellSpacing="0">
					<tr>
						
						<th>
							客户名称
						</th>
						<th>
							订单金额（元）
						</th>
					</tr>
						<c:forEach items="${page.content}" var="cus">
						<tr>
							<td align="center">
							${cus.name }
							</td>
							 <td align="center">
							 ${cus.money }
							 <%-- <c:if test="${cus.money==null }">0</c:if>
							 <c:if test="${cus.money!=null }">0</c:if> --%>
							</td> 
						</tr>
					</c:forEach>	
					</table>
				</c:if>
<atguigu:page page="${page}" toUrl="contribution" queryString="${queryString }"></atguigu:page>
			
			
		</div>
	</form>
</body>
</html>