<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/detail/style/css/index_1.css" />
	<script type="text/javascript">
		setInterval(function(){
			window.location.href = "/wirelessplatform/client.html?method=list";
		},1000 * 50);
	</script>
	

<style type="text/css">
#btn{
	width:900px;
	float:left;
	
}
#btn li{
	list-style:none;
	float:left;
}
#btn a{
	text-decoration:none;
	display:block;
	width:45px;
	height:10px;
	margin-top:0px;
	text-align:center;
	padding-top:4px;
	color:black;
	font-size:10px;
	font-family:"Courier New", Courier, monospace;
}
#btn a:hover{
	background-color: red;
}

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
right:0px;
top:0px;
}

table  
{  
    border-collapse: collapse;  
    margin: 2auto;  
    text-align: center;  
}  
table td, table th  
{  
    border: 1px solid #cad9ea;  
    color: #666;  
    height: 30px;  
}  
table thead th  
{  
    background-color: #CCE8EB;  
    width: 100px;  
}  
table tr:nth-child(odd)  
{  
    background: #fff;  
}  
table tr:nth-child(even)  
{  
    background: #F5FAFA;  
}  

</style> 
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath }/sys/detail/style/images/title_arrow.gif" /> 餐厅订单列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>订单编号</td>
					<td>餐桌名</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>状态</td>
					<td  onclick="select()">操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:choose>
					<c:when test="${not empty requestScope.list}">
						<c:forEach var="order" items="${requestScope.list}">
						
							<tr height="60">
					 		<td>${order.id }</td>
					 		<td>${order.dinnerTable_name }</td>
					 		<td>${order.orderDate }</td>
					 		<td>${order.totalPrice }</td>
					 		
					 		<c:if test="${order.orderStatus=='0'}">
					 				<td>未结账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 					
					 				</td>
					 		</c:if>	
					 		<c:if test="${order.orderStatus=='1'}">
					 				<td>已结账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 					
					 				</td>
					 		</c:if>		
					 			
					 		
					 		<td>
								<a href="${pageContext.request.contextPath }/orderDetailServlet?method=list&id=${order.id }" class="FunctionButton">详细</a>
								 
					 			<c:if test="${order.orderStatus=='0'}">
					 				<!--<a href="${pageContext.request.contextPath }/orderServlet?method=update&id=${order.id }&orderStatus=${order.orderStatus}" class="FunctionButton">结账</a>
					 				--><!--<a href="${pageContext.request.contextPath }/codeServlet?method=getCode&id=${order.id }" class="FunctionButton">结账</a>
					 				-->
					 				<a href="${pageContext.request.contextPath }/sys/detail/order/orderCode.jsp?&id=${order.id }" class="FunctionButton">结账</a>
					 			</c:if>	
					 			
					 		</td>
				 			</tr>
						
						</c:forEach>
						
						
					</c:when>
					<c:otherwise >
						<tr>
							<td clospan="3">
								没有你要找的数据，请先保存记录再查看！
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
	</div>
	
	<div id="btn">
<!-- 底部分页-->
					<ul>
						<!-- 分页 -->
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前页${pb.currentPage }/${pb.totalPage }
						
					<li><a href="${pageContext.request.contextPath }/orderServlet?method=list&myTag=sel&currentPage=1">首页</a></li>
					<li><a href="${pageContext.request.contextPath }/orderServlet?method=list&myTag=sel&currentPage=${pb.currentPage-1}">上一页</a></li>
					<li><a href="${pageContext.request.contextPath }/orderServlet?method=list&myTag=sel&currentPage=${pb.currentPage+1}">下一页</a></li>
					<li><a href="${pageContext.request.contextPath }/orderServlet?method=list&myTag=sel&currentPage=${pb.totalPage}">尾页</a></li>
					
					
					</ul>
	</div>
	
<script type="text/javascript">

	function select()
	{
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
			oMenu1.innerHTML="<form action='${pageContext.request.contextPath }/orderServlet?myTag=y&method=list'  method='post'> <table class='selectTable' align='center' cellspacing='0' cellpadding='0'><tr><td colspan='5'></td></tr>  <tr height='60'> <td>订单编号</td> <td><input type='text' name='id'/></td> </tr> <tr height='60'> <td>餐桌名</td> <td><input type='text' name='dinnerTable_name'/></td> </tr> <tr height='60'> <td>总金额</td> <td><input type='text' name='mintotalPrice'/>&nbsp;~&nbsp;<input type='text' name='maxtotalPrice'/></td> </tr> </table> <div id='close'></div>  <input type='submit' style='width:195px;' value='筛选'></form> "
				
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
	}
</script>

</body>
</html>
