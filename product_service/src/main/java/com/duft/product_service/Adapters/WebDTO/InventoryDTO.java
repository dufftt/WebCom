package com.duft.product_service.Adapters.WebDTO;

public class InventoryDTO {

     private Integer inventoryId;
    private Integer productId;
    private Integer quantity;
    private String location;
    private Integer lockedQuantities = 0;

    

    public InventoryDTO(Integer inventoryId, Integer productId, Integer quantity, String location,
            Integer lockedQuantities) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
        this.location = location;
        this.lockedQuantities = lockedQuantities;
    }

    public InventoryDTO() {
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

    public boolean isValidInventoryDTO(){
        if(this.getInventoryId()!=null && this.getProductId()!=null && this.getQuantity()!=null && this.getLocation()!=null) 
        {
            return true;
        }
       else return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InventoryDTO{");
        sb.append("inventoryId=").append(inventoryId);
        sb.append(", productId=").append(productId);
        sb.append(", quantity=").append(quantity);
        sb.append(", location=").append(location);
        sb.append('}');
        return sb.toString();
    }

    public Integer getLockedQuantities() {
        return lockedQuantities;
    }

    public void setLockedQuantities(Integer lockedQuantities) {
        this.lockedQuantities = lockedQuantities;
    }


}
