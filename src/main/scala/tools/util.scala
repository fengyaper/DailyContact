package tools
import scala.io.{BufferedSource, Source}

object util {

  //读取本地文件
  def getLocalFiles(path:String)={
    Source.fromFile(path).getLines().toList
  }


}
