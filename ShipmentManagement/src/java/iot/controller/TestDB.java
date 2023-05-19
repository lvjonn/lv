/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iot.controller;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import iot.model.Shipment;
import iot.model.dao.*;

/**
 *
 * @author aljonnsantos
 */
public class TestDB {
    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBManager db;
    
//    public static void main(String[] args) throws SQLException{
//        // (new TestDB()).runQueries();
//    }
    public static void main(String[] args) throws SQLException {
        
        (new TestDB()).runQueries();
    }
    
    public TestDB(){
        try{
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void runQueries() {
        char c;
        while((c = readChoice()) != '*'){
            switch(c){
                case 'C':
                    testAdd();
                    break;
                case 'R':
                    testRead();
                    break;
                case 'U':
                    testUpdate();
                    break;
                case 'D':
                    testDelete();
                    break;
                default:
                    System.out.println("Unknown Command");
            }
        }
    }
    private char readChoice(){
        System.out.print("Enter choice: ");
        return Character.toUpperCase(in.nextLine().charAt(0));
    }
    private void testAdd(){
        System.out.println("-Create New Shipment-");
        System.out.print("ShipmentID: ");
        String shipId = in.nextLine();
        System.out.print("UserId: ");
        String userId = in.nextLine();
        System.out.print("Shipment Method: ");
        String shipMethod = in.nextLine();
        System.out.print("Date: ");
        String date = in.nextLine();
        System.out.print("Street: ");
        String street = in.nextLine();
        System.out.print("City: ");
        String city = in.nextLine();
        System.out.print("State: ");
        String state = in.nextLine();
        System.out.print("Zip Code: ");
        String zipCode = in.nextLine();
        System.out.print("OrderId: ");
        String orderId = in.nextLine();
        System.out.println("Status: ");
        String status = in.nextLine();
        
        try{
            db.addShipment(shipId, userId, shipMethod, date, street, city, state, zipCode, orderId, status);
        } catch(SQLException ex){
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    private void testRead() {
        System.out.print("READ: Enter shipmentId: ");
        String shipId = in.nextLine();
        System.out.print("Enter date: ");
        String date = in.nextLine();
        System.out.print("Enter userId: ");
        String userId = in.nextLine();
        
        
         try{
            LinkedList<Shipment> ships = db.findShipment(shipId, date, userId);
            for(Shipment s : ships){
                System.out.println(s.toString());
            }
            
            
         } catch(SQLException ex){
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    private void testUpdate(){
        System.out.print("UPDATE: Enter shipment ID: ");
        String shipId = in.nextLine();
        
        try{
            if(db.checkShipment(shipId)){
                System.out.print("Enter new Shipment Method: ");
                String shipMet = in.nextLine();
                System.out.print("Enter new date: ");
                String date = in.nextLine();
                System.out.print("Enter new street: ");
                String street = in.nextLine();
                System.out.print("Enter new city: ");
                String city = in.nextLine();
                System.out.print("Enter new state: ");
                String state = in.nextLine();
                System.out.print("Enter new zipCode: ");
                String zipCode = in.nextLine();
                
                db.updateShipment(shipId, shipMet, date, street, city, state, zipCode);
                
            } else{
                System.out.println("Shipment ID not found.");
            }
            
        } catch(SQLException ex){
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    private void testDelete(){
        
        System.out.println("DELETE: Enter shipment ID: ");
        String shipId = in.nextLine();
        try{
            if(db.checkShipment(shipId)){
                db.deleteShipment(shipId);
            }else{
                System.out.println("Shipment ID not found.");
            }
        } catch(SQLException ex){
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
   
    
}
