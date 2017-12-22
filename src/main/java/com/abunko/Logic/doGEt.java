package com.abunko.Logic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class doGEt {
    private String url;

    public doGEt(String url) {
        this.url = url;
    }

    public void DoGet() throws Exception {
        Date date = new Date();
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        System.out.println(con.getDate() - date.getTime());
        System.out.println(con.getContentLength());
        System.out.println(con.getContentType());

        Map<String, List<String>> map = con.getHeaderFields();
        Set<String> set = map.keySet();
        for (String k : set) {
            System.out.println(k + "  " + map.get(k));
        }

        try {
            con.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
