<%-- 
    Document   : editAddressDetails
    Created on : 18 May 2023, 4:12:29 pm
    Author     : aljonnsantos
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="iot.model.*"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Address Page</title>
  <link rel="stylesheet" href="addAddressDetails.css">
</head>
<body>
    <%
    Address ad = (Address) session.getAttribute("editAddress");
    String zipErr = (String) session.getAttribute("zipErr");
    %>
    <h1><a href="addressDetails.jsp">Edit Address Details</a></h1>
  <form action="editAddressServlet" method="post">

    <div class="form-group">
      <label for="street">Street:</label>
      <input type="text" id="street" name="street" value="<%=ad.getStreet()%>" required>
    </div>

    <div class="form-group">
      <label for="city">City:</label>
      <input type="text" id="city" name="city" value="<%=ad.getCity()%>" required >
    </div>

    <div class="form-group">
      <label for="state">State:</label>
      <select id="state" name="state" required>
        <option value="" disabled selected>Select a state</option>
        <option value="ACT">ACT</option>
        <option value="NSW">NSW</option>
        <option value="NT">NT</option>
        <option value="Qld">Qld</option>
        <option value="SA">SA</option>
        <option value="Vic">Vic</option>
        <option value="Tas">Tas</option>
        <option value="WA">WA</option>
      </select>
    </div>

    <div class="form-group">
      <label for="zipCode">Zip Code:</label>
      <input type="text" id="zipCode" name="zipCode" required value="<%=ad.getZipCode()%>">
    </div>
    <h3 style="color:red;"><%=(zipErr != null ? zipErr : "")%></h3>
    <button type="submit">Edit</button>
  </form>
</body>
</html>
