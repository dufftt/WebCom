/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.duft.order_service.Adapters.WebDTO;


public class OrderItemRequest {
    private Integer product_id;
    private Integer quantity;
    public OrderItemRequest(Integer product_id, Integer quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }
    public OrderItemRequest() {
    }
    public Integer getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "OrderItemRequest [product_id=" + product_id + ", quantity=" + quantity + "]";
    }

    

}
