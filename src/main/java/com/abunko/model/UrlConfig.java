package com.abunko.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UrlConfig {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @URL
    private String url;
    @NotNull
    @Size(min = 10)
    private int repeatFrequency;
    @NotNull
    private int responseTimeOK;
    @NotNull
    private int responseTimeWARNING;
    @NotNull
    private int expectedResponseCode;
    @NotNull
    private int minResponseLength;
    @NotNull
    private int maxResponseLength;
    @NotNull
    private String substring;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;

    public UrlConfig() {
    }

    public UrlConfig(String url, int repeatFrequency, int responseTimeOK, int responseTimeWARNING, int responseTimeCRITICAL, int expectedResponseCode, int minResponseLength, int maxResponseLength, String substring) {
        this.url = url;
        this.repeatFrequency = repeatFrequency;
        this.responseTimeOK = responseTimeOK;
        this.responseTimeWARNING = responseTimeWARNING;
        this.expectedResponseCode = expectedResponseCode;
        this.minResponseLength = minResponseLength;
        this.maxResponseLength = maxResponseLength;
        this.substring = substring;
        this.status = Status.OK;
        this.description = "All ok";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRepeatFrequency() {
        return repeatFrequency;
    }

    public void setRepeatFrequency(int repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    public int getResponseTimeOK() {
        return responseTimeOK;
    }

    public void setResponseTimeOK(int responseTimeOK) {
        this.responseTimeOK = responseTimeOK;
    }

    public int getResponseTimeWARNING() {
        return responseTimeWARNING;
    }

    public void setResponseTimeWARNING(int responseTimeWARNING) {
        this.responseTimeWARNING = responseTimeWARNING;
    }

    public int getExpectedResponseCode() {
        return expectedResponseCode;
    }

    public void setExpectedResponseCode(int expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
    }

    public int getMinResponseLength() {
        return minResponseLength;
    }

    public void setMinResponseLength(int minResponseLength) {
        this.minResponseLength = minResponseLength;
    }

    public int getMaxResponseLength() {
        return maxResponseLength;
    }

    public void setMaxResponseLength(int maxResponseLength) {
        this.maxResponseLength = maxResponseLength;
    }

    public String getSubstring() {
        return substring;
    }

    public void setSubstring(String substring) {
        this.substring = substring;
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
        return "UrlConfig{" +
                "url='" + url + '\'' +
                ", repeatFrequency=" + repeatFrequency +
                ", responseTimeOK=" + responseTimeOK +
                ", responseTimeWARNING=" + responseTimeWARNING +
                ", expectedResponseCode=" + expectedResponseCode +
                ", minResponseLength=" + minResponseLength +
                ", maxResponseLength=" + maxResponseLength +
                ", substring='" + substring + '\'' +
                '}';
    }

}