package com.example.ges2coin.Object;

public class JobData {
    String name;
    String content;
    int quality;
    public void JobData(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    public int getQuality() {
        return quality;
    }


    int coins;

    public void setQuality(int count) {
        this.quality = count;
    }


    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public JobData(String id,String name, String content, int quality, int coins){
        this.name = name;
        this.id = id;
        this.content = content;
        this.quality = quality;
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
