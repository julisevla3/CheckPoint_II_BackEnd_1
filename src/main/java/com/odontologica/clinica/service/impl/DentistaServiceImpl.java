package com.odontologica.clinica.service.impl;

import com.odontologica.clinica.dto.DentistaDTO;
import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.service.IService;
import com.odontologica.repository.IDentistaRepository;
import com.odontologica.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DentistaServiceImpl implements IService<DentistaEntity> {

    @Autowired
    private IRepository<DentistaEntity> dentistaRepository;


    @Override
    public DentistaEntity salvar(DentistaEntity dentistaEntity) throws SQLException {
        return this.dentistaRepository.salvar(dentistaEntity);
    }

    @Override
    public void alterar(DentistaEntity dentistaEntity) throws SQLException {
        this.dentistaRepository.alterar(dentistaEntity);
    }

    @Override
    public List<DentistaEntity> buscarTodos() throws SQLException {
        return this.dentistaRepository.buscarTodos();
    }

    @Override
    public Optional<DentistaEntity> buscarPorId(int id) throws SQLException {
        return this.dentistaRepository.buscarPorId(id);
    }

    @Override
    public void excluir(int id) throws SQLException {
        this.dentistaRepository.excluir(id);
    }
}
