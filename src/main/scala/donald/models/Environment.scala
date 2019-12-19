package donald.models


import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}
import play.api.libs.json._

case class Environments(coccocMusic: CoccocMusic)

case class CoccocMusic(mobile: Mobile)

case class Mobile(api: Api)

case class Api(domain: String, basicAuth: BasicAuth)

case class BasicAuth(username: String, password: String)

object BasicAuth{
  implicit val basicAuthReads: Reads[BasicAuth] = (
    (JsPath \ "username").read[String] and
      (JsPath \ "password").read[String]
    )(BasicAuth.apply _)
}

object Api{
  implicit val apiReads: Reads[Api] = (
    (JsPath \ "domain").read[String] and
      (JsPath \ "basicAuth").read[BasicAuth]
    )(Api.apply _)
}

object Mobile{

  implicit val mobileReads: Reads[Mobile] =
    (__ \ "api").read[Api].map(Mobile.apply)
}

object CoccocMusic{

  implicit val coccocMusicReads: Reads[CoccocMusic] =
    (__ \ "mobile").read[Mobile].map(CoccocMusic.apply)
}

object Environments{
  implicit val coccocMusicReads: Reads[Environments] =
    (__ \ "coccocMusic").read[CoccocMusic].map(Environments.apply)
}






