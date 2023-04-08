package com.projeto.endereco.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.endereco.request.EnderecoRequest;
import com.projeto.endereco.response.EnderecoResponse;
import com.projeto.endereco.service.EnderecoService;
import com.projeto.endereco.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("endereco")
public class EnderecoResource {
    private final ViaCepService viaCepService;

    @Autowired
    private EnderecoService enderecoService;
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> obterCep(@PathVariable("cep") String cep) {
        EnderecoResponse enderecoResponse = viaCepService.obterCep(cep);
        return ResponseEntity.ok(enderecoResponse);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponse> enviarEndereco(@RequestBody EnderecoRequest enderecoRequest) throws JsonProcessingException {
        log.info("## Dados envidos pelo cliente: {}", enderecoRequest);

        EnderecoResponse enderecoResponse = viaCepService.obterCep(enderecoRequest.getCep());

        enderecoResponse.setComplemento(enderecoRequest.getComplemento());
        enderecoResponse.setNumero(enderecoRequest.getNumero());

        ObjectMapper objectMapper = new ObjectMapper();
        String mensagem = objectMapper.writeValueAsString(enderecoResponse);

        enderecoService.sendMessage(mensagem);
        log.info("## Endereço retornado pela API de CEP: {}", enderecoResponse);

        return ResponseEntity.ok(enderecoResponse);
    }
}