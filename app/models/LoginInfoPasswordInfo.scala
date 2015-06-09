package models

import java.util.UUID

import com.mohiva.play.silhouette.api.{ LoginInfo }
import play.api.libs.json.Json
import models._
import reactivemongo.bson.BSONDocument._
import reactivemongo.bson._


case class LoginInfoPasswordInfo(

	loginInfo: LoginInfo,
	passwordInfo: PasswordInfo
	
)


object LoginInfoPasswordInfo {


	implicit val jsonFormat1 = Json.format[LoginInfoPasswordInfo]

  	implicit object LoginInfoPasswordInfoBSONReader extends BSONDocumentReader[LoginInfoPasswordInfo] {
    	def read(doc: BSONDocument): LoginInfoPasswordInfo = {
      		LoginInfoPasswordInfo(
        		doc.getAs[BSONObjectID]("_id"),
        		doc.getAs[String]("loginInfo").get,
        		doc.getAs[String]("authInfo").get
      		)
      	}	
  	}


}

