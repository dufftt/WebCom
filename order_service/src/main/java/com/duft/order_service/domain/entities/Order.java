package com.duft.order_service.domain.entities;

public class Order {

    private Integer OrderId;
    private Integer customerId;
    private Boolean status = false;
    private Integer total;
    private String created_date;

    
      public Order(Integer orderId, Integer customerId, Boolean status, Integer total, String created_date) {
        OrderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.total = total;
        this.created_date = created_date;
    }


      public Order() {
    }


      public Integer getOrderId() {
        return OrderId;
    }


      public void setOrderId(Integer orderId) {
          OrderId = orderId;
      }


      public Integer getCustomerId() {
          return customerId;
      }


      public void setCustomerId(Integer customerId) {
          this.customerId = customerId;
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
        return "Order [OrderId=" + OrderId + ", customer_id=" + customerId + ", status=" + status + ", total=" + total
                + ", created_date=" + created_date + "]";
    }

    
}
