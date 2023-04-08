package com.projeto.cliente.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.cliente.Models.Endereco;
import com.projeto.cliente.Services.EnderecoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EnderecoConsumer {

    final
    EnderecoService enderecoService;

    public EnderecoConsumer(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @KafkaListener(topics = "${topic.endereco-client}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String enderecoString) throws JsonProcessingException {
        log.info("Mensagem Endereço {}", enderecoString);

        ObjectMapper objectMap = new ObjectMapper();
        Endereco endereco = objectMap.readValue(enderecoString, Endereco.class);

        enderecoService.register(endereco);
    }


}
