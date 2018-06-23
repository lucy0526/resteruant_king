<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="app/detail/style/css/index.css" />
	<script type="text/javascript"><!--
		/** // 删除菜品项
		function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=removeSorder&gid="+gid;
		}
		
		// 修改菜品项数量
		function alterSorder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}
		*/
		// 下单
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
			<form action="${pageContext.request.contextPath }/indexServlet?method=clientOrderList" method="post">
				<table align="center" width="100%">
				
				
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<!--<td align="center" width="20%">小计</td>
				 		<td align="center" width="20%">操作</td>-->
				 	</tr>
				 	
				 	
					<tr height="60">
					 		<td align="center" width="20%">${requestScope.food.foodName }</td>
					 		<td align="center" width="20%">${requestScope.food.price }</td>
					 		
					 		<td align="center" width="20%">
					 			<input type="text" value="1" name="num" size="3" lang="3" onblur="alterSorder(this)"/>
					 		</td>
					 		
					 		<!--<td align="center" width="20%">68.0</td>
					 		<td align="center" width="20%">
					 			<input type="button" value="删除" class="btn_next" lang="3" onclick="removeSorder(this)" />
					 		</td>-->
				 	</tr>


					<tr>
						<td colspan="6" align="right"><!--总计:
							<span style="font-size:36px;">&yen;&nbsp;68.0</span>
							-->
							<label id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="id" value="${requestScope.food.id }">
							<input type="submit" value="下单" class="btn_next" />
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/indexServlet?method=orderList">
							<img src="app/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					<c:forEach var="foodType" items="${requestScope.listFoodType}">
						<li>
							<a href="${pageContext.request.contextPath }/indexServlet?method=indexDetail&foodType_id=${foodType.id }">${foodType.typeName }</a>
							<input type="hidden" name="foodType_id" value="${foodType.id }"/>
						</li>
					
					</c:forEach>
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath }/indexServlet?method=indexDetail" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" />
								&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input type="submit" id="sub" value="" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
