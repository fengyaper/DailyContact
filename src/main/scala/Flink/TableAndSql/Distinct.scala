package Flink.TableAndSql

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api._
import org.apache.flink.table.api.bridge.scala._

object Distinct {


  def main(args: Array[String]): Unit = {
    // 假设有一个 StreamExecutionEnvironment 的环境变量 env
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 创建一个 StreamTableEnvironment
    val tEnv = StreamTableEnvironment.create(env)

    // 示例数据流
    val dataStream = env.fromElements(
      (1, "Alice", 25),
      (2, "Bob", 30),
      (1, "Alice", 25),
      (3, "Charlie", 35)
    )

    // 将数据流转换为 Table
    val table = tEnv.fromDataStream(dataStream, 'id, 'name, 'age)

    // 执行去重操作，根据多个字段去重
    val distinctTable = table.distinct('id, 'name, 'age)

    // 转换为 DataStream
    val resultDataStream: DataStream[(Int, String, Int)] = tEnv.toAppendStream[(Int, String, Int)](distinctTable)

    // 输出结果
    resultDataStream.print()

    // 执行作业
    env.execute("Distinct Example")
  }


}
