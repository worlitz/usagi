package com.spark1.service;

import com.spark1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkService {
    private FindAfter findAfter;
    private FindBefore findBefore;
    private  FindFuzzy findFuzzy;

    @Autowired
    public void setFindAfter(FindAfter findAfter){
        this.findAfter = findAfter;
    }
    @Autowired
    public void setFindBefore(FindBefore findBefore){
        this.findBefore = findBefore;
    }
    @Autowired
    public void setFindFuzzy(FindFuzzy findFuzzy){
        this.findFuzzy = findFuzzy;
    }

    public String query(String s, Integer type) {
        switch (type) {
            case 1:
                System.out.println();
                return FindFuzzy.getMovieInfo(s);
            case 2:
                System.out.println();
                return findBefore.getMovieInfo(s);
            case 3:
                return findAfter.getMovieInfo(s);
        }
        return "" ;
    }
}
