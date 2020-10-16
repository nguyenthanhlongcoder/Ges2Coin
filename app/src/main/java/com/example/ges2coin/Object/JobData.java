package com.example.ges2coin.Object;

public class JobData {
    String name;
    String content;
    int count;
    int timeCountDown;

    public int getCount() {
        return count;
    }

    public int getTimeCountDown() {
        return timeCountDown;
    }

    int coins;

    public void setCount(int count) {
        this.count = count;
    }

    public void setTimeCountDown(int timeCountDown) {
        this.timeCountDown = timeCountDown;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public JobData(String name, String content, int count, int timeCountDown, int coins){
        this.name = name;
        this.content = content;
        this.count = count;
        this.timeCountDown = timeCountDown;
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
