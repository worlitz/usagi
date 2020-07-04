package com.spark.controller;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.catalyst.expressions.WindowFunctionType;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.spark.dao.MoviesTypeDao;
import scala.collection.Seq;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import java.util.Properties;

@Controller
@RestController
public class RatingController {
    @RequestMapping(value = "/movie/rating",method= RequestMethod.GET)
    public String test(@RequestParam(value = "id") String id){
        MoviesTypeDao moviesTypeDao = new MoviesTypeDao();
//        SparkConf conf = new SparkConf().setAppName("test").setMaster("local");
//        SparkContext sc = new SparkContext(conf);
//        SQLContext sqlcontext = new SQLContext(sc);
        SQLContext sqlcontext = null;
        sqlcontext = moviesTypeDao.createspark();
        String url = "jdbc:mysql://roewudu12.cn:3306/usagi?useUnicode=true&characterEncoding=utf-8";
        String table = "ratings";
        Properties connectionProperties = new Properties();
        connectionProperties.put("user","root");
        connectionProperties.put("password","123456");
        connectionProperties.put("driver","com.mysql.jdbc.Driver");
        Dataset<Row> jdbcDF = sqlcontext.read().jdbc(url,table,connectionProperties)
                .select("movieId","rating").filter("movieId="+id);
        Dataset<Row> df = jdbcDF.select("movieId","rating").orderBy("timestamp").limit(10);

        String jsonStr = df.toJSON().collectAsList().toString();
        System.out.println(jsonStr);
        return jsonStr;
    }
    @ResponseBody
    @RequestMapping(value = "/json/data",method = RequestMethod.POST,produces = "application/json:charset=UTF-8")
    public JSONObject Receive(@RequestBody JSONObject jsonParam){
        SparkContext sc = new SparkContext();
        String jsonstr = jsonParam.toString();
        JSONObject result = new JSONObject();
        result.put("msg","ok");
        result.put("method","json");
        result.put("data",jsonParam);
        return result;
    }
}
