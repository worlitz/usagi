package com.spark.controller;

import com.google.gson.JsonObject;
import com.spark.service.IMoviesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 获取所有类别的平均得分
     * @return String json数据
     */
    @RequestMapping("/ave_score/genres")
    public String ave_score_genres(){

        JsonObject jsonObject = moviesTypeService.showAverageScore();

        return jsonObject.toString();
    }

    /**
     * 获取所有类别的平均得分
     * @return String json数据
     */
    @RequestMapping("/ave_score/age")
    public String ave_score_age(){

        //根据电影id返回需要的数据
        JsonObject jsonObject = moviesTypeService.showAverageScoreForAge();

        return jsonObject.toString();
    }

    /**
     * 获取所有类别的平均得分
     * @return String json数据
     */
    @RequestMapping("/ave_score/gender")
    public String ave_score_gender(){

        //根据电影id返回需要的数据
        JsonObject jsonObject = moviesTypeService.showAverageScoreForGender();

        return jsonObject.toString();
    }
}
