<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>无线点餐平台</title>
</head>
<body>
		<%  
	        //获得number  
	        String id=request.getParameter("id");  
     	%>
	    <img style="height:80px;width:80px" src="${pageContext.request.contextPath }/codeServlet?method=getCode&id=<%=id %>" /> 
	    <a href="${pageContext.request.contextPath }/orderServlet?method=update&id=<%=id %>">确认付款</a>
</body>
</html>
