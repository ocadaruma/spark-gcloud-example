package com.example

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapred.{JobConf, RecordWriter, Reporter}
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.hadoop.util.Progressable

import scala.collection.mutable

case class Key(clientId: Long, cookieId: String)

class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat[Any, Any] {

  override def generateActualKey(key: Any, value: Any): Any =
    NullWritable.get()

  override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String = key match {
    case Key(clientId, _) => new Path(clientId.toString, name).toString
    case _ => new Path(key.toString, name).toString
  }

  /**
   * Create a composite record writer that can write key/value data to different
   * output files
   *
   * @param fs
   * the file system to use
   * @param job
   * the job conf for the job
   * @param name
   * the leaf file name for the output file (such as part-00000")
   * @param arg3
   * a progressable for reporting progress.
   * @return a composite record writer
   */
  override def getRecordWriter(fs: FileSystem, job: JobConf, name: String, arg3: Progressable): RecordWriter[Any, Any] = {

    new RecordWriter[Any, Any]() { // a cache storing the record writers for different output files.
      private[this] val recordWriterCache = mutable.LinkedHashMap.empty[String, RecordWriter[Any, Any]]

      override def write(key: Any, value: Any): Unit = { // get the file name based on the key
        val keyBasedPath: String = generateFileNameForKeyValue(key, value, name)
        // get the file name based on the input file name
        val finalPath: String = getInputFileBasedOutputFileName(job, keyBasedPath)
        // get the actual key
        val actualKey: Any = generateActualKey(key, value)
        val actualValue: Any = generateActualValue(key, value)
        val rw: RecordWriter[Any, Any] = this.recordWriterCache.getOrElseUpdate(finalPath, getBaseRecordWriter(fs, job, finalPath, arg3))
        rw.write(actualKey, actualValue)
      }

      override def close(reporter: Reporter): Unit = {
        this.recordWriterCache.foreach { case (_, rw) =>
          rw.close(reporter)
        }
        this.recordWriterCache.clear()
      }
    }
  }
}
