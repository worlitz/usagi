package com.spark.service.impl;

import com.google.gson.JsonObject;
import com.spark.dao.MoviesTypeDao;
import com.spark.entity.Movie;
import com.spark.service.IMoviesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: HP
 * @Date: 2020/6/30 19:28
 * @Description:
 **/

@Service
public class MoviesTypeServiceImpl implements IMoviesTypeService {
    @Autowired
    MoviesTypeDao moviesTypeDao;
    @Override
    public JsonObject show_average(String movieId) {
        JsonObject jsonObject = new JsonObject();
        Movie movie = moviesTypeDao.getMovieDetailsById(movieId);
        jsonObject.addProperty("movieId",movie.getMovieId());
        jsonObject.addProperty("movieTitle",movie.getTitle());
        return jsonObject;
    }
}
