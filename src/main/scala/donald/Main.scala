package donald

import java.io.{File, FileInputStream}

import donald.models.{Api, CoccocMusic, Environments, Mobile}
import play.api.libs.json.Json

import scala.io.Source

/**
 * Hello world!
 *
 */
object Main extends App {
  val filename = System.getProperty("user.dir") + "/src/main/resources/env.json"
  val bufferSource=Source.fromFile(filename)
  try {
    val source:String=bufferSource.getLines.mkString
    val jsonFormat = Json.parse(source)
    val jsonStructure = Json.fromJson[Environments](jsonFormat)
    println(jsonStructure.getOrElse(Environments).asInstanceOf[Environments].coccocMusic.mobile.api.domain)
  }
  finally {
    bufferSource.close()
  }

}
