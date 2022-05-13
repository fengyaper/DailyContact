package Flink.Transformation

import org.apache.flink.streaming.api.scala.{DataStream, SplitStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

/*
df
 */

object SplitTransformation {
  def main(args: Array[String]): Unit = {
//    split +select算子联合使用可以达到分流的作用
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dStream: DataStream[Long] = env.generateSequence(1, 100)
    val value: SplitStream[Long] = dStream.split(d => {
      d % 2 match {
        case 0 => List("f")
        case 1 => List("s")
      }
    })
    val value1: DataStream[Long] = value.select("f")

    value1.print()
    env.execute()

  }

}
