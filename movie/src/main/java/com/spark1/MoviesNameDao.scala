package com.spark1

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

class MoviesNameDao{
  def searchByName(name:String) : String ={
    val spark = SparkSession
      .builder()
      .master("local")
      .config("spark.sql.codegen.wholeStage", false)
      .getOrCreate()

    println("init spark")
    val schema = StructType(
      List(
        StructField("movieId", IntegerType, true),
        StructField("title", StringType, true),
        StructField("genres", StringType, true)
      )
    )

    val df = spark.read.format("csv")
      .option("header", true)
      .option("delimiter", ",")
      .schema(schema)
      .load("hdfs://192.168.107.160:9000/movies.csv").cache()
    println(s"read text file, df type = ${df.getClass.toString}, rdd type = ${df.rdd.getClass.toString}")
    val name1 = name.toLowerCase().r
    val filter_rdd = df.rdd.filter(i=> !name1.findFirstIn(i.toString().toLowerCase()).toString().equals("None"))
    val temp = filter_rdd.collect().mkString("")

    temp
  }
}
