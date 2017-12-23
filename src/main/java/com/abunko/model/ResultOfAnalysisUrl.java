package com.abunko.model;

import javax.persistence.*;

@Entity
public class ResultOfAnalysisUrl {
    @Id
    private String url;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;


    public ResultOfAnalysisUrl() {
    }

    public ResultOfAnalysisUrl(String url, String description, String status) {
        this.url = url;
        this.description = description;
        this.status = Status.valueOf(status);
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

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResultOfAnalysisUrl{" +
                "url='" + url + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
