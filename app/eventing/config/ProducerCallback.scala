package eventing.config

import com.google.inject.Singleton
import org.apache.kafka.clients.producer.{Callback, RecordMetadata}
import play.api.Logging

@Singleton
class ProducerCallback extends Callback with Logging {

  override def onCompletion(metadata: RecordMetadata,
                            exception: Exception): Unit = {
    if (exception == null)
      logger.info(
        s"p:${metadata.partition()} t:${metadata.partition()} o:${metadata.offset()}"
      )
    else
      logger.error(s"Error while publishing to Kafka ${exception.getMessage}")
  }

}
