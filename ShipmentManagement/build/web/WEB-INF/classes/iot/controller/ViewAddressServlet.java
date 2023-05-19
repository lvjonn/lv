/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iot.controller;

import iot.model.Address;
import iot.model.dao.DBManager;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author aljonnsantos
 */
public class ViewAddressServlet extends HttpServlet {

     @Override   
     protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       
             //1- retrieve the current session
             HttpSession session = request.getSession();

             //3- capture the posted info         
             String userId = (String) session.getAttribute("userId");
             
             //5- retrieve the manager instance from session      
             DBManager manager = (DBManager) session.getAttribute("manager");    
             LinkedList<Address> addresses = new LinkedList<Address>();
             
                 try {       
                    //6- find address
                        addresses = manager.allMyAddress(userId);
                    
                    session.setAttribute("addresses", addresses);
                    request.getRequestDispatcher("addressDetails.jsp").include(request, response);
                    
                 } catch (SQLException ex) {           
                   Logger.getLogger(CreateShipmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println(ex.getMessage() == null ? "Shipment does not exist" : "Welcome");
                 }    
            }   
}
