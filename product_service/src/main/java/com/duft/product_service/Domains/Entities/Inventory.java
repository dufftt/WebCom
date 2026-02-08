package com.duft.product_service.Domains.Entities;

public class Inventory {
    private Integer inventoryId;
    private Integer productId;
    private Integer quantity;
    private String location;
    private Integer lockedQuantities = 0;
   
    public Inventory(Integer inventoryId, Integer productId, Integer quantity, String location,
            Integer lockedQuantities) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
        this.location = location;
        this.lockedQuantities = lockedQuantities;
    }

    public Inventory() {
    }
    public Integer getLockedQuantities() {
        return lockedQuantities;
    }
    public void setLockedQuantities(Integer lockedQuantities) {
        this.lockedQuantities = lockedQuantities;
    }
    public void lockQuantity(Integer quantity){
        this.setLockedQuantities(this.lockedQuantities+quantity);
        this.setQuantity(this.quantity - quantity);
    }
    public void releaseInventory(Integer quantity){
        this.setLockedQuantities(this.lockedQuantities-quantity);
                this.setQuantity(this.quantity + quantity);

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
                + ", location=" + location + ", lockedQuantities=" + lockedQuantities + "]";
    }
    
    

}
