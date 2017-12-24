package com.abunko.controllers;

import com.abunko.dao.ResultOfAnalysisUrlDao;
import com.abunko.model.UrlConfig;
import com.abunko.service.UrlCunfigAnaliseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
public class UrlController {

    private static final int ITEMS_PER_PAGE = 6;
    @Autowired
    private ResultOfAnalysisUrlDao analysisUrlDao;
    @Autowired
    private UrlCunfigAnaliseService urlCunfigAnaliseService;

    @RequestMapping("/")
    public String index(Model model,  @RequestParam(required = false, defaultValue = "0") Integer page ) {
        if (page < 0) page = 0;

        long totalCount =  urlCunfigAnaliseService.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("urlResults", urlCunfigAnaliseService.listResultOfAnalysis());
        model.addAttribute("pages", pageCount);

        return "index";
    }


    @RequestMapping("/urlConfig_add_page")
    public String contactAddPage() {
        return "urlConfig_add_page";
    }

    @RequestMapping(value="/urlConfig/add", method = RequestMethod.POST)
    public String urlConfigAdd(@RequestParam(required = true) String url,
                             @RequestParam(required = true) String timeOk,
                             @RequestParam(required = true) String timeWarning,
                             @RequestParam(required = true) String substring,
                             @RequestParam(required = true) String maxResponseLength,
                             @RequestParam(required = true) String minResponseLength,
                             @RequestParam(required = true) String responseCode,
                             @RequestParam(required = true) String repeatFrequency) throws ExecutionException, InterruptedException {


        UrlConfig urlConfig = new UrlConfig();
        urlConfig.setUrl(url);
        urlConfig.setRepeatFrequency(Integer.parseInt(repeatFrequency));
        urlConfig.setExpectedResponseCode(Integer.parseInt(responseCode));
        urlConfig.setMaxResponseLength(Integer.parseInt(maxResponseLength));
        urlConfig.setMinResponseLength(Integer.parseInt(minResponseLength));
        urlConfig.setResponseTimeWARNING(Integer.parseInt(timeWarning));
        urlConfig.setResponseTimeOK(Integer.parseInt(timeOk));
        urlConfig.setSubstring(substring);

        urlCunfigAnaliseService.addUrlConfig(urlConfig);
        urlCunfigAnaliseService.addResultUrlConfig(urlConfig);
        Thread.sleep(1000);

        return "redirect:/";
    }

    @RequestMapping(value = "/url/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) String[] toDelete) {
        System.out.println("Dsfsdfafkdbfsadnk");
        if (toDelete != null && toDelete.length > 0) {
            urlCunfigAnaliseService.delete(toDelete);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/stop/{urlConfig.url}")
    public String onStope(Model model, @PathVariable("urlConfig.url") String url) {
        urlCunfigAnaliseService.stop(url);
        return "redirect:/";
    }

    @RequestMapping("/run/{urlConfig.url}")
    public String onRun(Model model, @PathVariable("urlConfig.url") String url) {
        urlCunfigAnaliseService.run(url);
        return "redirect:/";
    }
}
