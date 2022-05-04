package Flink


import org.apache.flink.streaming.api.scala.{DataStream,StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

/**
 * @DateTime: 2022/4/28 下午4:27
 * @Description: flink 流计算的测试
 */
object FlinkClass01WordCount {
  def main(args: Array[String]): Unit = {
    /**
     * 1、createLocalEnvironment()：创建一个本地执行的环境 local
     * 2、createLocalEnvironmentWithWebUI(): 创建一个本地执行的环境local，同时还开启Web UI的查看端口 8081
     * 3、getExecutionEnvironment(): 根据执行环境创建上下文，比如 local cluster
     *
     */
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(2)
    val initDStream: DataStream[String] = env.socketTextStream("node01", 8888)
    val result: DataStream[(String, Int)] = initDStream.setParallelism(3)
      .flatMap((ro: String) => ro.split(" ",-1))
      .map(((_: String), 1))
      .keyBy(0).sum(1)

    result.print()

    env.execute("flink word_count")

  }
}
