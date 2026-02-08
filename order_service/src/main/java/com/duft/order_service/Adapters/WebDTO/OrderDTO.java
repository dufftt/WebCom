package com.duft.order_service.Adapters.WebDTO;

public class OrderDTO {
private Integer OrderId;
    private Integer customer_id;
    private Boolean status = false;
    private Integer total;
    private String created_date;
    public OrderDTO(Integer orderId, Integer customer_id, Boolean status, Integer total, String created_date) {
        OrderId = orderId;
        this.customer_id = customer_id;
        this.status = status;
        this.total = total;
        this.created_date = created_date;
    }
    public OrderDTO() {
    }
    public Integer getOrderId() {
        return OrderId;
    }
    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public String getCreated_date() {
        return created_date;
    }
    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
    @Override
    public String toString() {
        return "OrderDTO [OrderId=" + OrderId + ", customer_id=" + customer_id + ", status=" + status + ", total="
                + total + ", created_date=" + created_date + "]";
    }

    
}
