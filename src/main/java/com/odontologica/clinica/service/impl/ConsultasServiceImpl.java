package com.odontologica.clinica.service.impl;

import com.odontologica.clinica.entity.ConsultasEntity;
import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.repository.IConsultasRepository;
import com.odontologica.clinica.service.IClinicaService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultasServiceImpl implements IClinicaService<ConsultasEntity> {
    private static final Logger logger = Logger.getLogger(ConsultasServiceImpl.class);
    private final IConsultasRepository consultasRepository;
    private PacienteEntity pacienteEntity;
    private DentistaEntity dentistaEntity;

    public ConsultasServiceImpl(IConsultasRepository consultasRepository) {
        this.consultasRepository = consultasRepository;
    }

    @Override
    @Transactional
    public ConsultasEntity salvar(ConsultasEntity consultasEntity) {
//        logger.info("Salvando consulta");
        consultasRepository.save(consultasEntity);
        return consultasEntity;
    }

    @Override
    public String alterar(ConsultasEntity consultasEntity) {
        if(consultasEntity != null && consultasRepository.findById(consultasEntity.getId()).isPresent()){
//            logger.info("Alterando dados da consulta");
            consultasRepository.saveAndFlush(consultasEntity);
            return "Consulta alterado com sucesso!";
        }
//        logger.error("Dados da consulta não foi alterado!");
        return "Não foi possível alterar os dados";
    }

    @Override
    public List<ConsultasEntity> buscarTodos() {
        return consultasRepository.findAll();
    }

    @Override
    public Optional<ConsultasEntity> buscarPorId(Long id) {
        return consultasRepository.findById(id);
    }

    @Override
    public void excluir(Long id) {
        boolean excluiu = consultasRepository.findById(id).isPresent();
        if(excluiu = true) {
            consultasRepository.deleteById(id);
        }
    }
}
