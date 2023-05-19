/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iot.controller;

/**
 *
 * @author aljonnsantos
 */
   import jakarta.servlet.http.HttpSession;
   import java.io.Serializable;
   import java.util.regex.Matcher;
   import java.util.regex.Pattern;

   import java.time.LocalDate;
   import java.time.format.DateTimeFormatter;


    //validates ZipCode and if Date is After current date.
   public class Validator implements Serializable{ 

   private String zipCodePattern = "^[0-9]{4}";
    
   public Validator(){    }       


   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       
      return match.matches(); 

   }        
   
   public boolean validateDate(String date){
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate currentDate = LocalDate.now();
    LocalDate dateToCompare = LocalDate.parse(date, dateFormat);
       return dateToCompare.isAfter(currentDate);
   }
         
   public boolean validateZipCode(String zipCode){
       return validate(zipCodePattern, zipCode);
   }
   
   public void clear(HttpSession session){
       session.setAttribute("dateErr", "");
       session.setAttribute("zipErr", "");
   }
   
}