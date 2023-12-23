package com.example.indianshop.Modals;

public class ProductModal {
    String productName,productPrice,productColor,productCategory,productId,productBrand,
    productQnt,productDescription,productDiscount,productImage,longDescription;
    String cart, wish;

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public ProductModal(String productName, String productPrice, String productColor, String productCategory, String productId, String productBrand,
                        String productQnt, String productDescription, String productDiscount, String productImage, String longDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productColor = productColor;
        this.productCategory = productCategory;
        this.productId = productId;
        this.productBrand = productBrand;
        this.productQnt = productQnt;
        this.productDescription = productDescription;
        this.productDiscount = productDiscount;
        this.productImage = productImage;
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    public ProductModal() {
    }

    public ProductModal(String productName, String productPrice,
                        String productColor, String productBrand,
                        String productDescription, String productDiscount,String productImage) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productColor = productColor;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productDiscount = productDiscount;
    }

    public ProductModal(String productName, String productPrice,
                        String productColor, String productCategory, String productId, String
                                productBrand, String productQnt, String productDescription,
                        String productDiscount,String productImage) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productColor = productColor;
        this.productCategory = productCategory;
        this.productId = productId;
        this.productBrand = productBrand;
        this.productQnt = productQnt;
        this.productDescription = productDescription;
        this.productDiscount = productDiscount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductQnt() {
        return productQnt;
    }

    public void setProductQnt(String productQnt) {
        this.productQnt = productQnt;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

}
