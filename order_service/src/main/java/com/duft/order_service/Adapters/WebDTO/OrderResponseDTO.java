package com.duft.order_service.Adapters.WebDTO;

import java.util.List;

public class OrderResponseDTO {

    private Integer OrderId;
    private Integer customerId;
    private Boolean status;
    private Integer total;
    private String created_date;
    private List<OrderItemResponseDTO> orderItems;
    
    public OrderResponseDTO(Integer orderId, Integer customerId, Boolean status, Integer total, String created_date,
            List<OrderItemResponseDTO> orderItems) {
        OrderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.total = total;
        this.created_date = created_date;
        this.orderItems = orderItems;
    }

    public OrderResponseDTO() {
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
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

    public List<OrderItemResponseDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponseDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO [OrderId=" + OrderId + ", customer_id=" + customerId + ", status=" + status
                + ", total=" + total + ", created_date=" + created_date + ", orderItems=" + orderItems + "]";
    }


}
