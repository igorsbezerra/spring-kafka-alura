package dev.igor.pix_kafka.repository;

import dev.igor.pix_kafka.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Integer> {
}
