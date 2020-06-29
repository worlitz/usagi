package com.spark.controller;




import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MovieController {
    @ResponseBody
    @RequestMapping("/haha")
    public String hello(){
        return "haha";
    }


}
