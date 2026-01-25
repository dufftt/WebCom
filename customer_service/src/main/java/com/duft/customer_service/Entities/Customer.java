package com.duft.customer_service.Entities;


public class Customer {

    private int customerID;
    private String Name;
    private Long MobNumber;
    private String Email;

    public Customer(int customerID, String name, Long mobNumber, String email) {
        this.customerID = customerID;
        Name = name;
        MobNumber = mobNumber;
        Email = email;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Long getMobNumber() {
        return MobNumber;
    }

    public void setMobNumber(Long MobNumber) {
        this.MobNumber = MobNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{");
        sb.append("customerID=").append(customerID);
        sb.append(", Name=").append(Name);
        sb.append(", MobNumber=").append(MobNumber);
        sb.append(", Email=").append(Email);
        sb.append('}');
        return sb.toString();
    }



}
