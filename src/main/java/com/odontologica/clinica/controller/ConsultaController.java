package com.odontologica.clinica.controller;

import com.odontologica.clinica.controller.dto.ConsultaDTO;
import com.odontologica.clinica.controller.dto.ConsultaRespostaDTO;
import com.odontologica.clinica.entity.ConsultasEntity;
import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.exceptions.BadRequestException;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.ConsultasServiceImpl;
import com.odontologica.clinica.service.impl.DentistaServiceImpl;
import com.odontologica.clinica.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public ConsultaRespostaDTO salvarConsulta(@RequestBody ConsultaDTO consultaDTO) throws BadRequestException {

        PacienteEntity paciente = pacienteService.buscarPorId(consultaDTO.getIdPaciente()).orElse(null);
        DentistaEntity dentista = dentistaService.buscarPorId(consultaDTO.getIdDentista()).orElse(null);

        ConsultasEntity consulta = new ConsultasEntity(consultaDTO.getDataConsulta(),consultaDTO.getHoraConsulta(),
                dentista, paciente);

        consultasService.salvar(consulta);

        return consulta.dtoResposta();
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterarConsulta(@RequestBody ConsultasEntity consultasEntity) throws BadRequestException {
        return ResponseEntity.ok(consultasService.alterar(consultasEntity));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<ConsultasEntity> buscarTodos() throws SQLException {
        return consultasService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<ConsultasEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Optional<ConsultasEntity> consultasEntity = consultasService.buscarPorId(id);
            if (consultasEntity != null && consultasEntity.isPresent()) {
                return ResponseEntity.ok(consultasEntity);
            }
            throw new ResourceNotFoundException("Não foi encontrado a consulta " + id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao buscar a consulta " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            consultasService.excluir(id);
            return ResponseEntity.ok("Consulta deletada !");
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado consulta com id " + id);
        }
    }

}
