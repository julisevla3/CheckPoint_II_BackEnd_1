package com.odontologica.clinica.service;

import com.odontologica.repository.IRepository;
import com.odontologica.clinica.entity.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    IRepository<PacienteEntity> pacienteDaoH2;

    public PacienteEntity salvar(PacienteEntity pacienteEntity) throws SQLException {
        return pacienteDaoH2.salvar(pacienteEntity);
    }

    public  void alterar(PacienteEntity pacienteEntity) throws SQLException {
        pacienteDaoH2.alterar(pacienteEntity);
    }

    public List<PacienteEntity> buscarTodos() throws SQLException {
        return pacienteDaoH2.buscarTodos();
    }

    public Optional<PacienteEntity> buscarPorId(int id) throws SQLException {
        return pacienteDaoH2.buscarPorId(id);
    }

    public void excluir(int id) throws SQLException {
        pacienteDaoH2.excluir(id);
    }
}
