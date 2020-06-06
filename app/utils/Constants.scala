package utils

object Constants {

  object KafkaConfig {
    val KAFKA_HOST = "kafka.host"
    val KAFKA_PRODUCER_TOPIC = "kafka.producer.topic"
  }

  object ControllerMessages {
    val APPLICATION_RUNNING = "Application running"
    val MESSAGE_PUBLISHED = "message published"
    val INVALID_MESSAGE = "Invalid message"
    val PUBLISHING_MESSAGE = "Publishing message"
  }
}
