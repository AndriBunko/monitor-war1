package com.abunko.controllers;

import com.abunko.model.UrlConfig;
import com.abunko.service.UrlCunfigAnaliseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@Controller
public class UrlController {

    private static final int ITEMS_PER_PAGE = 6;

    @Autowired
    private UrlCunfigAnaliseService urlCunfigAnaliseService;

    @RequestMapping("/")
    public String index(Model model,  @RequestParam(required = false, defaultValue = "0") Integer page ) {
        if (page < 0) page = 0;

        long totalCount =  urlCunfigAnaliseService.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("urlResults", urlCunfigAnaliseService.listUrlConfigs());
        model.addAttribute("pages", pageCount);

        return "index";
    }

    @RequestMapping("/urlConfig_add_page")
    public String contactAddPage() {
        return "urlConfig_add_page";
    }

    @RequestMapping(value="/urlConfig/add", method = RequestMethod.POST)
    public String urlConfigAdd(@Valid UrlConfig urlConfig, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("string", "Input data error!!");
            return "urlConfig_add_page";
        }
        urlCunfigAnaliseService.addUrlConfig(urlConfig);
        urlCunfigAnaliseService.addResultUrlConfig(urlConfig);

        return "redirect:/";
    }

    @RequestMapping(value = "/url/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0) {
            urlCunfigAnaliseService.delete(toDelete);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/stop/{urlConfig.id}")
    public String onStope(Model model, @PathVariable("urlConfig.id") long id) {
        urlCunfigAnaliseService.stop(id);
        return "redirect:/";
    }

    @RequestMapping("/run/{urlConfig.id}")
    public String onRun(Model model, @PathVariable("urlConfig.id") long id) {
        urlCunfigAnaliseService.run(id);
        return "redirect:/";
    }
}
