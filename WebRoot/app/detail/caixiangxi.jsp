<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="app/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="app/detail/style/js/page_common.js"></script>
<link href="app/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="app/detail/style/css/index_1.css" />
	<link href="app/detail/style/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="app/detail/style/css/dis_message.css" />
</head>
<body style="text-align: center">
	<div id="all">
		<!--左边菜品详细信息 -->
		<div id="menu1">
			<div class="menu2" style="text-align:center;">
				<img src="app/detail/style/images/order_detials_bg.png" />
			</div>
			<div class="menu3">
				<div class="menu3_left">
					<img src="sys/detail/style/images/food/${requestScope.food.img }"
						style="width:270px; height:290px;" />
				</div>
				<div class="menu3_right">
					<p>${requestScope.food.foodName }</p>
					<p>价格:&nbsp;&nbsp;&yen;&nbsp;${requestScope.food.price }</p>
					<p>简介:${requestScope.food.introduce }</p>
				</div>
			</div>
			<div class="menu4">
			
			
				<a class='sp' href="${pageContext.request.contextPath }/indexServlet?method=clientCart&id=${requestScope.food.id }" style="background:url(app/detail/style/images/img/order_left_corner_bg.png);">放入餐车</a>
				<a class="sp" href="javascript:addNum()">-</a>
				<input class="sp"  id="foodNum" type="text" value="1" name="num" size="3" lang="3" onblur="alterSorder(this)"/>
				<a class="sp"  href="javascript:deNum()">+</a>
				
				<script type="text/javascript">
					var foodNum = ${requestScope.foodNum};
					if(foodNum.IsNaN())
						foodNum = 1;
					var numNode = document.getElementById("foodNum");
						numNode.Value = foodNum;
						
					function addNum(){
						var numNode = document.getElementById("foodNum");
						var num = numNode.Value+1;
						var frm = document.forms[0];
						frm.action="${pageContext.request.contextPath}/indexServlet?method=clientOrderList&tag=y&id=${requestScope.curFoodType_id}&num="+num;
						frm.submit();
					}
					function deNum(){
						var numNode = document.getElementById("foodNum");
						var num = numNode.Value-1;
						var frm = document.forms[0];
						frm.action="${pageContext.request.contextPath}/indexServlet?method=clientOrderList&tag=y&id=${requestScope.curFoodType_id}&num="+num;
						frm.submit();
					}
				</script>
				
				
				
				<a class="spe" href="#" onclick="javascript:history.go(-1);" style="background:url(app/detail/style/images/img/order_right_corner_bg.png);">返回</a>
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
