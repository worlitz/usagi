package com.spark.dao

import java.util
import java.util.Properties

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
  /**
   * 创建sparksession与配置链接mysql
   */
    val spark = SparkSession
      .builder().master("local[*]")
      .appName("Spark SQL basic example")
      .getOrCreate()
    spark.sqlContext.sparkContext.setLogLevel("WARN")
    val url="jdbc:mysql://roewudu12.cn:3306/usagi"
    val connectionPro = new Properties()
    connectionPro.put("user","root")
    connectionPro.put("password","123456")
    connectionPro.put("driver","com.mysql.cj.jdbc.Driver")
  /**
   * 初始化三个表
   */
    val moviesDF:DataFrame = spark.read.jdbc(url,"movies",connectionPro)
    moviesDF.createOrReplaceTempView("movies")
    val tagsDF:DataFrame = spark.read.jdbc(url,"ratings",connectionPro)
    tagsDF.createOrReplaceTempView("ratings")
    val usersDF:DataFrame = spark.read.jdbc(url,"users",connectionPro)
    usersDF.createOrReplaceTempView("users")


  /**
   * 查询所有类别
   * @return 返回类别的Array[String]
   */
  def getAllGenre():Array[String]={
    println("进入到AllGenre方法")
    val genresRDD= spark.sql("select genres from movies").rdd
    val words = genresRDD.flatMap(line =>line(0).toString.split("\\|")).distinct().collect()

    words
  }


  /**
   * 查询男生（女生）对同类型电影的打分情况
   * @param genres 类别List
   * @return 返回一个Map[类别,得分]
   */
  def getManOrFemaleScoreByGenre(genres:Array[String],gender:String):util.HashMap[String,String]={
    var ave_scores = new util.HashMap[String,String]()

    genres.foreach(genre =>{

      val scoreRDD = spark.sql("select avg(rating) from movies,users,ratings where movies.genres like'%"+genre+
        "%' and movies.movieId=ratings.movieId and ratings.userId=users.userId and users.gender='"+gender+"'").rdd
      if (scoreRDD.take(1).mkString!="[null]") {
        val words = scoreRDD.map(line=>line(0).toString).collect().head.toDouble

        val ave_score = words.formatted("%.2f")
        ave_scores.put(genre,ave_score)
      }

    })
    ave_scores

  }

  /**
   * 通过类别名称查询到类别对应的得分
   * @param genres 类别List
   * @return 返回一个Map[类别,得分]
   */
  def getScoreByGenres(genres:Array[String]):util.HashMap[String,String]={
    var ave_scores = new util.HashMap[String,String]()

    genres.foreach(genre =>{
      val scoreRDD = spark.sql("select avg(rating) from movies,users,ratings where movies.genres like'%"+genre+
        "%' and movies.movieId=ratings.movieId and ratings.userId=users.userId").rdd
      if (scoreRDD.take(1).mkString!="[null]") {
        val words = scoreRDD.map(line=>line(0).toString).collect().head.toDouble

        val ave_score = words.formatted("%.2f")
        ave_scores.put(genre,ave_score)
      }

    })

    ave_scores
  }

  /**
   * 查询不同年龄段，所有类别的评分平均分
   * @param genres
   * @param from 从几岁
   * @param to   到几岁
   * @return 返回一个Map[类别,得分]
   */
  def getScoreByAge(genres:Array[String],from:String,to:String):util.HashMap[String,String]={
    var ave_scores = new util.HashMap[String,String]()

    genres.foreach(genre =>{
      val scoreRDD = spark.sql("select avg(rating) from movies,users,ratings where movies.genres like'%"+genre+
        "%' and movies.movieId=ratings.movieId and ratings.userId=users.userId and users.age>'"+from+"' and users.age<='"+to+"'").rdd

      if (scoreRDD.take(1).mkString!="[null]") {
        val words = scoreRDD.map(line=>line(0).toString).collect().head.toDouble

        val ave_score = words.formatted("%.2f")
        ave_scores.put(genre,ave_score)
      }
    })
    ave_scores
  }

  override def run(args: String*): Unit = {
    println("正在启动本地spark")
  }
}
