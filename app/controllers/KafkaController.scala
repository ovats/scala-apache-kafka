package controllers

import javax.inject._
import play.api.mvc._
import eventing.producer.Producer
import models.KafkaMessage
import play.api.libs.json.{JsError, JsSuccess}
import utils.Constants.ControllerMessages._

@Singleton
class KafkaController @Inject()(val controllerComponents: ControllerComponents,
                                producer: Producer)
    extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(APPLICATION_RUNNING)
  }

  def publishSimpleMessage(message: String) = Action { implicit request =>
    producer.publish(s"$PUBLISHING_MESSAGE: $message ...")
    Ok(MESSAGE_PUBLISHED)
  }

  def publishMessage = Action(parse.json) { implicit request =>
    request.body.validate[KafkaMessage] match {
      case JsSuccess(value, _) =>
        producer.publish(s"$PUBLISHING_MESSAGE: ${value.message}")
        Ok(MESSAGE_PUBLISHED)
      case JsError(errors) =>
        BadRequest(s"$INVALID_MESSAGE: ${errors.toString()}")
    }
  }

}
