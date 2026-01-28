package com.duft.customer_service.Domain.Entities;

public class Customer {

    private Integer customerId;
    private String name;
    private Long mobNumber;
    private String email;

    public Customer() {
    }

    public Customer(Integer customerId, String name, Long mobNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.mobNumber = mobNumber;
        this.email = email;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(Long mobNumber) {
        this.mobNumber = mobNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{");
        sb.append("customerId=").append(customerId);
        sb.append(", name=").append(name);
        sb.append(", mobNumber=").append(mobNumber);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
}
