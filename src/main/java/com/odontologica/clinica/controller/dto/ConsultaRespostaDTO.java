package com.odontologica.clinica.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaRespostaDTO {

    private Date dataconsulta;
    private LocalDateTime horaConsulta;
    private ConsultaDTO consulta;

}
