package com.putao.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCountScala {



//  def main(args: Array[String]): Unit = {
//
//    val conf = new SparkConf()
//    conf.setAppName("word count")
//    conf.setMaster("local")
//
//    val sc = new SparkContext(conf)
//
//    val res: RDD[(String, Int)] = sc.textFile("data/word")
//      .flatMap(_.split(" "))
//      .map( (_, 1))
//      .reduceByKey(_ + _)
//
//    res.foreach(println)
//
//
//
//
//  }

}
