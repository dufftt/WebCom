package com.duft.order_service.domain.entities;

public class OrderItems {

    private Integer orderItemId;
    private Integer orderid;
    private Integer productId;
    private Integer quantity;
    private Integer price;
    public OrderItems(Integer orderItemId, Integer orderid, Integer productId, Integer quantity, Integer price) {
        this.orderItemId = orderItemId;
        this.orderid = orderid;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    public OrderItems() {
    }
    public Integer getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }
    public Integer getOrderid() {
        return orderid;
    }
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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
        return "OrderItems [orderItemId=" + orderItemId + ", orderid=" + orderid + ", productId=" + productId
                + ", quantity=" + quantity + ", price=" + price + "]";
    }




    
}
