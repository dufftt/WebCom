package com.duft.order_service.Adapters.out.persistence.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer customerId;
    private Boolean status = false;
    private Integer total;
    private String created_date;
    
    public OrderEntity(Integer orderId, Integer customerId, Boolean status, Integer total, String created_date) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.total = total;
        this.created_date = created_date;
    }
    public OrderEntity() {
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public String getCreated_date() {
        return created_date;
    }
    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
    @Override
    public String toString() {
        return "OrderEntity [OrderId=" + orderId + ", customer_id=" + customerId + ", status=" + status + ", total="
                + total + ", created_date=" + created_date + "]";
    }

    
}
