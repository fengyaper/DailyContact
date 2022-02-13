package tools
import scala.io.{BufferedSource, Source}

object util {

  val NAME = "zhangsan"

  //读取本地文件
  def getLocalFiles(path:String)={
    Source.fromFile(path).getLines().toList
  }

  //体彩判断，买的，目标
  def sportsResult(myNumber: List[List[Int]],tar:List[Int]): List[Int] = {

    val ints: List[Int] = myNumber.map{list =>
      list.filter(ele => tar.contains(ele)).length
    }
    ints
  }

}
