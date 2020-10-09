package com.example.ges2coin.Object;

public class GetMoneyData {
    String name;
    String money;
    int time;

    public GetMoneyData(String name, String money, int time) {
        this.name = name;
        this.money = money;
        this.time = time;
    }
    public GetMoneyData(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
