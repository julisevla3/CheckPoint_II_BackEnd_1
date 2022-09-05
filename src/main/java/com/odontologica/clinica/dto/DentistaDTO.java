package com.odontologica.clinica.dto;

import com.odontologica.clinica.entity.DentistaEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DentistaDTO {

    private String nome;
    private String sobrenome;
    private String matricula;

    public DentistaDTO(DentistaEntity dentistaEntity) {
        this.nome = dentistaEntity.getNome();
        this.sobrenome = dentistaEntity.getSobrenome();
        this.matricula = dentistaEntity.getMatricula();
    }
}

