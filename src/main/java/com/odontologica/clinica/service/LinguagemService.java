package com.odontologica.clinica.service;

import com.odontologica.clinica.model.LinguagemModel;
import com.odontologica.clinica.repository.LinguagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinguagemService {
    private LinguagemRepository linguagemRepository;

    public LinguagemService(){
        this.linguagemRepository = new LinguagemRepository();
    }

    public LinguagemModel buscarPorTipo(String tipo){
        return linguagemRepository.buscarPorTipo(tipo);

    }
    public List<LinguagemModel> buscarPorTipo(){
        return linguagemRepository.buscarTodos();
    }
}
