package com.duft.customer_service.Adapters.WebDTOs;

import lombok.NonNull;

public class AddressDTO {

    private Integer addressId;
    @NonNull
    private Integer customerId;
    @NonNull
    private String address;
    private String city;
    private String state;
    private String country;
    @NonNull
    private Long pinCode;

    public AddressDTO(Integer addressId, @NonNull Integer customerId, @NonNull String address, String city,
            String state, String country, @NonNull Long pinCode) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    public AddressDTO() {
    }

    public Integer getAddressId() {
        return addressId;
    }
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }


    
    public Long getPinCode() {
        return pinCode;
    }
    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }


    @Override
    public String toString() {
        return "AddressDTO [addressId=" + addressId + ", customerId=" + customerId + ", address=" + address + ", city="
                + city + ", state=" + state + ", country=" + country + ", pinCode=" + pinCode + "]";
    }



}
