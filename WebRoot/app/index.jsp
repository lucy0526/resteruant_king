 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="app/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="app/detail/style/js/page_common.js"></script>
<link href="app/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="app/detail/style/css/index_1.css" />
	<style type="text/css">
	* {
		margin: 0px;
		padding: 0px
	}
	#dish_2 a{
		text-decoration:none;
		font-size:36px;
		color:#000;
	}
	#dish_2 ul { 
		list-style:none;
	} 
	#dish_2 li{
		width:169px;
		height:47px;
		text-align:center;
		padding-top:5px;
	}
	font {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 20px;
	color:red; 
	line-height:24px
	} 
	</style>
</head>
<body style="text-align: center">
	<!--外部的大层-->
	<div class="index_all" style="text-align:center;">
		<div id="index_center">
			<!--中间层的空白层-->
			<div id="space">
			</div>
			<!--中间层的菜单层-->
			<div>
				<!--菜单层的中间-->
				<img
						src="app/detail/style/images/index_menu.jpg"
						border="0"/>
			</div>

			<!--放桌子的层-->
			<div id="center_bottom">
				<ul style=" display:inline-table">
					<c:choose>
						<c:when test="${not empty requestScope.listDinnerTable}">
							<c:forEach var="dt" items="${requestScope.listDinnerTable}"> 
								<li>
									<a href="${pageContext.request.contextPath }/indexServlet?method=indexDetail&table_id=${dt.id}">
										${dt.tableName }&nbsp;
									</a>
								</li>
								
							</c:forEach>
						</c:when>
					
					</c:choose>
				</ul>
			</div>
			
			
		</div>
	</div>
</body>
</html>