//package com.odontologica.clinica.service.impl;
//
//import com.odontologica.clinica.repository.IDentistaRepository;
//import com.odontologica.clinica.entity.PacienteEntity;
//import com.odontologica.clinica.service.IClinicaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PacienteServiceImpl implements IClinicaService {
//
//    @Autowired
//    private IDentistaRepository pacienteRepository;
//
////    public PacienteEntity salvar(PacienteEntity pacienteEntity) throws SQLException {
////        return (PacienteEntity) pacienteRepository.salvar(pacienteEntity);
////    }
////
////    public  void alterar(PacienteEntity pacienteEntity) throws SQLException {
////        pacienteRepository.alterar(pacienteEntity);
////    }
//
//    @Override
//    public Object salvar(Object o) throws SQLException {
//        return pacienteRepository.salvar(o);
//    }
//
//    @Override
//    public void alterar(Object o) throws SQLException {
//        pacienteRepository.alterar(o);
//    }
//
//    public List<PacienteEntity> buscarTodos() throws SQLException {
//        return pacienteRepository.buscarTodos();
//    }
//
//    public Optional<PacienteEntity> buscarPorId(int id) throws SQLException {
//        return pacienteRepository.buscarPorId(id);
//    }
//
//    public void excluir(int id) throws SQLException {
//        pacienteRepository.excluir(id);
//    }
//}
