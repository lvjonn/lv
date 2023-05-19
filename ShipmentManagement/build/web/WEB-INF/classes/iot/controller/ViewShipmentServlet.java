/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iot.controller;

import iot.model.Shipment;
import iot.model.dao.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/**
 *
 * @author aljonnsantos
 */
public class ViewShipmentServlet extends HttpServlet {

    @Override   
     protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       
             //1- retrieve the current session
             HttpSession session = request.getSession();

             //3- capture the posted info      
             String shipmentId = request.getParameter("shipmentId");
             String date = request.getParameter("date"); 
             
             String userId = (String) session.getAttribute("userId");
             
             //5- retrieve the manager instance from session      
             DBManager manager = (DBManager) session.getAttribute("manager");    
             LinkedList<Shipment> shipments = new LinkedList<Shipment>();
             
                 try {       
                    //6- find shipment
                    if(shipmentId.length() == 0 && date.length() == 0)
                        shipments = manager.allMyShipments(userId);
                    else
                        shipments = manager.findShipment(shipmentId, date, userId);
                    
                    session.setAttribute("shipments", shipments);
                    request.getRequestDispatcher("shipmentManagement.jsp").include(request, response);
                    
                 } catch (SQLException ex) {           
                   Logger.getLogger(CreateShipmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println(ex.getMessage() == null ? "Shipment does not exist" : "Welcome");
                 }    
            }   
        
     }
    
    
