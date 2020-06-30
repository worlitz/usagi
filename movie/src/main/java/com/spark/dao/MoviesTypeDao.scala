package com.spark.dao

import java.util.Properties

import com.spark.entity.Movie
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * @Auther: HP
 * @Date: 2020/6/30 19:28
 * @Description:类，用scala-sql对mysql数据进行增删改查，获取相应的信息
 *              继承CommandLineRunner，提前运行Dao层的spark，防止查询速度过慢
 **/


@Component
class MoviesTypeDao extends CommandLineRunner{
    val spark = SparkSession
      .builder().master("local[2]")
      .appName("Spark SQL basic example")
      .getOrCreate()
    spark.sqlContext.sparkContext.setLogLevel("WARN")

    val url="jdbc:mysql://roewudu12.cn:3306/usagi"
    val connectionPro = new Properties()
    connectionPro.put("user","root")
    connectionPro.put("password","123456")
    connectionPro.put("driver","com.mysql.cj.jdbc.Driver")
    val moviesDF:DataFrame = spark.read.jdbc("jdbc:mysql://roewudu12.cn:3306/usagi","movies",connectionPro)
    moviesDF.createOrReplaceTempView("movies")

  /**
   * 获取电影的信息，通过id
   * @param movieId
   * @return
   */
  def getMovieDetailsById(movieId:String):Movie={
    val movie = new Movie()

    val moviesRDD = spark.sql("select * from movies where movieId="+movieId)
    println(moviesRDD.collect().mkString(","))
    movie
  }

  override def run(args: String*): Unit = {
    println("正在启动本地spark")
  }
}
