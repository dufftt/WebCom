package com.duft.shipping_service.Adapters.WebDTO;

public class ShipmentRequestDTO {
    private Integer customerId;
    private Integer addressId;
    private Integer orderId;
    private String carrier;
    
    public ShipmentRequestDTO(Integer customerId, Integer addressId, Integer orderId, String carrier) {
        this.customerId = customerId;
        this.addressId = addressId;
        this.orderId = orderId;
        this.carrier = carrier;
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
     public String getCarrier() {
        return carrier;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    @Override
    public String toString() {
        return "ShipmentRequestDTO [customerId=" + customerId + ", addressId=" + addressId + ", orderId=" + orderId
                + ", carrier=" + carrier + "]";
    }
    public boolean validated() {
        if(this.getAddressId()!=null && this.getCustomerId()!=null && this.getOrderId()!=null){
            return true;
        }
        return false;
    }
   

    

}
