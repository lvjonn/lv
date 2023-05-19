package iot.model;
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aljonnsantos
 */
public class Shipment {
    private String shipmentId;
    private String userId;
    private String shipmentMethod;
    private String date;
    //address
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String orderId;
    private String status;

    public Shipment(String shipmentId, String userId, String shipmentMethod, String date, String street, String city, String state, String zipCode, String orderId, String status) {
        this.shipmentId = shipmentId;
        this.userId = userId;
        this.shipmentMethod = shipmentMethod;
        this.date = date;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.orderId = orderId;
        this.status = status;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shipment{" + "shipmentId=" + shipmentId + ", userId=" + userId + ", shipmentMethod=" + shipmentMethod + ", date=" + date + ", street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", orderId=" + orderId + ", status=" + status + '}';
    }

}
