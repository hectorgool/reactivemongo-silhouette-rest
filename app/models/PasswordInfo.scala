package models


import java.util.UUID
import com.mohiva.play.silhouette.api.{ Identity, LoginInfo }
import play.api.libs.json.Json


case class PasswordInfo(

	hasher: String,
	password: String,
    salt: Option[String]

)


/**
 * The companion object.
 */
object PasswordInfo {

  /**
   * Converts the [User] object to Json and vice versa.
   */
  implicit val jsonFormat2 = Json.format[PasswordInfo]


}