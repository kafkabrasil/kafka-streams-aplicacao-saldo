package com.kafkabrasil.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class ProcessadorSaldo {
    public static void main(String[] args) {
        criarStreams();
    }

    private static Properties configurarStreams(){
        final Properties streamsConfiguration = new Properties();
        // Give the Streams application a unique name.  The name must be unique in the Kafka cluster
        // against which the application is run.
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, System.getenv("APPLICATION_ID"));
        streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, System.getenv("APPLICATION_ID")+"-client");
        // Where to find Kafka broker(s).
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("BOOTSTRAP_SERVERS"));
        // Specify default (de)serializers for record keys and for record values.
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        // Set the commit interval to 500ms so that any changes are flushed frequently. The low latency
        // would be important for anomaly detection.
        streamsConfiguration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 500);

        return streamsConfiguration;
    }

    private static void criarStreams(){
        final StreamsBuilder builder = new StreamsBuilder();
        // Create a stream from the input topic
        KStream<String, String> inputStream = builder.stream(System.getenv("INPUT_TOPIC"));

        // Process the stream
        KStream<String, String> processedStream = inputStream.mapValues(value -> {
            return value+" - Processed";
        });

        // Write the processed stream to the output topic
        processedStream.to(System.getenv("OUTPUT_TOPIC"));

        // Build the topology
        final Topology topology = builder.build();

        // Start the Kafka Streams application
        KafkaStreams streams = new KafkaStreams(topology, configurarStreams());
        streams.start();
    }
}