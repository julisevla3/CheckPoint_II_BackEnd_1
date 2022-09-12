package com.odontologica.clinica.service;

import com.odontologica.repository.IRepository;
import com.odontologica.clinica.entity.DentistaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    IRepository<DentistaEntity> dentistaDaoH2;

    public DentistaEntity salvar(DentistaEntity dentistaEntity) throws SQLException {
        return dentistaDaoH2.salvar(dentistaEntity);
    }

    public  void alterar(DentistaEntity dentistaEntity) throws SQLException {
        dentistaDaoH2.alterar(dentistaEntity);
    }

    public List<DentistaEntity> buscarTodos() throws SQLException{
        return dentistaDaoH2.buscarTodos();
    }

    public Optional<DentistaEntity> buscarPorId(int id) throws SQLException {
        return dentistaDaoH2.buscarPorId(id);
    }


    public void excluir(int id) throws SQLException {
        dentistaDaoH2.excluir(id);
    }
}
