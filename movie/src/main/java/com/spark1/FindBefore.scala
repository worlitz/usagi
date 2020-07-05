package com.spark1

import org.apache.spark.sql.{SparkSession, types}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object FindBefore {
  def main(args: Array[String]): Unit = {
    getMovieInfo("Popcorn (2007)")
  }
  def getMovieInfo(name: String):String = {
    val spark = SparkSession
      .builder()
      .config("spark.jars","D:\\myproject\\scala\\target\\scalaWork-1.0-SNAPSHOT.jar")
      .master("local[2]")
      .appName("FindBehind")
      .config("spark.sql.codegen.wholeStage", false)
      .config("spark.network.timeout",60)
      .getOrCreate()

    val sh2 = StructType(
      List(
        StructField("movieID", IntegerType, true),
        StructField("title", StringType, true),
        StructField("genres", StringType, true)
      )
    )

    val df = spark.read.format("csv")
      .option("header", true)
      .option("delimiter", ",")
      .schema(sh2)
      .load("hdfs://192.168.107.160:9000/movies.csv").cache()
    println(s"read text file, df type = ${df.getClass.toString} rdd type = ${df.rdd.getClass.toString}")

    val filter_rdd = df.rdd.filter(i => i.getAs[String](1).equals(name)||
      i.getAs[String](1).matches(name+".*"))
    var temp = null
    var list = Set[String]()
    var maps:Map[String,String] = Map()
    var n:Int=0
    val count = filter_rdd.count()
    var unit: Unit = filter_rdd.foreach(i => {
      maps+=(i.get(1).toString
        .replace(","," ")
        .replace("("," ")
        .replace(")"," ")
        .replace("'"," ")
        ->i.get(2).toString
        .replace("'"," ")
        .replace(","," ")
        .replace("("," ")
        .replace(")"," "))
      n=n+1
      if(count==n){
        ScalaJdbcSelect.insert(maps)
      }
      list
    })
    temp
    return  temp
  }
}
