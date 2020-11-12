package com.example.ges2coin.Object;

import java.util.ArrayList;

public class SurveyInfo {
    String linkSurvey;
    String campaignName;
    String age;
    String gender;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    String id;
    Integer quality;
    ArrayList<String> category;

    public  SurveyInfo(){

    }
    public SurveyInfo(String id,String linkSurvey, String campaignName, String description, String age, String gender, Integer quality, ArrayList<String> category, String status) {
        this.id = id;
        this.linkSurvey = linkSurvey;
        this.campaignName = campaignName;
        this.age = age;
        this.gender = gender;
        this.quality = quality;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    public String getLinkSurvey() {
        return linkSurvey;
    }

    public void setLinkSurvey(String linkSurvey) {
        this.linkSurvey = linkSurvey;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
