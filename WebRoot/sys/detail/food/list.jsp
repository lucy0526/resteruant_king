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
</style> 
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/detail/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath }/foodServlet?method=list" method="post">
			<input type="text" name="keyword" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
        
			<c:choose>
				<c:when test="${not empty requestScope.pb}">
					<c:forEach var="food" items="${requestScope.pb.pageData}">
						<tr class="TableDetail1">
							<td>${food.id }&nbsp;</td>
							<td>${food.foodName }&nbsp;</td>
							<td>${food.foodType_name }&nbsp;</td>
							<td>${food.price }&nbsp;</td>
               				<td>${food.m_price }&nbsp;</td>
							<td>
							<a href="${pageContext.request.contextPath }/foodServlet?method=updateView&id=${food.id}"  class="FunctionButton">更新</a>				
							<a href="${pageContext.request.contextPath }/foodServlet?method=delete&id=${food.id}" class="FunctionButton">删除</a>				
							</td>
						</tr>
						
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td clospan="3">没有你要找的数据，请先保存记录再查看！</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
        </tbody>
    </table>
	
	<div id="btn">
<!-- 底部分页-->
					<ul>
						<!-- 分页 -->
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前页${pb.currentPage }/${pb.totalPage }
						
					<li><a href="${pageContext.request.contextPath }/foodServlet?method=list&currentPage=1&keywordlib=${requestScope.keywordlib}">首页</a></li>
					<li><a href="${pageContext.request.contextPath }/foodServlet?method=list&currentPage=${pb.currentPage-1}&keywordlib=${requestScope.keywordlib}">上一页</a></li>
					<li><a href="${pageContext.request.contextPath }/foodServlet?method=list&currentPage=${pb.currentPage+1}&keywordlib=${requestScope.keywordlib}">下一页</a></li>
					<li><a href="${pageContext.request.contextPath }/foodServlet?method=list&currentPage=${pb.totalPage}&keywordlib=${requestScope.keywordlib}">尾页</a></li>
					
					
					</ul>
	</div>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="right">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/foodServlet?method=addView">添加</a></div>
    </div> 
</div>
</body>
</html>
