/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iot.controller;


import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import iot.model.Address;
import iot.model.dao.DBManager;
import java.util.*;

/**
 *
 * @author aljonnsantos
 */
public class AddAddressServlet extends HttpServlet {

    @Override   
     protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       
             //1- retrieve the current session
             HttpSession session = request.getSession();
             //2- create an instance of the Validator class    
             Validator validator = new Validator();
             //3- capturethe posted info      
             String street = request.getParameter("street");
             String city = request.getParameter("city");
             String state = request.getParameter("state");
             String zipCode = request.getParameter("zipCode");
             
             String userId = (String) session.getAttribute("userId");
             
             //5- retrieve the manager instance from session      
             DBManager manager = (DBManager) session.getAttribute("manager");    
             validator.clear(session); //check this method
             

               if(!validator.validateZipCode(zipCode)){
                    //8-set incorrect zipcode error to the session           
                      session.setAttribute("zipErr","Error: Zip Code format incorrect.");
                      //9- redirect user back to the login.jsp     
                      request.getRequestDispatcher("addAddressDetails.jsp").include(request, response);
              }
              else {                       
                 try {       
                    //6- create shipment
                    //String shipmentId, String userId, String shipmentMethod, String date, String street, String city, String state, String zipCode
                    String addId = manager.generateAddressId();
                    manager.addAddress(addId, userId, street, city, state, zipCode);
                    Address ad = new Address(addId, userId, street, city, state, zipCode);
                    session.setAttribute("adress", ad);
                    request.getRequestDispatcher("ViewAddressServlet").include(request, response);
                    
                 } catch (SQLException ex) {           
                   Logger.getLogger(CreateShipmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println(ex.getMessage() == null ? "Address error" : "Welcome");
                 }    
            }   
        
     }
}
