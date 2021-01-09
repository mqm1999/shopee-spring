package com.example.codese_spring.dto;

import com.example.codese_spring.entity.Product;

public class ProductGetAll {
    private String productID;
    private String display;
    private int priceSale;
    private String images;

    public ProductGetAll() {
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ProductGetAll{" +
                "productID='" + productID + '\'' +
                ", display='" + display + '\'' +
                ", priceSale=" + priceSale +
                ", images='" + images + '\'' +
                '}';
    }
}
