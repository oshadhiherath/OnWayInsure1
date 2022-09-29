package com.example.onwayinsure1;

import android.graphics.Bitmap;

public class Claim {

    private String Vehicle;
    private int CustomerNo;
    private String Customer;
    private String  VehicleType;
    private int InsuranceNo;
    private Bitmap EnginePhoto;
    private Bitmap ChasisPhoto;
    private Bitmap VehiclePhoto;
    private Bitmap ObjectPhoto;
    private Bitmap DriverPhoto;
    private Bitmap LicenceFrontPhoto;
    private Bitmap LincenceBackPhoto;
    private String ApplyDate;
    private String ContactNo;
    private String AddressNote;


    public Claim() {
    }

    public Claim(String vehicle, int customerNo, String customer, String vehicleType, int insuranceNo, Bitmap enginePhoto, Bitmap chasisPhoto, Bitmap vehiclePhoto, Bitmap objectPhoto, Bitmap driverPhoto, Bitmap licenceFrontPhoto, Bitmap lincenceBackPhoto, String applyDate, String contactNo, String addressNote) {
        Vehicle = vehicle;
        CustomerNo = customerNo;
        Customer = customer;
        VehicleType = vehicleType;
        InsuranceNo = insuranceNo;
        EnginePhoto = enginePhoto;
        ChasisPhoto = chasisPhoto;
        VehiclePhoto = vehiclePhoto;
        ObjectPhoto = objectPhoto;
        DriverPhoto = driverPhoto;
        LicenceFrontPhoto = licenceFrontPhoto;
        LincenceBackPhoto = lincenceBackPhoto;
        ApplyDate = applyDate;
        ContactNo = contactNo;
        AddressNote = addressNote;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String vehicle) {
        Vehicle = vehicle;
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

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public int getInsuranceNo() {
        return InsuranceNo;
    }

    public void setInsuranceNo(int insuranceNo) {
        InsuranceNo = insuranceNo;
    }

    public Bitmap getEnginePhoto() {
        return EnginePhoto;
    }

    public void setEnginePhoto(Bitmap enginePhoto) {
        EnginePhoto = enginePhoto;
    }

    public Bitmap getChasisPhoto() {
        return ChasisPhoto;
    }

    public void setChasisPhoto(Bitmap chasisPhoto) {
        ChasisPhoto = chasisPhoto;
    }

    public Bitmap getVehiclePhoto() {
        return VehiclePhoto;
    }

    public void setVehiclePhoto(Bitmap vehiclePhoto) {
        VehiclePhoto = vehiclePhoto;
    }

    public Bitmap getObjectPhoto() {
        return ObjectPhoto;
    }

    public void setObjectPhoto(Bitmap objectPhoto) {
        ObjectPhoto = objectPhoto;
    }

    public Bitmap getDriverPhoto() {
        return DriverPhoto;
    }

    public void setDriverPhoto(Bitmap driverPhoto) {
        DriverPhoto = driverPhoto;
    }

    public Bitmap getLicenceFrontPhoto() {
        return LicenceFrontPhoto;
    }

    public void setLicenceFrontPhoto(Bitmap licenceFrontPhoto) {
        LicenceFrontPhoto = licenceFrontPhoto;
    }

    public Bitmap getLincenceBackPhoto() {
        return LincenceBackPhoto;
    }

    public void setLincenceBackPhoto(Bitmap lincenceBackPhoto) {
        LincenceBackPhoto = lincenceBackPhoto;
    }

    public String getApplyDate() {
        return ApplyDate;
    }

    public void setApplyDate(String applyDate) {
        ApplyDate = applyDate;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getAddressNote() {
        return AddressNote;
    }

    public void setAddressNote(String addressNote) {
        AddressNote = addressNote;
    }
}
