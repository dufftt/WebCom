package com.duft.product_service.Adapters.WebDTO;

public class ProductDTO {

    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer price;
    private String productImageUrl;

    public ProductDTO(Integer productId, String productName, String productDescription, Integer price,
            String productImageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.productImageUrl = productImageUrl;
    }

    public ProductDTO() {
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

    
    public boolean isValidProductDTO(){
       if(this.getProductId()!=null && this.getProductName()!=null && this.getProductDescription()!=null && this.getPrice()!=null) 
        {
            return true;
        }
       else return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductDTO{");
        sb.append("productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productDescription=").append(productDescription);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

  


}
