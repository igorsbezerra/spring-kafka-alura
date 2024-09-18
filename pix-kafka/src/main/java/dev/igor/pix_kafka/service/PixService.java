package dev.igor.pix_kafka.service;

import com.google.gson.Gson;
import dev.igor.pix_kafka.dto.PixDTO;
import dev.igor.pix_kafka.model.Pix;
import dev.igor.pix_kafka.repository.PixRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PixService {
    private final PixRepository pixRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public PixService(PixRepository pixRepository, KafkaTemplate<String, String> kafkaTemplate, Gson gson) {
        this.pixRepository = pixRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.gson = gson;
    }

    public PixDTO salvarPix(PixDTO pixDTO) {
        pixRepository.save(Pix.toEntity(pixDTO));
        try {
            String json = gson.toJson(pixDTO);
            kafkaTemplate.send("pix-topic", pixDTO.getIdentifier(), json);
            System.out.println("Send Json " + json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pixDTO;
    }
}
