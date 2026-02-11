package com.duft.shipping_service.Adapters.WebDTO;

public class UpdateStatusDTO {

    private Integer shipmentId;
    private Boolean status;


    public UpdateStatusDTO(Integer shipmentId, Boolean status) {
        this.shipmentId = shipmentId;
        this.status = status;
    }
    public UpdateStatusDTO() {
    }

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    
    public Boolean getStatus() {
        return status;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }


    
    @Override
    public String toString() {
        return "UpdateStatusDTO [shipmentId=" + shipmentId + ", status=" + status + "]";
    }

}
