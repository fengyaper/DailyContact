package Flink.DataSource

import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

import java.util.Random


object ReadDifferentDataSource {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    //    //1、数据源是集合 （不常用）
    //    val ds1: DataStream[Int] = env.fromCollection(List(1, 2, 3, 4, 5))
    //    ds1.print()
//    val df: DataStream[Long] = env.generateSequence(1, 100)

    //2、自定义数据源
    //    //2.1、SourceFunction单线程并行度为1的数据源
    //    val dsSource: DataStream[String] = env.addSource(new SourceFunction[String] {
    //      var flag = true
    //      override def run(ctx: SourceFunction.SourceContext[String]) = {
    //        while (flag) {
    //          val rand =new Random()
    //          ctx.collect("hello" + rand)
    //          Thread.sleep(500)
    //        }
    //      }
    //      override def cancel() = {
    //        flag = false
    //      }
    //    })
    //    dsSource.print()

    //2.2、多线程（多并行）的数据源
    //    val parallelData: DataStream[String] = env.addSource(new ParallelSourceFunction[String] {
    //      var flag = true
    //
    //      override def run(ctx: SourceFunction.SourceContext[String]) = {
    //        val rand = new Random()
    //        while (flag) {
    //          ctx.collect("parallel" + rand)
    //          Thread.sleep(500)
    //        }
    //      }
    //
    //      override def cancel() = {
    //        flag = flag
    //      }
    //    })
    //    parallelData.print()





    env.execute()


  }

}
