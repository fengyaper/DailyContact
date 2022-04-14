package com.putao.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RddCombinerByKeyTemperature {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("temprate")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    //2019-8-6	23
    //求每个月温度最高的两天
    val data= sc.textFile("data/TempraterData").map(row => {

      val items: Array[String] = row.split("\t")
      val dates: Array[String] = items(0).split("-")
      (dates(0).toInt, dates(1).toInt, dates(2).toInt, items(1).toInt)
    }
    )

    val data_key= data.map(t => ((t._1, t._2), (t._3, t._4)))


  }
}
