package com.abunko.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ResultOfAnalysisUrl {
    @Id
    private String url;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    public ResultOfAnalysisUrl() {
    }

    public ResultOfAnalysisUrl(String url, String description, Status status) {
        this.url = url;
        this.description = description;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultOfAnalysisUrl)) return false;
        ResultOfAnalysisUrl result = (ResultOfAnalysisUrl) o;
        return Objects.equals(getUrl(), result.getUrl()) &&
                Objects.equals(getDescription(), result.getDescription()) &&
                getStatus() == result.getStatus();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUrl(), getDescription(), getStatus());
    }
}
