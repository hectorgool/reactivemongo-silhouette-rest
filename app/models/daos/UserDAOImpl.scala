package models.daos

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import models.User
import models.daos.UserDAOImpl._

import scala.collection.mutable
import scala.concurrent.Future

import play.api.libs.json._
import play.modules.reactivemongo.json.collection.JSONCollection
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.Play.current
import play.modules.reactivemongo._
import reactivemongo.bson._
import play.modules.reactivemongo.json.collection.JSONCollection

import lib._

/**
 * Give access to the user object.
 */
class UserDAOImpl extends UserDAO {


  def db = ReactiveMongoPlugin.db
  def collection: JSONCollection = db.collection[JSONCollection]("user")

  /**
   * Finds a user by its login info.
   *
   * @param loginInfo The login info of the user to find.
   * @return The found user or None if no user for the given login info could be found.
   */
  def find(loginInfo: LoginInfo) = {
    Future.successful(
      users.find { case (id, user) => user.loginInfo == loginInfo }.map(_._2)
    )
  }

  /**
   * Finds a user by its user ID.
   *
   * @param userID The ID of the user to find.
   * @return The found user or None if no user for the given ID could be found.
   */
  def find(userID: UUID) = {
    Future.successful(users.get(userID))
  }

  /**
   * Saves a user.
   *
   * @param user The user to save.
   * @return The saved user.
   */
  def save(user: User) = {

    //println("\n\n*** UserDAOImpl.save *** \n\n")

    user match {
      case User( userID, loginInfo, Some(firstName), Some(lastName), Some(email), None ) => 

        val id = BSONObjectID.generate

        val json = Json.obj(
          //"_id" -> JSONObjectID(id.toString),
          //"userID" -> id.stringify,
          "userID" -> userID,
          "loginInfo" -> loginInfo,
          "firstName" -> firstName,
          "lastName" -> lastName,
          "email" -> email
        )

        collection.insert(json).map( lastError =>
          println("Mongo LastError: %s".format(lastError))
        )

      case _ => // Insert a new user
        println("\n\n ** UserDAOImpl.save.None ** \n\n")
    }

    users += (user.userID -> user)
    Future.successful(user)

  }


}

/**
 * The companion object.
 */
object UserDAOImpl {

  /**
   * The list of users.
   */
  val users: mutable.HashMap[UUID, User] = mutable.HashMap()

  
}
