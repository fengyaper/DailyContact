package Flink.TableAndSql

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.api.scala._
import org.apache.flink.types.Row


object Sql {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val tableEnv = StreamTableEnvironment.create(env)
    val data: DataStream[String] = env.socketTextStream("node01", 99999)
    //    val table: Table = tableEnv.fromDataStream(data)
    val test = tableEnv.registerDataStream("test", data, 'sid, 'callOut, 'callIn, 'callType, 'callTime.rowtime, 'duration)

    //业务1：每隔5秒、每个基站通话成功总时间
    //tumble 滚动窗口；hop 滑动窗口
    //tumble_start 窗口开始时间, tumble_end 窗口结束时间
    tableEnv.sqlQuery(
      """
        |select sid,sum(duration),
        |tumble_start(callTime, interval '5' second),
        |tumble_end(callTime, interval '5' second)
        |from test
        |where callType ='success'
        |group by tumble(callTime,interval '5' second),sid
        |""".stripMargin)
      .toRetractStream[Row]
      .print()

    //业务2：每隔5秒、计算每个基站10s内通话成功的总时间
    ////hop(event_time,滑动步长, 窗口大小)
    tableEnv.sqlQuery(
      """
        |select sid,sum(duration),
        |hop_start(callTime,interval '5' second,interval '10' second),
        |hop_end(callTime, interval '5' second, interval '10' second)
        |from test
        |where callType ='success'
        |group by hop(callTime,interval '5' second, interval '10' second),sid
        |""".stripMargin)
      .toRetractStream[Row]
      .print()

    env.execute()
  }


}
