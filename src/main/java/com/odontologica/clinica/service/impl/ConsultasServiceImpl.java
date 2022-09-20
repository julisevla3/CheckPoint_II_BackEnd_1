package com.odontologica.clinica.service.impl;

import com.odontologica.clinica.entity.ConsultasEntity;
import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.repository.IConsultasRepository;
import com.odontologica.clinica.service.IClinicaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultasServiceImpl implements IClinicaService<ConsultasEntity> {

    private final IConsultasRepository consultasRepository;
    private PacienteEntity pacienteEntity;
    private DentistaEntity dentistaEntity;

    public ConsultasServiceImpl(IConsultasRepository consultasRepository) {
        this.consultasRepository = consultasRepository;
    }

    @Override
    @Transactional
    public ConsultasEntity salvar(ConsultasEntity consultasEntity) {
        consultasRepository.save(consultasEntity);
        return consultasEntity;
    }

    @Override
    public ConsultasEntity alterar(ConsultasEntity consultasEntity) {
        return consultasRepository.saveAndFlush(consultasEntity);
    }

    @Override
    public List<ConsultasEntity> buscarTodos() {
        return consultasRepository.findAll();
    }

    @Override
    public Optional<ConsultasEntity> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public void excluir(Long id) {
    }
}
