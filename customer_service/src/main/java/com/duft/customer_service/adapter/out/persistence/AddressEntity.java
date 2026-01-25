package com.duft.customer_service.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Address")
public class AddressEntity {
    @Id
    @Column(name = "address_id")
    private int addressID;
    @Column(name = "customer_id")
    private int customerID;
    @Column(name = "address")
    private String Address;
    @Column(name = "city")
    private String City;
    @Column(name = "state")
    private String State;
    @Column(name = "country")
    private String Country;
    @Column(name = "pin_code")
    private Long PinCode;

    public AddressEntity(int addressID, int customerID, String address, String city, String state, String country,
            Long pinCode) {
        this.addressID = addressID;
        this.customerID = customerID;
        Address = address;
        City = city;
        State = state;
        Country = country;
        PinCode = pinCode;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public Long getPinCode() {
        return PinCode;
    }

    public void setPinCode(Long PinCode) {
        this.PinCode = PinCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AddressEntity{");
        sb.append("addressID=").append(addressID);
        sb.append(", customerID=").append(customerID);
        sb.append(", Address=").append(Address);
        sb.append(", City=").append(City);
        sb.append(", State=").append(State);
        sb.append(", Country=").append(Country);
        sb.append(", PinCode=").append(PinCode);
        sb.append('}');
        return sb.toString();
    }


}
