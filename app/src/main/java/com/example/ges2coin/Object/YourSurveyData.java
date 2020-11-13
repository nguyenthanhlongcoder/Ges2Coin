package com.example.ges2coin.Object;

public class YourSurveyData {
    String id, status;
    int process;

    public YourSurveyData(String id, String status, int process) {
        this.id = id;
        this.status = status;
        this.process = process;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }
}
