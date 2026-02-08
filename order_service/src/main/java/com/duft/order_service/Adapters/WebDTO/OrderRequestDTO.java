package com.duft.order_service.Adapters.WebDTO;

import java.util.List;

public class OrderRequestDTO {

    private Integer customer_id;
    private List<OrderItemRequest> orderItems;
    public OrderRequestDTO(Integer customer_id, List<OrderItemRequest> orderItems) {
        this.customer_id = customer_id;
        this.orderItems = orderItems;
    }
    public OrderRequestDTO() {
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
    @Override
    public String toString() {
        return "OrderRequestDTO [customer_id=" + customer_id + ", orderItems=" + orderItems + "]";
    }

    
}
