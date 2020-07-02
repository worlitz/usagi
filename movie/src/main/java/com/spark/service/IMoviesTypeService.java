package com.spark.service;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

/**
 * 接口类
 * Roe
 * 2020/6/30
 * 电影类别有关操作的接口类（Service层）
 */

public interface IMoviesTypeService {

    /**
     * 查询每个类别的平均分
     * @return 一个json对象
     */
    JsonObject showAverageScore();

    /**
     *  通过年龄段查询每个类别的平均分
     * @return 一个json对象
     */
    JsonObject showAverageScoreForAge();

    /**
     *  通过性别查询每个类别的平均分
     * @return 一个json对象
     */
    JsonObject showAverageScoreForGender();


}
