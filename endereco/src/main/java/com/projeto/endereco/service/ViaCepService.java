package com.projeto.endereco.service;

import com.projeto.endereco.response.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url="https://viacep.com.br/ws")
public interface ViaCepService {
    @GetMapping("/{cep}/json")
    EnderecoResponse obterCep(@PathVariable("cep") String cep);
}