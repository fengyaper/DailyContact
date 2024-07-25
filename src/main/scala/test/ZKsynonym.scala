package test

import com.hankcs.hanlp.HanLP
import org.apache.spark.ml.feature.RegexTokenizer
import org.apache.spark.sql.SparkSession

object ZKsynonym {
  def main(args: Array[String]): Unit = {
    // 创建 SparkSession
    val spark = SparkSession.builder()
      .appName("SynonymMerge")
      .master("local[*]")
      .getOrCreate()

    // 创建数据集
    val data = Seq(
      (1, "我喜欢吃苹果"),
      (2, "他爱吃香蕉")
    )
    val df = spark.createDataFrame(data).toDF("id", "text")

    // 定义分词器
    val tokenizer = new RegexTokenizer()
      .setInputCol("text")
      .setOutputCol("words")
      .setPattern("\\W") // 非单词字符分割文本

    // 对文本进行切词处理
    val tokenizedDF = tokenizer.transform(df)

    // 同义词归并函数
    def mergeSynonyms(words: Seq[String]): String = {
      val mergedWords = words.flatMap { word =>
        val synonyms = HanLP.segment(word)
        synonyms.get(0).word
      }
      mergedWords.mkString("")
    }
    val mergeSynonymsUDF = spark.udf.register("mergeSynonyms", mergeSynonyms _)

    // 应用同义词归并函数
    val mergedDF = tokenizedDF.withColumn("mergedText", mergeSynonymsUDF($"words"))

    // 显示结果
    mergedDF.show(truncate = false)
  }

}
