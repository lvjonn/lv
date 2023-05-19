<%-- 
    Document   : shipmentManagement
    Created on : 16 May 2023, 10:36:08 pm
    Author     : aljonnsantos
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="iot.model.Shipment"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Shipment Management Page</title>
        <link rel="stylesheet" type="text/css" href="shipMan.css">
        
</head>
    <body>
        
    <% 
        LinkedList<Shipment> shipments = ( LinkedList<Shipment> ) session.getAttribute("shipments");
    
    %>
    <h1><a href="index.jsp">Shipments</a></h1>
	<form method="post" action="ViewShipmentServlet">
		<label for="shipmentId">Shipment ID:</label>
		<input type="text" id="shipmentId" name="shipmentId">
		<label for="date">Date:</label>
		<input type="date" id="date" name="date">
		<button type="submit">Search</button>
	</form>
	<hr>
	
	<% if(shipments != null) {%>
		<table>
			<tr>
				<th>Shipment ID</th>
                                <th>Order ID</th>
				<th>Date</th>
				<th>Shipping Method</th>
                                <th>Status</th>
			</tr>
			<%   
                        for(Shipment s : shipments){
                        %>
				<tr>
					<td><%=s.getShipmentId()%></td>
                                        <td><%=s.getOrderId()%></td>
					<td><%=s.getDate()%></td>
					<td><%=s.getShipmentMethod()%></td>
                                        <td><%=s.getStatus()%></td>
				</tr>
			<%   
                            }
                        %>
		</table>
	<%} else{ %>
		<p>Use the search button to find shipments.</p>
        <%}%>
        
        
        
        
        
    </body>
</html>
