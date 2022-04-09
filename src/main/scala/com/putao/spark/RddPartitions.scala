package com.putao.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object RddPartitions {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("test").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val rdd_sql: RDD[Int] = sc.parallelize(1 to 10, 2)
    //    rdd_sql.map(
    //      (row:Int)=>{
    //      println("-----sql-con-----")
    //      println(s"---select-$row------")
    //      println("-----sql-close-----")
    //      }
    //    ).foreach(println)

//    rdd_sql.mapPartitionsWithIndex(
//
//      (pindex, piter) => {
//        val list = new ListBuffer[String]
//        println(s"----$pindex-sql-con-----")
//        while (piter.hasNext) {
//          val va = piter.next()
//          println(s"---select-$pindex--${va}------")
//          list.append(va + "select")
//        }
//        println("-----sql-close-----")
//        list.toIterator
//      }
//    ).foreach(println)

    rdd_sql.mapPartitionsWithIndex(

      (pindex, piter) => {
        println(s"----$pindex-sql-con-----")
        new Iterator[String] {
          override def hasNext = if (piter.hasNext == false) {
            println("-----sql-close-----");
            false
          } else true

          override def next() = {
            val va = piter.next()
            println(s"---select-$pindex--${va}------")
            va + "select"
          }
        }
      }
    ).foreach(println)


  }

}
