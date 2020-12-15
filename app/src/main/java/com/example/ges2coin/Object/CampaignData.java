package com.example.ges2coin.Object;

public class CampaignData {
    String id;
    String name;
    String description;
    int count;
    int quanlity;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public CampaignData(String id, String name, String description, int count, int quanlity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.count = count;
        this.quanlity = quanlity;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
