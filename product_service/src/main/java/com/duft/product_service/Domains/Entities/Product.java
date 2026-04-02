package com.duft.product_service.Domains.Entities;

public class Product {
    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer price;
    private String productImageUrl;
    
    public Product(Integer productId, String productName, String productDescription, Integer price,
            String productImageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.productImageUrl = productImageUrl;
    }

    public Product() {
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

      public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
                + productDescription + ", price=" + price + "]";
    }

  

    
}
