package com.duft.order_service.Adapters.WebDTO;

import java.util.List;

import org.springframework.asm.Type;

public class OrderRequestDTO {

    private Integer customerId;
    private List<OrderItemRequest> orderItems;
    
    public OrderRequestDTO(Integer customerId, List<OrderItemRequest> orderItems) {
        this.customerId = customerId;
        this.orderItems = orderItems;
    }

    public OrderRequestDTO() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO [customer_id=" + customerId + ", orderItems=" + orderItems + "]";
    }

    public boolean validated() {
       if(this.getCustomerId()!=null){
        for(OrderItemRequest orderItemRequest: this.getOrderItems()){
            if(!orderItemRequest.validated()){
                return false;
            }
        }
        return true;
       }
       return false;
    }

    
}
