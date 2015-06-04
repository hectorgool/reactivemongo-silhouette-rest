package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import models._
import views._


object Application extends Controller{


  def index = Action {
    Ok(views.html.index())
  }


}