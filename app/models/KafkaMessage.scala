package models

import play.api.libs.json.{Json, Reads, Writes}

case class KafkaMessage(message: String)

object KafkaMessage {
  implicit val readsKafkaMessage: Reads[KafkaMessage] = Json.reads[KafkaMessage]
}
