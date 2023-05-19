/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iot.controller;

import iot.model.dao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServlet;
import iot.model.*;


/**
 *
 * @author aljonnsantos
 */
public class editAddressServlet extends HttpServlet {

    @Override   
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
             //1- retrieve the current session
             HttpSession session = request.getSession();

             //3- capture the posted info         
             String userId = (String) session.getAttribute("userId");
            
             Address ad = (Address) session.getAttribute("editAddress");
   
             String addId = ad.getAddressId();
             
             String street = request.getParameter("street");
             String city = request.getParameter("city");
             String state = request.getParameter("state");
             String zipCode = request.getParameter("zipCode");
             
             Validator validator = new Validator();
             validator.clear(session);
             //5- retrieve the manager instance from session      
             DBManager manager = (DBManager) session.getAttribute("manager");    
             
             if(!validator.validateZipCode(zipCode)){
                      //8-set incorrect date error to the session           
                      session.setAttribute("zipErr","Error: Zip Code format incorrect.");
                      //9- redirect user back to the ?.jsp     
                      request.getRequestDispatcher("editAddressDetails.jsp").include(request, response);
                 
             } else{
                 try {       
                    //6- find address
                    manager.updateAddress(addId, street, city, state, zipCode);
                    //session.setAttribute("addresses", addresses);
                    request.getRequestDispatcher("ViewAddressServlet").include(request, response);
                    
                 } catch (SQLException ex) {           
                   Logger.getLogger(CreateShipmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println(ex.getMessage() == null ? "Error" : "Welcome");
                 } 
             }
        }   
}
