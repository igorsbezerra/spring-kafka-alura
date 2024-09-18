package dev.igor.pix_kafka.controller;

import dev.igor.pix_kafka.dto.PixDTO;
import dev.igor.pix_kafka.dto.PixStatus;
import dev.igor.pix_kafka.service.PixService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
public class PixController {
    private final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping
    public PixDTO salvarPix(@RequestBody PixDTO pixDTO) {
        pixDTO.setIdentifier(UUID.randomUUID().toString());
        pixDTO.setDataTransferencia(LocalDate.now().toString());
        pixDTO.setStatus(PixStatus.EM_PROCESSAMENTO);

        return pixService.salvarPix(pixDTO);
    }
}
