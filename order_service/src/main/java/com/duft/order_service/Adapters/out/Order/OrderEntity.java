package com.duft.order_service.Adapters.out.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer OrderId;
    private Integer customer_id;
    private Boolean status = false;
    private Integer total;
    private String created_date;
    public OrderEntity(Integer orderId, Integer customer_id, Boolean status, Integer total, String created_date) {
        OrderId = orderId;
        this.customer_id = customer_id;
        this.status = status;
        this.total = total;
        this.created_date = created_date;
    }
    public OrderEntity() {
    }
    public Integer getOrderId() {
        return OrderId;
    }
    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
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
        return "OrderEntity [OrderId=" + OrderId + ", customer_id=" + customer_id + ", status=" + status + ", total="
                + total + ", created_date=" + created_date + "]";
    }

    
}
