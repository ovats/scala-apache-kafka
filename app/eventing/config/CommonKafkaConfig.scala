package eventing.config

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig
import utils.Constants.KafkaConfig.KAFKA_HOST
import play.api.Configuration

trait CommonKafkaConfig {

  val config: Configuration

  val kafkaHost: String = config.get[String](KAFKA_HOST)

  def properties: Properties = {
    val props: Properties = new Properties()
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost)
    props
  }
}
