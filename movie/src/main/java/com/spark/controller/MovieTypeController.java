package com.spark.controller;

import com.google.gson.JsonObject;
import com.spark.service.IMoviesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: HP
 * @Date: 2020/6/30 17:42
 * @Description:
 **/

@RestController
public class MovieTypeController {
    @Autowired
    IMoviesTypeService moviesTypeService;

    /**
     * 获取某个电影类别的评分的平均值和类型打分的平均值，返回json
     * @return String json数据
     */
    @GetMapping("/show/average")
    public String show_average(@RequestParam("movieId")String movieId){

        //根据电影id返回需要的数据
        JsonObject jsonObject = moviesTypeService.show_average(movieId);


        return jsonObject.toString();
    }
}
