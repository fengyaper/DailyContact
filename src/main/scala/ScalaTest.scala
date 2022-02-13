import tools._

import scala.collection.immutable

object ScalaTest {
  def test(a:Int = 8,b:String = "asdf")={
    println(s"$a \t $b")
  }


  def main(args: Array[String]): Unit = {

    val res=for(i <- 1 to 10) yield if(i<5) i*10
    println(res)

  }
}