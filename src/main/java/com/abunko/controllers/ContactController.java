package com.abunko.controllers;

import com.abunko.dao.ResultOfAnalysisUrlDao;
import com.abunko.dao.ResultOfAnalysisUrlDaoImpl;
import com.abunko.dao.UrlConfigDao;
import com.abunko.dao.UrlConfigDaoImpl;
import com.abunko.model.ResultOfAnalysisUrl;
import com.abunko.model.Status;
import com.abunko.model.UrlConfig;
import com.abunko.service.UrlCunfigService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
public class ContactController {
    private static final int ITEMS_PER_PAGE = 6;

    @Autowired
    private UrlCunfigService urlCunfigService;
    @Autowired
    private ResultOfAnalysisUrlDao resultOfAnalysisUrlDao;

    @RequestMapping("/")
    public String index(Model model,  @RequestParam(required = false, defaultValue = "0") Integer page ) {
        if (page < 0) page = 0;

        long totalCount =  resultOfAnalysisUrlDao.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("urlResults", resultOfAnalysisUrlDao.list());
        model.addAttribute("pages", pageCount);

        return "index";
    }


    @RequestMapping("/urlConfig_add_page")
    public String contactAddPage(Model model) {
        return "urlConfig_add_page";
    }

    @RequestMapping(value="/urlConfig/add", method = RequestMethod.POST)
    public String urlConfigAdd(@RequestParam(required = true) String url,
                             @RequestParam(required = true) String timeOk,
                             @RequestParam(required = true) String timeWarning,
                             @RequestParam(required = true) String timeCritical,
                             @RequestParam(required = true) String substring,
                             @RequestParam(required = true) String maxResponseLength,
                             @RequestParam(required = true) String minResponseLength,
                             @RequestParam(required = true) String responseCode,
                             @RequestParam(required = true) String cooldown) throws ExecutionException, InterruptedException {


        UrlConfig urlConfig = new UrlConfig();
        urlConfig.setUrl(url);
        urlConfig.setCooldown(Integer.parseInt(cooldown));
        urlConfig.setExpectedResponseCode(Integer.parseInt(responseCode));
        urlConfig.setMaxResponseLength(Integer.parseInt(maxResponseLength));
        urlConfig.setMinResponseLength(Integer.parseInt(minResponseLength));
        urlConfig.setResponseTimeWARNING(Integer.parseInt(timeCritical));
        urlConfig.setResponseTimeOK(Integer.parseInt(timeOk));
        urlConfig.setSubstring(substring);

//          ResultOfAnalysisUrl result = new ResultOfAnalysisUrl(url, "all OK", "OK");
//          resultOfAnalysisUrlDao.add(result);
       // UrlConfigDao urlConfigDao = new UrlConfigDaoImpl();
       // urlConfigDao.add(urlConfig);

         urlCunfigService.addUrlConfig(urlConfig);

        return "index";
    }

}
