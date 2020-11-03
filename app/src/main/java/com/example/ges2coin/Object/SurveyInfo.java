package com.example.ges2coin.Object;

import java.util.ArrayList;

public class SurveyInfo {
    String linkSurvey, campaignName, age, gender;
    Integer quality;
    ArrayList<String> category;

    public  SurveyInfo(){

    }
    public SurveyInfo(String linkSurvey, String campaignName, String age, String gender, Integer quality, ArrayList<String> category) {
        this.linkSurvey = linkSurvey;
        this.campaignName = campaignName;
        this.age = age;
        this.gender = gender;
        this.quality = quality;
        this.category = category;
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
