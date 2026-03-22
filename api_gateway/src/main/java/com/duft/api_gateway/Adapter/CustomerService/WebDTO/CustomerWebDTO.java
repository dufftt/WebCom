package com.duft.api_gateway.Adapter.CustomerService.WebDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerWebDTO {
    private Integer customerId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mobNumber")
    private String mobNumber;
    @JsonProperty("email")
    private String email;
    public CustomerWebDTO(Integer customerId, String name, String mobNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.mobNumber = mobNumber;
        this.email = email;
    }
    public CustomerWebDTO() {
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
    public String getMobNumber() {
        return mobNumber;
    }
    public void setMobNumber(String mobNumber) {
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
        return "CustomerWebDTO [customerId=" + customerId + ", name=" + name + ", mobNumber=" + mobNumber + ", email="
                + email + "]";
    }

    
}
