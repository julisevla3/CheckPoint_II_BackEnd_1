package com.odontologica.repository.impl;

import com.odontologica.repository.ConfiguracaoJDBC;
import com.odontologica.repository.IRepository;
import com.odontologica.clinica.entity.PacienteEntity;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

@Configuration
public class PacienteDAOH2 implements IRepository<PacienteEntity> {

    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger log = getLogger(PacienteDAOH2.class);
    @Override
    public PacienteEntity salvar(PacienteEntity pacienteEntity) throws SQLException {
        log.info("Abrindo conexão");
        String SQLInsert = String.format("INSERT INTO paciente (nome, sobrenome, endereco, rg, dataAlta)"  +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')", pacienteEntity.getNome(), pacienteEntity.getSobrenome(), pacienteEntity.getEndereco(),
                pacienteEntity.getRg(),  pacienteEntity.getDataAlta().getYear() + "-" + pacienteEntity.getDataAlta().getMonth() + "-" + pacienteEntity.getDataAlta().getDay());
        Connection connection = null;

        try {
            log.info("Salvando paciente: " + pacienteEntity.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC();
            connection = configuracaoJDBC.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = stmt.getGeneratedKeys();

            if(resultSet.next()){
                pacienteEntity.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            log.error("Erro ao inserir o paciente: " + e.getMessage());
        }finally {
            log.info("Fechando a conexão");
            connection.close();
        }
        return pacienteEntity;
    }

    @Override
    public void alterar(PacienteEntity pacienteEntity) throws SQLException {
        String SQLUpdate = String.format("UPDATE paciente set nome = '%s', sobrenome = '%s', endereco = '%s', rg = '%s' where id = '%s';",
                pacienteEntity.getNome(), pacienteEntity.getSobrenome(), pacienteEntity.getEndereco(), pacienteEntity.getRg(), pacienteEntity.getId());
        Connection connection = null;

        try {
            log.info("Alterando os dados do paciente" + pacienteEntity.getId());
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
    public Optional<PacienteEntity> buscarPorId(int id) throws SQLException {
        log.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM paciente where id= %s ", id);
        PacienteEntity pacienteEntity = null;

        try {
            configuracaoJDBC = new ConfiguracaoJDBC();
            connection = configuracaoJDBC.getConnection();
            log.debug("Buscando paciente por id: " + id);
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            while (resultado.next()){
                pacienteEntity = criarObjetoPaciente(resultado);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            log.debug("Fechando a conexão com o banco");
            stmt.close();
        }
        return pacienteEntity != null ? Optional.of(pacienteEntity) : Optional.empty();
    }

    @Override
    public List<PacienteEntity> buscarTodos() throws SQLException {
        log.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = "SELECT * FROM paciente";
        List<PacienteEntity> pacienteEntities = new ArrayList<>();

        try {
            configuracaoJDBC = new ConfiguracaoJDBC();
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            log.debug("Buscando todos os pacientes cadastrados no DB");

            while(resultado.next()){
                pacienteEntities.add(criarObjetoPaciente(resultado));
            }

        }catch (SQLException  throwables){
            throwables.printStackTrace();
        }finally {
            log.debug("Fechando a conexão com o banco");
            stmt.close();
        }
        return pacienteEntities;
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

    private PacienteEntity criarObjetoPaciente(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("ID");
        String nome = resultSet.getString("nome");
        String sobrenome = resultSet.getString("sobrenome");
        String endereco = resultSet.getString("endereco");
        String rg = resultSet.getString("rg");
        Date dataAlta = resultSet.getDate("dataAlta");

        return new PacienteEntity(id, nome, sobrenome, endereco, rg, dataAlta);
    }


}
