package com.abunko.model;

import javax.persistence.Entity;

public class ResultOfAnalysisUrl {
    private String url;
    private Status status;
    private String Description;

    public ResultOfAnalysisUrl() {
    }

    public ResultOfAnalysisUrl(String url, Status status, String description) {
        this.url = url;
        this.status = status;
        Description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
