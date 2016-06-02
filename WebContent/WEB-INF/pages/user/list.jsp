<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<head>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href="${ctp}" + "/user/create";
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form action="${ctp}/user/list2">
		<div class="page_title">
			用户管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">新建</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					用户名
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_name" />
				</td>
				<th class="input_title">
					状态
				</th>
				<td class="input_content">
					<select name="search_EQ_enabled">
						<option value="">
							全部
						</option>
						<option value="1">
							正常
						</option>
						<option value="0">
							已删除
						</option>
					</select>
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
					<th class="data_title" style="width: 40px;">
						编号
					</th>
					<th class="data_title" style="width: 50%;">
						用户名
					</th>
					<th class="data_title" style="width: 20%;">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
					<c:forEach items="${page.content }" var="item" >
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">
						${item.id }
						</td>
						<td class="data_cell" style="text-align: center;">
						${item.name }
						</td>
						
						<c:if test="${item.enabled =='1'}">
						<td class="data_cell">正常</td>
						</c:if>
						<c:if test="${item.enabled =='0'}">
						<td class="data_cell">已删除</td>
						</c:if>
					
						<td class="data_cell">
							<img onclick="window.location.href='delete/${item.id}'" 
								title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
							<img onclick="window.location.href='create/${item.id}'" 
								class="op_button" src="${ctp}/static/images/bt_edit.gif" title="编辑" />
						</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>


<atguigu:page page="${page }" toUrl="user" queryString="${queryString }"></atguigu:page>
	</form>
</body>
</html>

