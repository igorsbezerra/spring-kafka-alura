package devigor.pix_kafka_consumer.repository;

import devigor.pix_kafka_consumer.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Integer> {
    Key findByChave(String chave);
}
