package devigor.pix_kafka_consumer.consumer;

import com.google.gson.Gson;
import devigor.pix_kafka_consumer.dto.PixDTO;
import devigor.pix_kafka_consumer.dto.PixStatus;
import devigor.pix_kafka_consumer.exception.KeyNotFoundException;
import devigor.pix_kafka_consumer.model.Key;
import devigor.pix_kafka_consumer.model.Pix;
import devigor.pix_kafka_consumer.repository.KeyRepository;
import devigor.pix_kafka_consumer.repository.PixRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class PixValidator {
    private final KeyRepository keyRepository;
    private final PixRepository pixRepository;
    private final Gson gson;

    public PixValidator(KeyRepository keyRepository, PixRepository pixRepository, Gson gson) {
        this.keyRepository = keyRepository;
        this.pixRepository = pixRepository;
        this.gson = gson;
    }

    @KafkaListener(topics = "pix-topic", groupId = "grupo")
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "5",
            autoCreateTopics = "true",
            include = KeyNotFoundException.class)
    public void processaPix(String message) {
        PixDTO pixDTO = gson.fromJson(message, PixDTO.class);
        System.out.println("Pix recebido: " + pixDTO.getIdentifier());

        Pix pix = pixRepository.findByIdentifier(pixDTO.getIdentifier());
        Key origem = keyRepository.findByChave(pixDTO.getChaveOrigem());
        Key destino = keyRepository.findByChave(pixDTO.getChaveDestino());

        if (origem != null && destino != null) {
            pix.setStatus(PixStatus.PROCESSADO);
        } else {
            pix.setStatus(PixStatus.ERRO);
//            throw new KeyNotFoundException();
        }
        pixRepository.save(pix);
    }
}
