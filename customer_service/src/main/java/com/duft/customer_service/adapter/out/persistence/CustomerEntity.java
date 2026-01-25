package com.duft.customer_service.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mob_number")
    private Long mobNumber;

    @Column(name = "email")
    private String email;

    // constructors, getters, setters
    public CustomerEntity() {}
    public CustomerEntity(Integer customerId, String name, Long mobNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.mobNumber = mobNumber;
        this.email = email;
    }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getMobNumber() { return mobNumber; }
    public void setMobNumber(Long mobNumber) { this.mobNumber = mobNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}