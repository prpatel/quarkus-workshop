package com.thejavacafe.backend;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.annotations.Merge;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;
import java.util.logging.Logger;

/**
 * A bean consuming data from the "prices" Kafka topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class EmailGenerator {

    private final static Logger LOGGER = Logger.getLogger(EmailGenerator.class.getName());

    @Incoming("emails")
    @Merge
    public CompletionStage<Void> consume(org.eclipse.microprofile.reactive.messaging.Message msg) {
//        LOGGER.info("Received message on topic and partition) with key and value:" + msg.getTopic() + "\n" + msg.getPartition()+ "\n" + msg.getKey()+ "\n" + msg.getPayload());

        LOGGER.info("Received message on topic 'emails': \n" + msg.getPayload());
        return msg.ack();
    }
}
