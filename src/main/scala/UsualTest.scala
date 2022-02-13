import tools._

object UsualTest {

  def letterCasePermutation(s: String): List[String] = {


    ???
  }

  def main(args: Array[String]): Unit = {

    val path = "C:\\Users\\Administrator\\Desktop\\note"
    util.getLocalFiles(path)
      .zipWithIndex
      .toSeq
      .sortBy(f=>f._2)(Ordering.Int.reverse)
      .map(f=>f._1)
      .foreach(println(_))



  }
}
