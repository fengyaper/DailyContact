import tools._

import java.util.Date
import scala.collection.immutable

object ScalaTest {
  //偏应用函数
  def fun01(date:Date,a:Int,b:String){
    println(s"偏应用函数 = $date \t $a \t $b")
  }

  //可变参数
  def fun02(a:Int*)={
    for (elem <- a) {
      print(elem+", ")
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

    val hsq = textSchema("hsq", 18)
    val bm = textSchema("hsq", 18)
    print(hsq.equals(bm))

  }
  case class textSchema(name:String,age:Int)
}