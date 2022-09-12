package com.odontologica.clinica.dto;
import com.odontologica.clinica.entity.PacienteEntity;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class PacienteDTO {
    private String nome;
    private String sobrenome;
    private String endereco;
    private String rg;
    private Date dataAlta;

    public PacienteDTO(PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.getNome();
        this.sobrenome = pacienteDTO.getSobrenome();
        this.endereco = pacienteDTO.getEndereco();
        this.rg = pacienteDTO.getRg();
        this.dataAlta = pacienteDTO.getDataAlta();
    }
}


