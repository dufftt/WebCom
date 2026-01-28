package com.duft.customer_service.Domain.Entities;

public class Address {

    private Integer addressId;
    private Integer customerId;
    private String address;
    private String city;
    private String state;
    private String country;
    private Long pinCode;

    public Address() {
    }

    public Address(Integer addressId, Integer customerId, String address, String city, String state, String country, Long pinCode) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append("addressID=").append(addressId);
        sb.append(", customerID=").append(customerId);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", country=").append(country);
        sb.append(", pinCode=").append(pinCode);
        sb.append('}');
        return sb.toString();
    }
}
