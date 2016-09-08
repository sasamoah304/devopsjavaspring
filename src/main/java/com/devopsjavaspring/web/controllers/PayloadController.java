package com.devopsjavaspring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by stephenasamoah on 9/8/16.
 */
@Controller
public class PayloadController {

    public static final String PAYLOAD_VIEW_NMAE = "payload/payload";

    @RequestMapping("/payload")
    public String payload(){
        return PAYLOAD_VIEW_NMAE;
    }
}