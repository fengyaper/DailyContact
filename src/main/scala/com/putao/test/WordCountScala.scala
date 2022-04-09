package com.putao.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCountScala {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

        val lis = List(1, 2, 3, 4)
    lis.zipWithIndex.foreach(println)
    //    val list2: List[Int] = List(3, 4, 5, 6)
    //    val rdd1: RDD[Int] = sc.parallelize(lis)
    //    val rdd2: RDD[Int] = sc.parallelize(list2)
    //    rdd1.intersection(rdd2).foreach(println)
    val rdd3: RDD[(String, Int)] = sc.parallelize(List(
      ("ZS", 11),
      ("ZS", 12),
      ("LS", 13),
      ("ZL", 14)
    ))

    val rdd4: RDD[(String, Int)] = sc.parallelize(List(
      ("ZS", 31),
      ("ZS", 32),
      ("WU", 33),
      ("TQ", 44)
    ))
//    rdd3.cogroup(rdd4).foreach(println)


    while (true) {
    }

  }

}
