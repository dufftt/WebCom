package com.duft.shipping_service.domain.entities;

public class Shipments {

    private Integer shipmentId;
    private Integer orderId;
    private Integer customer_id;
    private Integer address_id;
    private Integer carrier;
    private Boolean status;
    private String trackId;
    private Integer deliveryCost;
    public Shipments(Integer shipmentId, Integer orderId, Integer customer_id, Integer address_id, Integer carrier,
            Boolean status, String trackId, Integer deliveryCost) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.customer_id = customer_id;
        this.address_id = address_id;
        this.carrier = carrier;
        this.status = status;
        this.trackId = trackId;
        this.deliveryCost = deliveryCost;
    }
    public Shipments() {
    }
    public Integer getShipmentId() {
        return shipmentId;
    }
    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
    public Integer getAddress_id() {
        return address_id;
    }
    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }
    public Integer getCarrier() {
        return carrier;
    }
    public void setCarrier(Integer carrier) {
        this.carrier = carrier;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getTrackId() {
        return trackId;
    }
    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
    public Integer getDeliveryCost() {
        return deliveryCost;
    }
    public void setDeliveryCost(Integer deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
    @Override
    public String toString() {
        return "Shipments [shipmentId=" + shipmentId + ", orderId=" + orderId + ", customer_id=" + customer_id
                + ", address_id=" + address_id + ", carrier=" + carrier + ", status=" + status + ", trackId=" + trackId
                + ", deliveryCost=" + deliveryCost + "]";
    }
    



}
