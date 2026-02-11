package com.duft.shipping_service.Adapters.WebDTO;

public class ShipmentRequestDTO {
    private Integer customerId;
    private Integer addressId;
    private Integer orderId;
    public ShipmentRequestDTO(Integer customerId, Integer addressId, Integer orderId) {
        this.customerId = customerId;
        this.addressId = addressId;
        this.orderId = orderId;
    }
    public ShipmentRequestDTO() {
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getAddressId() {
        return addressId;
    }
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    @Override
    public String toString() {
        return "ShipmentRequestDTO [customerId=" + customerId + ", addressId=" + addressId + ", orderId=" + orderId
                + "]";
    }

    

}
