package com.putao.test

import java.util.Date
import tools._

object ScalaTest {
  //偏应用函数
  def fun01(date: Date, a: Int, b: String) {
    println(s"偏应用函数 = $date \t $a \t $b")
  }

  //可变参数
  def fun02(a: Int*) = {
    for (elem <- a) {
      print(elem + ", ")
    }
  }


  def main(args: Array[String]): Unit = {
    //    fun01(new Date(),3,"df")
    //    val use = fun01(_:Date,6,_:String)
    //    use(new Date(),"ok")
    //    fun02(3)
    //    fun02(1,2,5)

    //    val ll =Array("hello word","hello kai","hello long")
    //    val llIter: Iterator[String] = ll.iterator
    //    val flatIter: Iterator[String] = llIter.flatMap(x => x.split(" "))
    //    val tuplesIter: Iterator[(String, Int)] = flatIter.map(x => (x, 1))
    //    tuplesIter.foreach(println)

    val me = List(List(3,16,19,27,28),List(17,24,28,32,35),List(1,6,10,18,23),List(1,10,13,20,33),List(6,9,18,30,33))
    val tar = List(5,9,18,31,32)
    println(util.sportsResult(me,tar))

//    val iterator: Iterator[Any] = ("longpang", 8, 12, 10.3, "hello").productIterator
//    val unitsTest: Iterator[Unit] = iterator.map(x =>
//      x match {
//
//        case o: Double => println(s"$o is Double")
//        case e: Int if (e > 11) => println(s"$e > 11")
//        case ss: String => println(s"$x is String!!!!")
//        case _ => println("sha ye mei you")
//      }
//    )
//
//    unitsTest.foreach(println)


  }

}
