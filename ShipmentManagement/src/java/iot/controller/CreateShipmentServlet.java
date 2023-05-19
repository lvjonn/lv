/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import iot.model.Shipment;
import iot.model.dao.DBManager;
import java.util.*;

/**
 *
 * @author aljonnsantos
 */
public class CreateShipmentServlet extends HttpServlet {
    @Override   
     protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       
             //1- retrieve the current session
             HttpSession session = request.getSession();
             //2- create an instance of the Validator class    
             Validator validator = new Validator();
             //3- capture the posted info      
             String shipmentMethod = request.getParameter("shipmentMethod");
             String date = request.getParameter("date"); 
             String street = request.getParameter("street");
             String city = request.getParameter("city");
             String state = request.getParameter("state");
             String zipCode = request.getParameter("zipCode");
             String status = "Processing";
             
             String userId = (String) session.getAttribute("userId");
             String orderId = (String) session.getAttribute("orderId");
             
             //5- retrieve the manager instance from session      
             DBManager manager = (DBManager) session.getAttribute("manager");    
             validator.clear(session); //check this method

             if (!validator.validateDate(date)) {           
                      //8-set incorrect date error to the session           
                      session.setAttribute("dateErr","Error: Date must not be in the past.");
                      //9- redirect user back to the ?.jsp     
                      request.getRequestDispatcher("shipmentOrder.jsp").include(request, response);

              }else if(!validator.validateZipCode(zipCode)){
                    //8-set incorrect zipcode error to the session           
                      session.setAttribute("zipErr","Error: Zip Code format incorrect");
                      //9- redirect user back to the login.jsp     
                      request.getRequestDispatcher("shipmentOrder.jsp").include(request, response);
              }
              else {                       
                 try {       
                    //6- create shipment
                    //String shipmentId, String userId, String shipmentMethod, String date, String street, String city, String state, String zipCode
                    String shipmentId = manager.generateShipmentId();
                    manager.addShipment(shipmentId, userId, shipmentMethod, date, street, city, state, zipCode, orderId, status);
                    Shipment ship = new Shipment(shipmentId, userId, shipmentMethod, date, street, city, state, zipCode, orderId, status);
                    session.setAttribute("shipment", ship);
                    request.getRequestDispatcher("index.jsp").include(request, response);
                    
                 } catch (SQLException ex) {           
                   Logger.getLogger(CreateShipmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println(ex.getMessage() == null ? "Shipment does not exist" : "Welcome");
                   
                 }    
            }   
        
     }
}
