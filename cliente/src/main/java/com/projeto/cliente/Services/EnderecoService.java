package com.projeto.cliente.Services;

import com.projeto.cliente.Models.Endereco;
import com.projeto.cliente.Repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public void register(Endereco endereco) {
        enderecoRepository.save(endereco);

    }
}