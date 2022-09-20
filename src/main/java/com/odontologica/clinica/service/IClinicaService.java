package com.odontologica.clinica.service;

import java.util.List;
import java.util.Optional;

public interface IClinicaService<T> {

    public T salvar(T t);

    public T alterar(T t);

    public List<T> buscarTodos();

    public Optional<T> buscarPorId(Long id);

    public void excluir(Long id);
}
