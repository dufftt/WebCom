package com.duft.order_service.Adapters.WebDTO;

public class OrderItemResponseDTO {

    private Integer orderItemId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer price;

   
    public OrderItemResponseDTO(Integer orderItemId, Integer orderId, Integer productId, Integer quantity,
            Integer price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }


    public OrderItemResponseDTO() {
    }


    public Integer getOrderItemId() {
        return orderItemId;
    }


    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }


    public Integer getOrderId() {
        return orderId;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Integer getProductId() {
        return productId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Integer getPrice() {
        return price;
    }


    public void setPrice(Integer price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "OrderItemResponseDTO [orderItemId=" + orderItemId + ", orderid=" + orderId + ", productId=" + productId
                + ", quantity=" + quantity + ", price=" + price + "]";
    }
    
    
}
