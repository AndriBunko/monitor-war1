package com.abunko.controllers;

import com.abunko.model.UrlConfig;
import com.abunko.service.UrlCunfigService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
    private static final int ITEMS_PER_PAGE = 6;

    @Autowired
    private UrlCunfigService urlCunfigService;

    @RequestMapping("/")
    public String index(Model model,  @RequestParam(required = false, defaultValue = "0") Integer page ) {
        if (page < 0) page = 0;

        long totalCount = urlCunfigService.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("urlConfigs", urlCunfigService.listUrlConfigs());
        model.addAttribute("pages", pageCount);

        return "index";
    }


    @RequestMapping("/urlConfig_add_page")
    public String contactAddPage(Model model) {
        return "urlConfig_add_page";
    }

    @RequestMapping(value="/urlConfig/add", method = RequestMethod.POST)
    public String urlConfigAdd(@RequestParam String url,
                             @RequestParam String timeOk,
                             @RequestParam String timeWarning,
                             @RequestParam String timeCritical,
                             @RequestParam String substring,
                             @RequestParam String maxResponseLength,
                             @RequestParam String minResponseLength,
                             @RequestParam String responseCode,
                             @RequestParam String cooldown) {


        UrlConfig urlConfig = new UrlConfig();
        urlConfig.setUrl(url);
        urlConfig.setCooldown(Integer.parseInt(cooldown));
        urlConfig.setExpectedResponseCode(Integer.parseInt(responseCode));
        urlConfig.setMaxResponseLength(Integer.parseInt(maxResponseLength));
        urlConfig.setMinResponseLength(Integer.parseInt(minResponseLength));
        urlConfig.setResponseTimeCRITICAL(Integer.parseInt(timeCritical));
        urlConfig.setResponseTimeOK(Integer.parseInt(timeOk));
        urlConfig.setSubstring(substring);


        urlCunfigService.addUrlConfig(urlConfig);

        return "redirect:/";
    }


//    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
//    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
//        if (toDelete != null && toDelete.length > 0)
//            contactService.deleteContact(toDelete);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @RequestMapping(value="/contact/add", method = RequestMethod.POST)
//    public String contactAdd(@RequestParam(value = "group") long groupId,
//                             @RequestParam String name,
//                             @RequestParam String surname,
//                             @RequestParam String phone,
//                             @RequestParam String email) {
//        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;
//
//        Contact contact = new Contact(group, name, surname, phone, email);
//        contactService.addContact(contact);
//
//        return "redirect:/";
//    }
}
