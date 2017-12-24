package com.abunko;

import com.abunko.model.UrlConfig;
import com.abunko.model.UrlRequestParam;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Component
public class Response {
    public UrlRequestParam getHttpParam(UrlConfig urlConfig) throws IOException {
        UrlRequestParam requestParam = new UrlRequestParam();
        String str;
        StringBuilder sb = new StringBuilder();
        Date date = new Date();
        long time = System.currentTimeMillis();
        HttpURLConnection con = (HttpURLConnection) new URL(urlConfig.getUrl()).openConnection();
        //con.connect();
        requestParam.setUrl(urlConfig.getUrl());
        requestParam.setResponseCode(con.getResponseCode());
        requestParam.setResponseLength(con.getContentLength());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while ((str = in.readLine()) != null) {
            sb.append(str);
        }
        in.close();
        requestParam.setStr(sb.toString());
        requestParam.setResponseTime((System.currentTimeMillis() - time));
        //con.disconnect();
        //System.out.println(requestParam);
        return requestParam;
        }

}

