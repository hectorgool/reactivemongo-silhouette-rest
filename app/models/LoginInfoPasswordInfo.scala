package models

import java.util.UUID

import com.mohiva.play.silhouette.api.{ LoginInfo }
import play.api.libs.json.Json
import models._


case class LoginInfoPasswordInfo(

	loginInfo: LoginInfo,
	passwordInfo: PasswordInfo
	
)

/**
 * The companion object.
 */
object LoginInfoPasswordInfo {

  /**
   * Converts the [User] object to Json and vice versa.
   */
  implicit val jsonFormat1 = Json.format[LoginInfoPasswordInfo]

}

