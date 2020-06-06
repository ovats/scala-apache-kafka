package eventing.config

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

trait ProducerKafkaConfig extends CommonKafkaConfig {

  override def properties: Properties = {
    val props: Properties = super.properties

    props.setProperty(
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      classOf[StringSerializer].getName
    )
    props.setProperty(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      classOf[StringSerializer].getName
    )

    props
  }

}
