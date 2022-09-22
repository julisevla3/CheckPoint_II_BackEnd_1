package com.odontologica.clinica.service.impl;

import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.repository.IPacienteRepository;
import com.odontologica.clinica.service.IClinicaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IClinicaService<PacienteEntity> {
    private final IPacienteRepository pacienteRepository;

    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteEntity salvar(PacienteEntity pacienteEntity) {
        if(pacienteEntity != null) {
            return pacienteRepository.save(pacienteEntity);
        }
        return new PacienteEntity();
    }

    @Override
    public String alterar(PacienteEntity pacienteEntity) {
        if(pacienteEntity != null && pacienteRepository.findById(pacienteEntity.getId()).isPresent()){
            pacienteRepository.saveAndFlush(pacienteEntity);
            return "Paciente alterado com sucesso";
        }
        return "Não foi possível alterar os dados";
    }

    @Override
    public List<PacienteEntity> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<PacienteEntity> buscarPorId(Long id){
        return pacienteRepository.findById(id);
    }

    @Override
    public void excluir(Long id) {
        boolean excluiu = pacienteRepository.findById(id).isPresent();
        if(excluiu = true){
            pacienteRepository.deleteById(id);
        }
    }
}
