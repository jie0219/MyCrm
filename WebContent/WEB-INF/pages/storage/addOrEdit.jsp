<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>编辑库存</title>
</head>

<body class="main">

	<span class="page_title">编辑库存</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	<c:if test="${empty storage.id  }">
	<form:form action="${ctp}/storage/add" method="POST" modelAttribute="storage">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			
			<tr>
				<th>
					产品
				</th>
				<td>
						<form:select path="product.id" items="${ products}" itemValue="id" itemLabel="name"></form:select>
				</td>
				<th>仓库</th>
				<td>
						<form:input path="wareHouse"/>
				</td>
			</tr>
			<tr>
				<th>货位</th>
				<td>
						<form:input path="stockWare"/>
				</td>
				<th>数量</th>
				<td>	
						<form:input path="stockCount"/>
				</td>
			</tr>
			<tr>
				<th>备注</th>
				<td>
						<form:input path="memo"/>
				</td>
			</tr>
			</table>
			</form:form>
			</c:if>
			<c:if test="${ not empty storage.id  }">
			
			<form:form action="${ctp}/storage/update" method="POST" modelAttribute="storage">
			<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					产品
				</th>
				<td>
					<form:input path="product.name" readonly="true" />
					<form:hidden path="id"/>
				</td>
				<th>仓库</th>
				<td>
					<form:input path="wareHouse" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th>货位</th>
				<td>	
						<form:input path="stockWare" readonly="true"/>
				</td>
				<th>添加数量</th>
				<td>	
						<form:hidden path="stockCount"/>
						<input type="text" name="addStockCount">
				</td>
			</tr>
			<tr>
				<th>备注</th>
				<td>
					<form:input path="memo" readonly="true"/>
				</td>
			</tr>
			</table>
			</form:form>
			</c:if>
		
	
</body>
</html>
