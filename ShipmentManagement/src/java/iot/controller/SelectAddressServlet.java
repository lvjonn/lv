/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iot.controller;

import iot.model.Address;
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
public class SelectAddressServlet extends HttpServlet {

   @Override   
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
             //1- retrieve the current session
             HttpSession session = request.getSession();

             //5- retrieve the manager instance from session      
             DBManager manager = (DBManager) session.getAttribute("manager"); 
             
             //3- capture the posted info         
             String userId = (String) session.getAttribute("userId");
            
             String addId = request.getParameter("addressId");
            
             
                 try {       
                    Address ad = manager.findAddress(addId, userId);
                    session.setAttribute("address", ad);
                    request.getRequestDispatcher("shipmentOrder.jsp").include(request, response);
                    
                 } catch (SQLException ex) {           
                   Logger.getLogger(CreateShipmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println(ex.getMessage() == null ? "Error" : "Welcome");
                 } 
             }
        }  

