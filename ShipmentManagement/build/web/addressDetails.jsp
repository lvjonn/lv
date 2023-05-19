<%-- 
    Document   : shipmentDetails
    Created on : 18 May 2023, 2:06:45 pm
    Author     : aljonnsantos
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="iot.model.Address"%>
<%@ page import="iot.controller.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Saved Address Details Page</title>
        <link rel="stylesheet" type="text/css" href="addrDetails.css">
        
</head>
    <body>
        
    <% 
        LinkedList<Address> addresses = ( LinkedList<Address> ) session.getAttribute("addresses");
        Validator validator = new Validator();
        validator.clear(request.getSession()); //clear error messages for all.
    
    %>
        <h1>Saved Address</h1>
        <form method="post">
		<button type="submit" formaction="addAddressDetails.jsp">Add</button>
        </form>
        
	<hr>
	<% if(addresses != null) {%>
		<table>
			<tr>
				<th>Address ID</th>
				<th>Street</th>
				<th>City</th>
                                <th>State</th>
                                <th>Zip Code</th>
			</tr>
			<%   
                        for(Address a : addresses){
                        %>
				<tr>
					<td><%=a.getAddressId()%></td>
					<td><%=a.getStreet()%></td>
					<td><%=a.getCity()%></td>
                                        <td><%=a.getState()%></td>
                                        <td><%=a.getZipCode()%></td>   
                                        </td>
				</tr>
			<%   
                            }
                        %>
		</table>
                <br/><hr>
                <form method="post">
                    <center>
                    <div class="form-group">
                    <label for="addressId">Address ID:</label>
                    <select id="addressId" name="addressId" required>
                    <option value="" disabled selected>Select your address details</option>
                    
                    <% 
                    for(Address a : addresses){
                    %>
                    <option value="<%=a.getAddressId()%>"><%=a.getAddressId()%></option>
                    <%} %>
                    </select>
                    </div>
                    </center>

                    <button type="submit" value = "Select" formaction="SelectAddressServlet">Select</button>

                    <button type="submit" value ="Edit" formaction="findAddressServlet">Edit</button>

                    <button type="submit" value ="Delete" formaction="deleteAddressServlet">Delete</button>
                </form>

	<%} else{ %>
		<p>Select an option.</p>
        <%}%>
        
    </body>
</html>