package com.example.onwayinsure1;

import android.graphics.Bitmap;
import android.net.Uri;

public class SOSMessage {

    private String Vehicle;
    private int InsuranceNo;
    private int CustomerNo;
    private String Customer;
    private String ContactNo;
    private String DateTime;
    private String Agent;
    private String Branch;
    private String Longitude;
    private String Latitude;
    private Bitmap Photo;

    public SOSMessage() {
    }

    public SOSMessage(String vehicle, int insuranceNo, int customerNo, String customer, String contactNo, String dateTime, String agent, String branch, String longitude, String latitude, Bitmap photo) {
        Vehicle = vehicle;
        InsuranceNo = insuranceNo;
        CustomerNo = customerNo;
        Customer = customer;
        ContactNo = contactNo;
        DateTime = dateTime;
        Agent = agent;
        Branch = branch;
        Longitude = longitude;
        Latitude = latitude;
        Photo = photo;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String vehicle) {
        Vehicle = vehicle;
    }

    public int getInsuranceNo() {
        return InsuranceNo;
    }

    public void setInsuranceNo(int insuranceNo) {
        InsuranceNo = insuranceNo;
    }

    public int getCustomerNo() {
        return CustomerNo;
    }

    public void setCustomerNo(int customerNo) {
        CustomerNo = customerNo;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getAgent() {
        return Agent;
    }

    public void setAgent(String agent) {
        Agent = agent;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public Bitmap getPhoto() {
        return Photo;
    }

    public void setPhoto(Bitmap photo) {
        Photo = photo;
    }
}
