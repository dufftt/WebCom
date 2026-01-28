package com.duft.customer_service.Adapters.out.persistence.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "mob_number")
    private Long mobNumber;

    @Column(name = "email")
    private String email;

    // constructors, getters, setters
    public CustomerEntity() {}
    public CustomerEntity(String name, Long mobNumber, String email) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomerEntity{");
        sb.append("customerId=").append(customerId);
        sb.append(", name=").append(name);
        sb.append(", mobNumber=").append(mobNumber);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
}