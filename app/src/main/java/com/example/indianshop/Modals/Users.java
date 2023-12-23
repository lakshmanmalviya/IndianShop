package com.example.indianshop.Modals;

public class Users {
    String userName, email,possword,userId;

    public Users() {
    }

    public Users(String userName, String email, String possword) {
        this.userName = userName;
        this.email = email;
        this.possword = possword;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPossword() {
        return possword;
    }

    public void setPossword(String possword) {
        this.possword = possword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
