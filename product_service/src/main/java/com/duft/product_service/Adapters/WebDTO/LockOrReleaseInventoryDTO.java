package com.duft.product_service.Adapters.WebDTO;

public class LockOrReleaseInventoryDTO {

    public LockOrReleaseInventoryDTO() {
    }

    private Integer inventoryId;
    private Integer quantity;
    public LockOrReleaseInventoryDTO(Integer inventoryId, Integer quantity) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }
    public Integer getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "LockOrReleaseInventoryDTO [inventoryId=" + inventoryId + ", quantity=" + quantity + "]";
    }
    
    
   
    

}
