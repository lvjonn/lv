/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iot.model.dao;

import iot.model.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author aljonnsantos
 */
/* 
* DBManager is the primary DAO class to Stringeract with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

private Statement st;
   
public DBManager(Connection conn) throws SQLException {       
   st = conn.createStatement();   
}

//READ
//Find shipments by shipmentId or date in the database   
public LinkedList<Shipment> findShipment(String shipmentId, String date, String userId) throws SQLException {       
   //setup the select sql query string
   LinkedList<Shipment> shipments = new LinkedList<Shipment>();
   int input = 0;
   
   String fetch;
   if(shipmentId.length() != 0 && date.length() != 0){
        fetch = "select * from ISDUSER.Shipment where shipmentId = '" + shipmentId + "' and date = '" + date + "'" + " and userId = '" + userId + "'";
        input = 1;
   }
   else if(shipmentId.length() != 0){
       fetch = "select * from ISDUSER.Shipment where shipmentId = '" + shipmentId + "'" + " and userId = '" + userId + "'";
       input = 2;
   }
   else{
       fetch = "select * from ISDUSER.Shipment where date = '" + date + "'" + " and userId = '" + userId + "'";
       input = 3;
   }
     //execute this query using the statement field  
   ResultSet rs = st.executeQuery(fetch);
    while(rs.next()){
           String shipId = rs.getString(1);
           String shipDate = rs.getString(4);
           if(checkInput(input, shipId, shipmentId, shipDate, date)){
               String shipmentMethod = rs.getString(3);
               String street = rs.getString(5);
               String city = rs.getString(6);
               String state = rs.getString(7);
               String zipCode = rs.getString(8);
               String orderId = rs.getString(9);
               String status = rs.getString(10);
               shipments.add(new Shipment(shipId, userId, shipmentMethod, shipDate, street, city, state, zipCode, orderId, status));
           }
       
   }
   return shipments;   
}

private boolean checkInput(int input, String shipId1, String shipId2, String date1, String date2){
    if(input == 1){
        return (shipId1.equals(shipId2) && date1.equals(date2));
    }else if(input == 2){
        return (shipId1.equals(shipId2));
    }else if(input == 3){
        return (date1.equals(date2));
    }
    return false;
    
}
//CREATE
//Add a shipment to the database   
public void addShipment(String shipmentId, String userId, String shipmentMethod, String date, String street, String city, String state, String zipCode, String orderId, String status) throws SQLException {                   //code for add-operation       
  st.executeUpdate("INSERT INTO ISDUSER.Shipment VALUES ('" + shipmentId + "','" + userId + "','" + shipmentMethod + "','" + date + "','" + street + "','" + city + "','" + state + "','" + zipCode + "','"+ orderId +"','" + status + "')");   

}
//UPDATE
//update a user details in the database   
public void updateShipment(String shipmentId, String shipmentMethod, String date, String street, String city, String state, String zipCode) throws SQLException {       
   //code for update-operation   //cant change shipId, status, orderId
   st.executeUpdate("UPDATE ISDUSER.Shipment SET SHIPMENTMETHOD='" + shipmentMethod + "',DATE='" + date + "',STREET='" + street + "',CITY='" + city + "',STATE='" + state + "',ZIPCODE='" + zipCode +"' WHERE shipmentId='" + shipmentId +"'");
}       
//DELETE
//delete a shipment from the database   
public void deleteShipment(String shipmentId) throws SQLException{       
   //code for delete-operation  
   st.executeUpdate("DELETE FROM ISDUSER.Shipment WHERE SHIPMENTID='" + shipmentId + "'");
}

public boolean checkShipment(String shipmentId) throws SQLException {
    String fetch = "select * from ISDUSER.Shipment WHERE SHIPMENTID='" + shipmentId + "'";
    ResultSet rs = st.executeQuery(fetch);
    
    while(rs.next()){
        String shipId = rs.getString(1);
        if(shipmentId.equals(shipId))
            return true;
    }
    return false;
}

public String generateShipmentId() throws SQLException {
    String fetch = "SELECT * FROM ISDUSER.SHIPMENT ORDER BY LENGTH(SHIPMENTID), SHIPMENTID DESC";
    ResultSet rs = st.executeQuery(fetch);
    if(rs.next()){
        int shipId = Integer.parseInt(rs.getString("SHIPMENTID"));
        shipId++;
        return Integer.toString(shipId);
    }
    return "1"; //if there is no record, starts from 1
}
 
public LinkedList<Shipment> allMyShipments(String userId) throws SQLException{
    LinkedList<Shipment> shipments = new LinkedList<Shipment>();
    String fetch = "SELECT * FROM ISDUSER.SHIPMENT WHERE USERID = '" + userId + "'";
    ResultSet rs = st.executeQuery(fetch);
    while(rs.next()){
           String shipId = rs.getString(1);
           String shipDate = rs.getString(4);
           String shipmentMethod = rs.getString(3);
           String street = rs.getString(5);
           String city = rs.getString(6);
           String state = rs.getString(7);
           String zipCode = rs.getString(8);
           String orderId = rs.getString(9);
           String status = rs.getString(10);
           shipments.add(new Shipment(shipId, userId, shipmentMethod, shipDate, street, city, state, zipCode, orderId, status));
   }
    
    return shipments;
    
}

public LinkedList<Address> allMyAddress(String userId) throws SQLException{
    LinkedList<Address> addresses = new LinkedList<Address>();
    String fetch = "SELECT * FROM ISDUSER.ADDRESS WHERE USERID = '" + userId + "'";
    ResultSet rs = st.executeQuery(fetch);
    while(rs.next()){
           String addId = rs.getString(1);
           String street = rs.getString(3);
           String city = rs.getString(4);
           String state = rs.getString(5);
           String zipCode = rs.getString(6);
  
           addresses.add(new Address(addId, userId, street, city, state, zipCode));
   }
    
    return addresses;
    
}


public String generateAddressId() throws SQLException {
    String fetch = "SELECT * FROM ISDUSER.ADDRESS ORDER BY LENGTH(ADDRESSID), ADDRESSID DESC";
    ResultSet rs = st.executeQuery(fetch);
    if(rs.next()){
        int addId = Integer.parseInt(rs.getString("ADDRESSID"));
        addId++;
        return Integer.toString(addId);
    }
    return "1"; //if there is no record, starts from 1
}

public void addAddress(String addId, String userId, String street, String city, String state, String zipCode) throws SQLException {                   //code for add-operation       
  st.executeUpdate("INSERT INTO ISDUSER.ADDRESS VALUES ('" + addId + "', '" + userId + "', '" + street + "', '" + city + "', '" + state + "', '" + zipCode + "')");   
}

//UPDATE
//update a user details in the database   
public void updateAddress(String addId, String street, String city, String state, String zipCode) throws SQLException {       
   //code for update-operation   //cant change shipId, status,
   st.executeUpdate("UPDATE ISDUSER.ADDRESS SET STREET='" + street + "',CITY='" + city + "',STATE='" + state + "',ZIPCODE='" + zipCode +"' WHERE addressId='" + addId +"'");
}       
//DELETE
//delete a shipment from the database   
public void deleteAddress(String addId, String userId) throws SQLException{       
   //code for delete-operation  
   st.executeUpdate("DELETE FROM ISDUSER.ADDRESS WHERE ADDRESSID='" + addId + "' and USERID='"+ userId +"'");
}

public Address findAddress(String addId, String userId) throws SQLException{
    ResultSet rs = st.executeQuery("SELECT * FROM ISDUSER.ADDRESS WHERE ADDRESSID='" + addId + "' and USERID='" + userId + "'");
    if(rs.next()){
           String street = rs.getString(3);
           String city = rs.getString(4);
           String state = rs.getString(5);
           String zipCode = rs.getString(6);
           
           return new Address(addId, userId, street, city, state, zipCode);
    }
    return null;
}

}
