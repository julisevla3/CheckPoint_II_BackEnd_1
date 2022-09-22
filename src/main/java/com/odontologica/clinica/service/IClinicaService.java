package com.odontologica.clinica.service;

import com.odontologica.clinica.exceptions.BadRequestException;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IClinicaService<T> {

    public T salvar(T t);

    public String alterar(T t);

    public List<T> buscarTodos();

    public Optional<T> buscarPorId(Long id);

    public void excluir(Long id);
}
