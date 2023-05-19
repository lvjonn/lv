/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iot.controller;

/**
 *
 * @author aljonnsantos
 */
 import java.io.IOException;

   import java.sql.Connection;
   import java.sql.SQLException;
   import java.util.logging.Level;
   import java.util.logging.Logger;
   import jakarta.servlet.ServletException;
   import jakarta.servlet.http.HttpServlet;
   import jakarta.servlet.http.HttpServletRequest;
   import jakarta.servlet.http.HttpServletResponse;
   import jakarta.servlet.http.HttpSession;
   import iot.model.dao.*;

   public class ConnServlet extends HttpServlet {
       private DBConnector db;
       private DBManager manager;
       private Connection conn;

       @Override //Create and instance of DBConnector for the deployment session

       public void init() {
           try {
               db = new DBConnector(); //creates database connection when app starts
           } catch (ClassNotFoundException | SQLException ex) {
               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
           }      
       }
       @Override //Add the DBConnector, DBManager, Connection instances to the session
       //creates instance of DBManager and adds to current session
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");       
           HttpSession session = request.getSession();
           conn = db.openConnection();       
           try {
               manager = new DBManager(conn);
           } catch (SQLException ex) {
               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
           //export the DB manager to the view-session (JSPs)
           session.setAttribute("manager", manager);           
       }   
       @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
        public void destroy() {
           try {
               db.closeConnection();
           } catch (SQLException ex) {
               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
