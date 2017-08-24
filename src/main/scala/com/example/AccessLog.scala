package com.example

import io.circe._, io.circe.generic.auto._, io.circe.syntax._

case class AccessLog(
  clientId: Long,
  cookieId: String,
  referrer: String,
  location: String,

  key_0001: String,
  key_0002: String,
  key_0003: String,
  key_0004: String,
  key_0005: String,
  key_0006: String,
  key_0007: String,
  key_0008: String,
  key_0009: String,
  key_0010: String,
  key_0011: String,
  key_0012: String,
  key_0013: String,
  key_0014: String,
  key_0015: String,
  key_0016: String,
  key_0017: String,
  key_0018: String,
  key_0019: String,
  key_0020: String,
  key_0021: String,
  key_0022: String,
  key_0023: String,
  key_0024: String,
  key_0025: String,
  key_0026: String,
  key_0027: String,
  key_0028: String,
  key_0029: String,
  key_0030: String,
  key_0031: String,
  key_0032: String,
  key_0033: String,
  key_0034: String,
  key_0035: String,
  key_0036: String,
  key_0037: String,
  key_0038: String,
  key_0039: String,
  key_0040: String) {

  def toJson: String = this.asJson.noSpaces
}

object AccessLog {
  private[this] val md = java.security.MessageDigest.getInstance("SHA-1")

  def generateN(clientId: Long, num: Int): Seq[AccessLog] = {
    (0 until num).map { i =>
      AccessLog(
        clientId = clientId,
        cookieId = md.digest(s"${clientId}_${i}".getBytes("UTF-8")).map("%02x".format(_)).mkString,

        referrer = s"http://www.example.com/referrer/${i}.html",
        location = s"http://www.example.com/location/${i}.html",

        key_0001 = f"value_0001",
        key_0002 = f"value_0002",
        key_0003 = f"value_0003",
        key_0004 = f"value_0004",
        key_0005 = f"value_0005",
        key_0006 = f"value_0006",
        key_0007 = f"value_0007",
        key_0008 = f"value_0008",
        key_0009 = f"value_0009",
        key_0010 = f"value_0010",
        key_0011 = f"value_0011",
        key_0012 = f"value_0012",
        key_0013 = f"value_0013",
        key_0014 = f"value_0014",
        key_0015 = f"value_0015",
        key_0016 = f"value_0016",
        key_0017 = f"value_0017",
        key_0018 = f"value_0018",
        key_0019 = f"value_0019",
        key_0020 = f"value_0020",
        key_0021 = f"value_0021",
        key_0022 = f"value_0022",
        key_0023 = f"value_0023",
        key_0024 = f"value_0024",
        key_0025 = f"value_0025",
        key_0026 = f"value_0026",
        key_0027 = f"value_0027",
        key_0028 = f"value_0028",
        key_0029 = f"value_0029",
        key_0030 = f"value_0030",
        key_0031 = f"value_0031",
        key_0032 = f"value_0032",
        key_0033 = f"value_0033",
        key_0034 = f"value_0034",
        key_0035 = f"value_0035",
        key_0036 = f"value_0036",
        key_0037 = f"value_0037",
        key_0038 = f"value_0038",
        key_0039 = f"value_0039",
        key_0040 = f"value_0040"
      )
    }
  }
}
