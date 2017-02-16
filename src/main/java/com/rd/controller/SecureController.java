package com.rd.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {
	
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasOption('delete')")
    public String sayHello() {
        return "Secure Hello!";
    }

}
