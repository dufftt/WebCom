package com.duft.product_service.Adapters.WebDTO;

public class InventoryDTO {

     private Integer inventoryId;
    private Integer productId;
    private Integer quantity;
    private String location;

    public InventoryDTO(Integer inventoryId, String location, Integer productId, Integer quantity) {
        this.inventoryId = inventoryId;
        this.location = location;
        this.productId = productId;
        this.quantity = quantity;
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


}
