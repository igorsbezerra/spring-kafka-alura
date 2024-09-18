package devigor.pix_kafka_consumer.repository;

import devigor.pix_kafka_consumer.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Integer> {
    Pix findByIdentifier(String identifier);
}
