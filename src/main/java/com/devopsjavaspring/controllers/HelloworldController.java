package com.devopsjavaspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by stephenasamoah on 9/7/16.
 */
@Controller
public class HelloworldController {

    @RequestMapping("/")
    public String SayHello(){
        return "index";
    }
}
