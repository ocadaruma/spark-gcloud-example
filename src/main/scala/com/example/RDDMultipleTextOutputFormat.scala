package com.example

import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat

case class Key(clientId: Long, cookieId: String)

class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat[Any, Any] {

  override def generateActualKey(key: Any, value: Any): Any =
    NullWritable.get()

  override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String = key match {
    case Key(clientId, _) => new Path(clientId.toString, name).toString
    case _ => new Path(key.toString, name).toString
  }

}
