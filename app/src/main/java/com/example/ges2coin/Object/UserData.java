package com.example.ges2coin.Object;

import java.util.ArrayList;

public class UserData {
    String id, displayName, gender, email, status;
    int age, coin;
    ArrayList<String> categories;


    public UserData(){

    }

    public UserData(String id, String displayName, String gender, String email, int age, int coin, ArrayList<String> categories, String status) {
        this.id = id;
        this.displayName = displayName;
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.coin = coin;
        this.categories = categories;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }



}
