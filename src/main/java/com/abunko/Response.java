package com.abunko;

import com.abunko.model.UrlConfig;
import com.abunko.model.UrlRequestParam;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Component
public class Response {
    public UrlRequestParam getHttpParam(UrlConfig urlConfig) throws IOException {
        UrlRequestParam requestParam = new UrlRequestParam();
        Date date = new Date();
        long time = System.currentTimeMillis();
        HttpURLConnection con = (HttpURLConnection) new URL(urlConfig.getUrl()).openConnection();
        con.connect();
        requestParam.setResponseTime((System.currentTimeMillis() - time));
        requestParam.setUrl(urlConfig.getUrl());
        requestParam.setResponseCode(con.getResponseCode());
        requestParam.setResponseLength(con.getContentLength());
        con.disconnect();
      //  System.out.println(requestParam);
        return requestParam;
        }

}

