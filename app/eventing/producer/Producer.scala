package eventing.producer

import com.google.inject.{Inject, Singleton}
import eventing.config.{ProducerCallback, ProducerKafkaConfig}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import play.api.{Configuration, Logging}
import utils.Constants.KafkaConfig._

@Singleton
class Producer @Inject()(val config: Configuration, callback: ProducerCallback)
    extends Logging
    with ProducerKafkaConfig {

  val producer = new KafkaProducer[String, String](properties)
  val topic: String = config.get[String](KAFKA_PRODUCER_TOPIC)

  def publish(message: String) = {
    val record: ProducerRecord[String, String] =
      new ProducerRecord(topic, message)

    producer.send(record, callback)
    producer.flush()

  }
}
