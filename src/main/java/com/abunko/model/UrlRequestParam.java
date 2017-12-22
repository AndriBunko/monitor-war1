package com.abunko.model;

public class UrlRequestParam {
    private String url;
    private long responseTime;
    private int ResponseCode;
    private int ResponseLength;
    private String str;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public int getResponseLength() {
        return ResponseLength;
    }

    public void setResponseLength(int responseLength) {
        ResponseLength = responseLength;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "UrlRequestParam{" +
                "url='" + url + '\'' +
                ", responseTime=" + responseTime +
                ", ResponseCode=" + ResponseCode +
                ", ResponseLength=" + ResponseLength +
                ", str='" + str + '\'' +
                '}';
    }
}
