package com.odontologica.clinica.controller;

import com.odontologica.clinica.controller.dto.ConsultaDTO;
import com.odontologica.clinica.controller.dto.ConsultaRespostaDTO;
import com.odontologica.clinica.entity.ConsultasEntity;
import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.service.impl.ConsultasServiceImpl;
import com.odontologica.clinica.service.impl.DentistaServiceImpl;
import com.odontologica.clinica.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultasServiceImpl consultasService;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private DentistaServiceImpl dentistaService;

    @PostMapping("/salvar")
    @Transactional
    public ConsultaRespostaDTO salvarConsulta(@RequestBody ConsultaDTO consultaDTO) throws SQLException {
        PacienteEntity paciente = pacienteService.buscarPorId(consultaDTO.getIdPaciente()).orElse(null);
        DentistaEntity dentista = dentistaService.buscarPorId(consultaDTO.getIdDentista()).orElse(null);

        ConsultasEntity consulta = new ConsultasEntity(consultaDTO.getDataConsulta(),consultaDTO.getHoraConsulta(),
                dentista, paciente);

        consultasService.salvar(consulta);

        return consulta.dtoResposta();
    }
}
