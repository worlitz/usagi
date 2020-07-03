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
@CrossOrigin("http://localhost:8080")
public class MovieTypeController {
    @Autowired
    IMoviesTypeService moviesTypeService;

    /**
     * 获取所有类别的平均得分
     * @return String json数据
     */
    @RequestMapping(value = "/ave_score/genres",method = RequestMethod.GET)
    public String ave_score_genres(){

        JsonObject jsonObject = moviesTypeService.showAverageScore();

        return jsonObject.toString();
    }

    /**
     * 获取所有类别的平均得分
     * @return String json数据
     */
    @RequestMapping(value = "/ave_score/age",method = RequestMethod.GET)
    public String ave_score_age(){


        JsonObject jsonObject = moviesTypeService.showAverageScoreForAge();

        return jsonObject.toString();
    }

    /**
     * 获取所有类别的平均得分
     * @return String json数据
     */
    @RequestMapping(value = "/ave_score/gender",method = RequestMethod.GET)
    public String ave_score_gender(){


        JsonObject jsonObject = moviesTypeService.showAverageScoreForGender();

        return jsonObject.toString();
    }
}
