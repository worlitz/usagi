package com.spark.service.impl;

import com.google.gson.JsonObject;
import com.spark.dao.MoviesTypeDao;
import com.spark.service.IMoviesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public JsonObject showAverageScore() {
        System.out.println("进入service");
        JsonObject jsonObject = new JsonObject();
        String[] allGenre = moviesTypeDao.getAllGenre();
        HashMap<String, String> scoreByGenres = moviesTypeDao.getScoreByGenres(allGenre);
        for (Map.Entry<String, String> entry : scoreByGenres.entrySet()) { ;
            jsonObject.addProperty(entry.getKey(),entry.getValue());
        }

        return jsonObject;
    }

    @Override
    public JsonObject showAverageScoreForAge() {
        JsonObject jsonObject = new JsonObject();
        String[] allGenre = moviesTypeDao.getAllGenre();
        //1-17岁
        HashMap<String, String> scoreByAge = moviesTypeDao.getScoreByAge(allGenre, "0", "17");
        JsonObject zeroToSeventeen = new JsonObject();
        for (Map.Entry<String, String> entry : scoreByAge.entrySet()) {
            zeroToSeventeen.addProperty(entry.getKey(),entry.getValue());
        }
        jsonObject.add("zeroToSeventeen",zeroToSeventeen);

        //18-45岁
        scoreByAge = moviesTypeDao.getScoreByAge(allGenre,"17","45");
        JsonObject eighteenToFourtyFive = new JsonObject();
        for (Map.Entry<String, String> entry : scoreByAge.entrySet()) {
            eighteenToFourtyFive.addProperty(entry.getKey(),entry.getValue());
        }
        jsonObject.add("eighteenToFourtyFive",zeroToSeventeen);

        //大于46岁
        scoreByAge = moviesTypeDao.getScoreByAge(allGenre,"45","100");
        JsonObject overFourtySix = new JsonObject();
        for (Map.Entry<String, String> entry : scoreByAge.entrySet()) {
            overFourtySix.addProperty(entry.getKey(),entry.getValue());
        }
        jsonObject.add("overFourtySix",zeroToSeventeen);

        return jsonObject;
    }

    @Override
    public JsonObject showAverageScoreForGender() {
        JsonObject jsonObject = new JsonObject();
        String[] allGenre = moviesTypeDao.getAllGenre();

        //女生
        JsonObject Female = new JsonObject();
        HashMap<String, String> scoreByAge = moviesTypeDao.getManOrFemaleScoreByGenre(allGenre, "F");
        for (Map.Entry<String, String> entry : scoreByAge.entrySet()) {
            Female.addProperty(entry.getKey(),entry.getValue());
        }
        jsonObject.add("Female",Female);

        //男生
        JsonObject Man = new JsonObject();
        scoreByAge = moviesTypeDao.getManOrFemaleScoreByGenre(allGenre, "M");
        for (Map.Entry<String, String> entry : scoreByAge.entrySet()) {
            Man.addProperty(entry.getKey(),entry.getValue());
        }
        jsonObject.add("Man",Man);

        return jsonObject;
    }
}
