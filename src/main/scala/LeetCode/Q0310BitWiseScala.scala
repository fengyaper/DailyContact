package LeetCode

object Q0310BitWiseScala {

  /**
   * 计算int数据类型的32进制
   *
   * @param num
   */
  def printBit(num: Int): Unit = {
    var str: String = ""   //给string类型赋空值
    for (i <- 31 to 0 by -1) {
      val str1: String = if ((num & (1 << i)) == 0) "0" else "1"
      str+=str1
    }
    print(str)
  }

  def main(args: Array[String]): Unit = {
    val num: Int = 4
    printBit(num)

  }


}
