package com.spark.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: HP
 * @Date: 2020/6/30 19:54
 * @Description:
 **/

@Data
public class Movie {
    private String movieId;     //电影id
    private String title;       //电影名称
    private List<String> genres;//电影类型
    private List<String> tag;   //电影标签（可能是类别）
    private String ave_score;       //电影得分平均分

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getAve_score() {
        return ave_score;
    }

    public void setAve_score(String ave_score) {
        this.ave_score = ave_score;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", tag=" + tag +
                ", ave_score='" + ave_score + '\'' +
                '}';
    }
}
