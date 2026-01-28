package com.duft.product_service.Domains.Entities;

public class Inventory {
    private Integer inventoryId;
    private Integer productId;
    private Integer quantity;
    private String location;
    
    public Inventory(Integer inventoryId, Integer productId, Integer quantity, String location) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
        this.location = location;
    }
    public Inventory() {
    }
    public Integer getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return "Inventory [inventoryId=" + inventoryId + ", productId=" + productId + ", quantity=" + quantity
                + ", location=" + location + "]";
    }
    
    

}
