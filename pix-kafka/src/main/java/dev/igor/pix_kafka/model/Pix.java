package dev.igor.pix_kafka.model;

import dev.igor.pix_kafka.dto.PixDTO;
import dev.igor.pix_kafka.dto.PixStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Pix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private String dataTransferencia;
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getIdentifier());
        pix.setChaveOrigem(pixDTO.getChaveOrigem());
        pix.setChaveDestino(pixDTO.getChaveDestino());
        pix.setValor(Double.valueOf(pixDTO.getValor()));
        pix.setDataTransferencia(pixDTO.getDataTransferencia().toString());
        pix.setStatus(pixDTO.getStatus());
        return pix;
    }

    public Pix() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getChaveOrigem() {
        return chaveOrigem;
    }

    public void setChaveOrigem(String chaveOrigem) {
        this.chaveOrigem = chaveOrigem;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public void setChaveDestino(String chaveDestino) {
        this.chaveDestino = chaveDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(String dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public PixStatus getStatus() {
        return status;
    }

    public void setStatus(PixStatus status) {
        this.status = status;
    }
}
