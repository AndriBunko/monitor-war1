package com.abunko.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlConfig {
    @Id
    private String url;
    private int cooldown;
    private int responseTimeOK;
    private int responseTimeWARNING;
    private int expectedResponseCode;
    private int minResponseLength;
    private int maxResponseLength;
    private String substring;

    public UrlConfig() {
    }

    public UrlConfig(String url, int cooldown, int responseTimeOK, int responseTimeWARNING, int responseTimeCRITICAL, int expectedResponseCode, int minResponseLength, int maxResponseLength, String substring) {
        this.url = url;
        this.cooldown = cooldown;
        this.responseTimeOK = responseTimeOK;
        this.responseTimeWARNING = responseTimeWARNING;
        this.expectedResponseCode = expectedResponseCode;
        this.minResponseLength = minResponseLength;
        this.maxResponseLength = maxResponseLength;
        this.substring = substring;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
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

    @Override
    public String toString() {
        return "UrlConfig{" +
                "url='" + url + '\'' +
                ", cooldown=" + cooldown +
                ", responseTimeOK=" + responseTimeOK +
                ", responseTimeWARNING=" + responseTimeWARNING +
                ", expectedResponseCode=" + expectedResponseCode +
                ", minResponseLength=" + minResponseLength +
                ", maxResponseLength=" + maxResponseLength +
                ", substring='" + substring + '\'' +
                '}';
    }

/*    public static class UrlConfigBuilder {
        private String url;
        private int cooldown;
        private int responseTimeOK;
        private int responseTimeWARNING;
        private int responseTimeCRITICAL;
        private int expectedResponseCode;
        private int minResponseLength;
        private int maxResponseLength;
        private String substring;


        public  UrlConfigBuilder setURL(String url) {
            this.url = url;
            return this;
        }

        public UrlConfigBuilder setCooldown(int cooldown) {
            this.cooldown = cooldown;
            return this;
        }

        public UrlConfigBuilder setResponseTimeOK(int responseTimeOK) {
            this.responseTimeOK = responseTimeOK;
            return this;
        }

        public UrlConfigBuilder setResponseTimeWARNING(int responseTimeWARNING) {
            this.responseTimeWARNING = responseTimeWARNING;
            return this;
        }

        public UrlConfigBuilder setResponseTimeCRITICAL(int responseTimeCRITICAL) {
            this.responseTimeCRITICAL = responseTimeCRITICAL;
            return this;
        }

        public UrlConfigBuilder setExpectedResponseCode(int expectedResponseCode) {
            this.expectedResponseCode = expectedResponseCode;
            return this;
        }

        public UrlConfigBuilder setMinResponseLength(int minResponseLength) {
            this.minResponseLength = minResponseLength;
            return this;
        }

        public UrlConfigBuilder setMaxResponseLength(int maxResponseLength) {
            this.maxResponseLength = maxResponseLength;
            return this;
        }

        public UrlConfigBuilder setSubstring(String substring) {
            this.substring = substring;
            return this;
        }

        public UrlConfig createUrlConfig() {
            UrlConfig urlConfig = new UrlConfig();
            urlConfig.setUrl(this.url);
            urlConfig.setCooldown(this.cooldown);
            urlConfig.setSubstring(this.substring);
            urlConfig.setExpectedResponseCode(this.expectedResponseCode);
            urlConfig.setMaxResponseLength(this.maxResponseLength);
            urlConfig.setMinResponseLength(this.minResponseLength);
            urlConfig.setResponseTimeOK(this.responseTimeOK);
            urlConfig.setResponseTimeWARNING(this.responseTimeWARNING);
            urlConfig.setResponseTimeCRITICAL(this.responseTimeCRITICAL);
            return urlConfig;
        }
    }*/
}
