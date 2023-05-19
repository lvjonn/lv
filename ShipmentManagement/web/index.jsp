<%-- 
    Document   : index
    Created on : 17 May 2023, 12:34:29 am
    Author     : aljonnsantos
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>My E-commerce Site</title>
	<link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
	<h1>Welcome to My E-commerce Site</h1>
	<ul>
            <form method="post">       
                <li><button type="submit" formaction="ViewAddressServlet">Enter Shipping Details</button></li>
		<li><button type="submit" formaction="shipmentManagement.jsp">Shipment Management</button></li>
            </form>
	</ul>
        <jsp:include page="/ConnServlet" flush="true" />
                <%
        session.setAttribute("userId", "1001"); //need to get this from Hoon
        session.setAttribute("orderId","1235"); //need to get from Jae
        %>
</body>
</html>

