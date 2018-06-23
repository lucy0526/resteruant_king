<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="app/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="app/detail/style/js/page_common.js"></script>
<link href="app/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="app/detail/style/css/index_1.css" />
<link rel="stylesheet" type="text/css" href="app/detail/style/css/dis_message.css" />
<link href="app/detail/style/css/index.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
		
function gotoPage(page){
	//获取当前表单对象
	var frm = document.forms[0];
	frm.action="${pageContext.request.contextPath}/indexServlet?method=indexDetail&foodType_id=${requestScope.curFoodType_id}&currentPage="+page;
	frm.submit();
}
function addNum(){
	var numNode = document.getElementById("foodNum");
	numNode.value++;
}
function deNum(){
	var numNode = document.getElementById("foodNum");
	if(numNode.value != 1)
		numNode.value--;
}
function addOrder(){
	var numNode = document.getElementById("foodNum");
	num = numNode.value;
	var frm = document.forms[0];
	frm.action="${pageContext.request.contextPath}/indexServlet?method=clientOrderList&id=${requestScope.food.id}&num="+num;
	frm.submit();
}
</script>	
<style type="text/css">

	#dish_2 *{margin:0; padding:0; font-size:18px}
	#dish_2 ul{list-style:none; width:120px}
	#dish_2 a{text-decoration:none; display:block; height:40px; line-height:30px; width:180px;
	 backgroud-color:#ccc; margin-bottom:1px; text-indent:10px}
	#dish_2 a:hover{background-color:#f60; color:red}
	
	#mask{
	background:#000;
	opacity:0.75;
	filter:alpha(opacity=75);
	height:100%;
	width:100%;
	position:absolute;
	left:0;
	top:0;
	z-index:1000;
	}
	#menu1{
	position:fixed;
	left:30%;
	top:30%;
	z-index:1001;
	}
	#close{
	width:30px;
	height:30px;
	background: url(app/detail/style/images/close.png) no-repeat;
	cursor:pointer;
	position:absolute;
	right:23px;
	top:20px;
	}
</style>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
<!-- 显示菜品 -->
			<div id="top">
				<ul>
					<!-- 循环列出菜品 -->
					<c:forEach var="food" items="${sessionScope.pb.pageData}">
							<li>
								<dl>
									<dt>
										<a href="${pageContext.request.contextPath }/indexServlet?method=foodDetail&id=${food.id}">
											<img width="214px" height="145px" src="sys/detail/style/images/food/${food.img }" />
										</a>
									</dt>
									
									
									<dd class="f1">
										<a href="${pageContext.request.contextPath }/indexServlet?method=foodDetail&id=${food.id}">${food.foodName }</a>
									</dd>
									<dd class="f2">
										<a href="${pageContext.request.contextPath }/indexServlet?method=foodDetail&id=${food.id}">&yen;${food.price }</a>
									</dd>
								</dl>
							</li>
					</c:forEach>
				</ul>
			</div>
			
			<div id="foot">
						<span
							style="float:left; line-height:53PX; margin-left:-50px; font-weight:bold; ">
							<span style="font-weight:bold">&lt;&lt;</span>
						</span>
				<div id="btn">
<!-- 底部分页-->
					<ul>
						<!-- 分页 -->
						当前页${pb.currentPage }/${pb.totalPage }
					<li><a href="javascript:gotoPage(1)">首页</a></li>
					<li><a href="javascript:gotoPage(${pb.currentPage-1})">上一页</a></li>
					<li><a href="javascript:gotoPage(${pb.currentPage+1})">下一页</a></li>
					<li><a href="javascript:gotoPage(${pb.totalPage})">尾页</a></li>
					
					
					</ul>
				</div>
			</div>
			
		</div>
<!-- 右边-->
<form action="${pageContext.request.contextPath }/indexServlet?method=indexDetail"   method="post">
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
				
				
<!-- 购物车 -->
					<li>
						<a href="${pageContext.request.contextPath }/indexServlet?method=orderList">
							<img src="app/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>
<!-- 菜系 -->
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
<!-- 搜索菜品 -->
					<table width="100px">
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
	<c:if test="${!empty requestScope.food}">
		<script type="text/javascript">
			//获取页面的高度、宽度
			var sHeight = document.documentElement.scrollHeight;
			var sWidth = document.documentElement.scrollWidth;
			var oMask = document.createElement("div");
				oMask.id = "mask";
				oMask.style.height = sHeight + "px";
				oMask.style.width = sWidth + "px";
				document.body.appendChild(oMask);
		
			//获取可使区域的高度、宽度
			var wHeight = document.documentElement.clientHeight;
			var wWidth = document.documentElement.clientWidth;
			var oMenu1 = document.createElement("div");
				oMenu1.id = "menu1";
				//oMenu1.innerHTML="<div class='menu2' style='text-align:center;'> <img src='app/detail/style/images/order_detials_bg.png' /> </div><div class='menu3'><div class='menu3_left'><img src='sys/detail/style/images/food/${requestScope.food.img }' style='width:270px; height:290px;' /></div><div class='menu3_right'><p>${requestScope.food.foodName }</p><p>价格:&nbsp;&nbsp;&nbsp;&nbsp;&yen;&nbsp;${requestScope.food.price }</p><p>简介:${requestScope.food.introduce }</p></div></div> <div class='menu4'>    <a href='${pageContext.request.contextPath }/indexServlet?method=clientCart&id=${requestScope.food.id }' style='background:url(app/detail/style/images/img/order_left_corner_bg.png);'>放入餐车</a>    <a href='#' onclick='javascript:history.go(-1);' style='background:url(app/detail/style/images/img/order_right_corner_bg.png);'>返回</a></div><div id='close'></div>"
				oMenu1.innerHTML="<div class='menu2' style='text-align:center;'> <img src='app/detail/style/images/order_detials_bg.png' /> </div><div class='menu3'><div class='menu3_left'><img src='sys/detail/style/images/food/${requestScope.food.img }' style='width:270px; height:290px;' /></div><div class='menu3_right'><p>${requestScope.food.foodName }</p><p>价格:&nbsp;&nbsp;&nbsp;&nbsp;&yen;&nbsp;${requestScope.food.price }</p><p>简介:${requestScope.food.introduce }</p></div></div> <div class='menu4'>  <div class='sp'>  <a class='de' href='javascript:deNum()'  style='font-size:30px;' >-</a> <input id='foodNum' type='text' value='1' name='num' size='3' lang='3' onblur='alterSorder(this)'/> <a class='add'  href='javascript:addNum()'   style='font-size:30px;' >+</a> </div><div class='spe'>  <a class='igo'  style='font-size:15px;'  href='#' onclick='javascript:addOrder();' >加入购物车</a>  <a class='iback' href='#' onclick='javascript:history.go(-1);' style='background:url(app/detail/style/images/img/order_right_corner_bg.png);'>返回</a></div> </div> <div id='close'></div>"
				
				//<a href="javascript:addNum()">-</a> <input id="foodNum" type="text" value="1" name="num" size="3" lang="3" onblur="alterSorder(this)"/> <a href="javascript:deNum()">+</a>
				document.body.appendChild(oMenu1);
			var dHeight = oMenu1.offsetHeight;
			var dWidth = oMenu1.offsetWidth;
				oMenu1.style.left = (sWidth - dWidth)/2 + "px";
				oMenu1.style.top = (wHeight - dHeight)/2 + "px";

			var oClose = document.getElementById("close");
				oClose.onclick = function(){
					document.body.removeChild(oMask);
					document.body.removeChild(oMenu1);
					}
		</script>
	</c:if>
	
</body>
</html>
