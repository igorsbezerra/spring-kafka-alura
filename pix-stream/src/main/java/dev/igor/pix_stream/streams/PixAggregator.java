package dev.igor.pix_stream.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PixAggregator {

    @Autowired
    private void aggregator(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream("pix-topic", Consumed.with(Serdes.String(), Serdes.String()))
                .peek((key, value) -> System.out.println("Pix recebido: " + value));
        messageStream.print(Printed.toSysOut());
        messageStream.to("pix-topic-verify-fraud", Produced.with(Serdes.String(), Serdes.String()));
    }
}
