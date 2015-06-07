package models

import java.util.UUID

import com.mohiva.play.silhouette.api.{ LoginInfo }
import play.api.libs.json.Json

/**
 * The user object.
 *
 * @param userID The unique ID of the user.
 * @param loginInfo The linked login info.
 * @param firstName Maybe the first name of the authenticated user.
 * @param lastName Maybe the last name of the authenticated user.
 * @param fullName Maybe the full name of the authenticated user.
 * @param email Maybe the email of the authenticated provider.
 * @param avatarURL Maybe the avatar URL of the authenticated provider.
 */
case class LoginInfoPasswordInfo(

	loginInfo: LoginInfo,
  	hasher: String,
	password: String,
    salt: Option[String]
)

/**
 * The companion object.
 */
object LoginInfoPasswordInfo {

  /**
   * Converts the [User] object to Json and vice versa.
   */
  implicit val jsonFormat = Json.format[LoginInfoPasswordInfo]


}