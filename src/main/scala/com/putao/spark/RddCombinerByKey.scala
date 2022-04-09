package com.putao.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RddCombinerByKey {
  def main(args: Array[String]): Unit = {
    //    max ,min, count,sum,avg
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("test")

    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val rdd1: RDD[(String, Int)] = sc.parallelize(List(
      ("zs", 1),
      ("zs", 2),
      ("ls", 3),
      ("ls", 21),
      ("wu", 4),
      ("wu", 14),
      ("wu", 45),
      ("zs", 11),
      ("zs", 39)
    ))

    val sum_rdd: RDD[(String, Int)] = rdd1.reduceByKey(_ + _)
    val min_rdd: RDD[(String, Int)] = rdd1.reduceByKey((ov, nv) => if (ov > nv) nv else ov)
    val max_rdd: RDD[(String, Int)] = rdd1.reduceByKey((ov, nv) => if (ov > nv) ov else nv)
    val count_rdd: RDD[(String, Int)] = rdd1.mapValues(e => 1).reduceByKey(_ + _)
    val avg_rdd: RDD[(String, Int)] = sum_rdd.join(count_rdd).mapValues(e => e._1 / e._2)
    println("-------sum---------")
    sum_rdd.foreach(println)
    println("-------min---------")
    min_rdd.foreach(println)
    println("-------max---------")
    max_rdd.foreach(println)
    println("-------count---------")
    count_rdd.foreach(println)
    println("-------avg---------")
    avg_rdd.foreach(println)

    //    createCombiner: V => C,
    //    mergeValue: (C, V) => C,
    //    mergeCombiners: (C, C) => C,
    val avg_rdd2: RDD[(String, Int)] = rdd1.combineByKey(
      (value: Int) => (value, 1),
      (oldVale: (Int, Int), value: Int) => (oldVale._1 + value, oldVale._2 + 1),
      (oldValue: (Int, Int), old1: (Int, Int)) => (oldValue._1 + old1._1, oldValue._2 + old1._2)
    ).mapValues(e => e._1 / e._2)
    avg_rdd2.foreach(println)


    while (true) {

    }


  }
}
