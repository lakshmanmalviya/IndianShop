package com.example.indianshop.Modals;

public class CategoryModal {
    String categoryId,categoryImage,categoryName;

    public String getCategoryId() {
        return categoryId;
    }

    public CategoryModal() {
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryModal(String categoryId, String categoryImage, String categoryName) {
        this.categoryId = categoryId;
        this.categoryImage = categoryImage;
        this.categoryName = categoryName;
    }
}
