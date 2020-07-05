package com.spark1.controller;

import com.spark1.mapper.Temp;
import com.spark1.service.*;
import com.spark1.generate.*;
import com.spark1.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class moviesController {
    private SparkService sparkService;

    @Autowired
    public void setSparkService(SparkService sparkService) {
        this.sparkService = sparkService;
    }
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("content","prematch");
        return "index";
    }
    @RequestMapping(value = "/spark_fuzzy",method = RequestMethod.GET)
    @ResponseBody
    public Object SparkSearch1(String name_fuzzy){
        System.out.println(name_fuzzy);
        String name1 = sparkService.query(name_fuzzy, 1);
        return name1;
    }
    @RequestMapping(value = "/spark_before",method = RequestMethod.GET)
    @ResponseBody
    public Object SparkSearch2(String name_before){
        System.out.println(name_before);
        String name2 = sparkService.query(name_before, 2);
        return name2;
    }
    @RequestMapping(value = "/spark_after",method = RequestMethod.GET)
    @ResponseBody
    public Object SparkSearch3(String name_after){
        System.out.println(name_after);
        String name3 = sparkService.query(name_after, 3);
        return name3;
    }
}
