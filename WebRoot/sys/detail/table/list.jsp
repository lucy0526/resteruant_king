<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/detail/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/detail/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="${pageContext.request.contextPath }/tableServlet" method="post">
		<input type="hidden" name="method" value="getTables">
		<input type="text" name="keyword" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		
			
			
			
			
				<c:choose>
					<c:when test="${!empty requestScope.list}">
						<c:forEach var="table" items="${requestScope.list}">
						
							<tr class="TableDetail1">
								<td align="center">${table.id }&nbsp;</td>
								<td align="center">${table.tableName }&nbsp;</td>
								
								<c:if test="${table.tableStatus==0}">
									<td align="center">空闲</td>
									<td align="center">&nbsp; </td>
								</c:if>
								<c:if test="${table.tableStatus==1}">
									<td align="center">已预定</td>
									<td align="center">${table.orderDate }</td>
								</c:if>
								
								<td>
									<c:if test="${table.tableStatus==1}">
										<a href="${pageContext.request.contextPath }/tableServlet?method=update&tableStatus=${table.tableStatus}&id=${table.id }" class="FunctionButton">退桌</a>
									</c:if>
									<c:if test="${table.tableStatus==0}">
										<a href="${pageContext.request.contextPath }/tableServlet?method=update&tableStatus=${table.tableStatus}&id=${table.id }" class="FunctionButton">预定</a>
									</c:if>			
									
									<a href="${pageContext.request.contextPath }/tableServlet?method=delete&id=${table.id }" class="FunctionButton">删除</a>				
								</td>
							</tr>
							
							
							
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td clospan="5">没有你要找的数据，请先保存记录再查看！</td>
						</tr>
					</c:otherwise>
				</c:choose>
				
				
				
				
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/sys/detail/table/add.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
