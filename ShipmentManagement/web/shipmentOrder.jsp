<%-- 
    Document   : shipment
    Created on : 16 May 2023, 10:21:09 pm
    Author     : aljonnsantos
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="iot.model.*"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Shipment Page</title>
  <link rel="stylesheet" href="shipmentOrder.css">
</head>
<body>
    
    <% 
        Address ad = (Address) session.getAttribute("address");
        String dateErr = (String) session.getAttribute("dateErr");
    %>
    
  <h1>Shipment Details</h1>
  <form action="CreateShipmentServlet" method="post">
    <div class="form-group">
      <label for="shipmentMethod">Shipment Method:</label>
      <select id="shipmentMethod" name="shipmentMethod" required>
        <option value="" disabled selected>Select your shipment method</option>
        <option value="Standard">Standard</option>
        <option value="Express">Express</option>
        <option value="Overnight">Overnight</option>
      </select>
    </div>

    <div class="form-group">
      <label for="date">Delivery Date:</label>
      <input type="date" id="date" name="date" required>
    </div>

    <div class="form-group">
      <label for="street">Street Address:</label>
      <input type="text" id="street" name="street" value="<%=ad.getStreet()%>" readonly>
    </div>

    <div class="form-group">
      <label for="city">City:</label>
      <input type="text" id="city" name="city" value="<%=ad.getCity()%>" readonly>
    </div>

    <div class="form-group">
      <label for="state">State:</label>
      <input type ="text" id="state" name="state" value="<%=ad.getState()%>" readonly>
    </div>

    <div class="form-group">
      <label for="zipCode">Zip Code:</label>
      <input type="text" id="zipCode" name="zipCode" value="<%=ad.getZipCode()%>" readonly>
    </div>

    <h3><%=(dateErr != null ? dateErr : "")%></h3>
    <button type="submit">Submit</button>
  </form>
</body>
</html>
