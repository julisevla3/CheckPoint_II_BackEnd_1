package com.clinica.odontologica.dao.impl;


import com.clinica.odontologica.dao.ConfiguracaoJDBC;
import com.clinica.odontologica.dao.IDao;
import com.clinica.odontologica.model.Paciente;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

@Configuration
public class PacienteDAOH2 implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger log = getLogger(PacienteDAOH2.class);
    @Override
    public Paciente salvar(Paciente paciente) throws SQLException {
        log.info("Abrindo conexão");
        String SQLInsert = String.format("INSERT INTO paciente (nome, sobrenome, endereco, rg, dataAlta)"  +
                        " VALUES ('%s', '%s', '%s', '%s', '%s')", paciente.getNome(), paciente.getSobrenome(), paciente.getEndereco(),
                paciente.getRg(), paciente.getDataAlta().getYear() + "-" + paciente.getDataAlta().getMonth() + "-" + paciente.getDataAlta().getDay());
        Connection connection = null;

        try {
            log.info("Salvando paciente: " + paciente.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC();
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                paciente.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            log.error("Erro ao inserir o paciente: " + e.getMessage());
        }finally {
            log.info("Fechando a conexão");
            connection.close();
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() throws SQLException {
        List<Paciente> pacientes = new ArrayList();

        String sql = String.format("SELECT id, nome, sobrenome, rg, dataAlta FROM paciente");
        Connection connection = null;

        try {
            log.info("Buscando os pacientes");
            configuracaoJDBC = new ConfiguracaoJDBC();
            connection = configuracaoJDBC.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                pacientes.add(new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4)));
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao tentar alterar dados do paciente");
        }finally {
            log.info("Fechando a conexão com o banco");
            connection.close();
        }
    }



    @Override
    public void alterar(Paciente paciente) throws SQLException {
        String SQLUpdate = String.format("UPDATE paciente set nome, sobrenome, endereco, rg, dataAlta = '%s', '%s', '%s', '%s', '%s'" +
                paciente.getNome(), paciente.getSobrenome(), paciente.getEndereco(), paciente.getRg(), paciente.getDataAlta());
        Connection connection = null;

        try {
            log.info("Alterando os dados do paciente" + paciente.getId());
            configuracaoJDBC = new ConfiguracaoJDBC();
            connection = configuracaoJDBC.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(SQLUpdate);

        }catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao tentar alterar dados do paciente");
        }finally {
            log.info("Fechando a conexão com o banco");
            connection.close();
        }
    }

    @Override
    public Optional<Paciente> buscarPorId(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {
        log.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String SQLDelete = String.format("DELETE FROM paciente where id = %s ", id);

        try {
            configuracaoJDBC = new ConfiguracaoJDBC();
            connection = configuracaoJDBC.getConnection();
            log.debug("Excluindo paciente com id: " + id);
            stmt = connection.createStatement();
            stmt.execute(SQLDelete);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error ao tentar deletar paciente: " + e.getMessage());
        }finally {
            log.debug("Fechando conexão com o banco");
            connection.close();
        }

    }
}