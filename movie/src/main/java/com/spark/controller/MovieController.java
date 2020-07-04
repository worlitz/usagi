package com.spark.controller;

import javafx.application.Application;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.Function1;
import org.apache.spark.api.java.function.Function;
import java.util.Properties;

@RestController
@Controller
public class MovieController {
    @ResponseBody
    @RequestMapping(value = "/movie",method=RequestMethod.GET)
    public String sayHello(@RequestParam(value = "movieid")String movieid){
        return "hello springboot" + movieid;
    }
}
