package com.duft.product_service.Adapters.out.Inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
    private Integer productId;
    @PositiveOrZero(message="quantity must never go to negative")
    private Integer quantity;
    private String location;
    private Integer lockedQuantities = 0;
    public InventoryEntity(Integer inventoryId, Integer productId, Integer quantity, String location,
            Integer lockedQuantities) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
        this.location = location;
        this.lockedQuantities = lockedQuantities;
    }

    public InventoryEntity() {
    }
    public Integer getLockedQuantities() {
        return lockedQuantities;
    }
    public void setLockedQuantities(Integer lockedQuantities) {
        this.lockedQuantities = lockedQuantities;
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
        return "InventoryEntity [inventoryId=" + inventoryId + ", productId=" + productId + ", quantity=" + quantity
                + ", location=" + location + ", lockedQuantities=" + lockedQuantities + "]";
    }
    
}
