package com.example

import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  def main(args: Array[String]): Unit = {

    val n = args(0).toInt
    val output = args(1)

    val conf = new SparkConf().setAppName("gcloud-example")
    val sc = new SparkContext(conf)

    // clean up
    cleanup(sc, output)

    // 250 logs per client
    sc.parallelize(1 to n).flatMap { clientId =>
      AccessLog.generateN(clientId, 250)
    }.map { log =>
      (Key(log.clientId, log.cookieId), log.toJson)
    }.saveAsHadoopFile(output, classOf[NullWritable], classOf[Text], classOf[RDDMultipleTextOutputFormat])

    sc.stop()
  }

  private def cleanup(sc: SparkContext, path: String): Unit = {
    val fspath = new Path(path)

    val fs = fspath.getFileSystem(sc.hadoopConfiguration)

    if (fs.exists(fspath)) {
      fs.delete(fspath, true)
      println(s"deleted path : ${path}")
    }
  }
}
