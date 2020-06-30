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
     * 根据movieId查询到genre，再根据movieId查询到的tags。
     * 再通过模糊查询查询到有genre相同的
     * @param movieId
     * @return jsonObject
     */
    JsonObject show_average(String movieId);
}
