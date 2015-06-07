package models.daos

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import models.daos.PasswordInfoDAO._

import scala.collection.mutable
import scala.concurrent.Future

import play.api.Play.current
import play.modules.reactivemongo._
import reactivemongo.bson._
import play.modules.reactivemongo.json.collection.JSONCollection
import models._
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global


/**
 * The DAO to store the password information.
 */
class PasswordInfoDAO extends DelegableAuthInfoDAO[PasswordInfo] {


  def db = ReactiveMongoPlugin.db
  def collection: JSONCollection = db.collection[JSONCollection]("LoginInfoPasswordInfo")

  /**
   * Saves the password info.
   *
   * @param loginInfo The login info for which the auth info should be saved.
   * @param authInfo The password info to save.
   * @return The saved password info.
   */
  def save(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = {

    val json = Json.obj(
      "loginInfo" -> Json.obj(
        "providerID" -> loginInfo.providerID,
        "providerKey" -> loginInfo.providerKey
      ),
      "authInfo" -> Json.obj(
        "hasher" -> authInfo.hasher,
        "password" -> authInfo.password,
        "salt" -> authInfo.salt
      )
    )

    collection.insert(json)
    data += (loginInfo -> authInfo)
    Future.successful(authInfo)
  }

  /**
   * Finds the password info which is linked with the specified login info.
   *
   * @param loginInfo The linked login info.
   * @return The retrieved password info or None if no password info could be retrieved for the given login info.
   */
  def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] = {

    val query = Json.obj( "loginInfo" -> loginInfo )
    val filter = Json.obj( "authInfo" -> 1 )

    collection.find( query, filter ).one[LoginInfoPasswordInfo] 

    Future.successful(data.get(loginInfo))

  }


}

/**
 * The companion object.
 */
object PasswordInfoDAO {

  /**
   * The data store for the password info.
   */
  var data: mutable.HashMap[LoginInfo, PasswordInfo] = mutable.HashMap()
}
