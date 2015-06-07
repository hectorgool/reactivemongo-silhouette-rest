package forms

import com.mohiva.play.silhouette.api.util.Credentials
import play.api.libs.json.Json

/**
 * The form which handles the submission of the credentials.
 */
object SignInForm {

  case class Data(
    email: String,
    password: String)

  /**
   * The companion object.
   */
  object Data {

    /**
     * Converts the [Date] object to Json and vice versa.
     */
    implicit val jsonFormat = Json.format[Data]
  }


}
