package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.exceptions.BadRequestException;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.DentistaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    private DentistaServiceImpl dentistaService;

    public DentistaController(DentistaServiceImpl dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<DentistaEntity> salvarDentista(@RequestBody DentistaEntity dentistaEntity) throws BadRequestException {
        try {
            return ResponseEntity.ok(dentistaService.salvar(dentistaEntity));
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível salvar dentista !");
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterarDentista(@RequestBody DentistaEntity dentistaEntity) throws BadRequestException{
        return ResponseEntity.ok(dentistaService.alterar(dentistaEntity));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<DentistaEntity> buscarTodos() {
        return dentistaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DentistaEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Optional<DentistaEntity> dentistaEntity = dentistaService.buscarPorId(id);
            if (dentistaEntity != null && dentistaEntity.isPresent()) {
                return ResponseEntity.ok(dentistaEntity);
            }
            throw new ResourceNotFoundException("Não foi encontrado o Dentista " + id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao buscar o dentista " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> excluirDentista(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            dentistaService.excluir(id);
            return ResponseEntity.ok("Dentista deletado !");
        }catch (Exception e){
            throw new ResourceNotFoundException("Não foi encontrado o dentista com id " + id);
        }
    }

}