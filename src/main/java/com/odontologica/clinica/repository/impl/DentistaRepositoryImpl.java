//package com.odontologica.clinica.repository.impl;
//
//import com.odontologica.clinica.repository.ConfiguracaoJDBC;
//import com.odontologica.clinica.repository.IDentistaRepository;
//import com.odontologica.clinica.entity.DentistaEntity;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.apache.logging.log4j.LogManager.getLogger;
//
//@Repository
//public class DentistaRepositoryImpl implements IDentistaRepository<DentistaEntity> {
//
//    private ConfiguracaoJDBC configuracaoJDBC;
//
//    final static Logger log = getLogger(DentistaRepositoryImpl.class);
//    @Override
//    public DentistaEntity salvar(DentistaEntity dentistaEntity) throws SQLException {
//        log.info("Abrindo conexão");
//        String SQLInsert = String.format("INSERT INTO dentista (nome, sobrenome, matricula)"  +
//                        "VALUES ('%s', '%s', '%s')", dentistaEntity.getNome(), dentistaEntity.getSobrenome(), dentistaEntity.getMatricula());
//        Connection connection = null;
//
//        try {
//            log.info("Salvando dentista: " + dentistaEntity.getNome());
//            configuracaoJDBC = new ConfiguracaoJDBC();
//            connection = configuracaoJDBC.getConnection();
//            Statement stmt = connection.createStatement();
//            stmt.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);
//            ResultSet resultSet = stmt.getGeneratedKeys();
//
//            if(resultSet.next()){
//                dentistaEntity.setId(resultSet.getInt(1));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//            log.error("Erro ao tentar inserir o dentista: " + e.getMessage());
//        }finally {
//            log.info("Fechando a conexão");
//            connection.close();
//        }
//        return dentistaEntity;
//    }
//
//    @Override
//    public void alterar(DentistaEntity dentistaEntity) throws SQLException {
//        String SQLUpdate = String.format("UPDATE dentista set nome = '%s', sobrenome = '%s', matricula = '%s' where id = '%s';",
//                dentistaEntity.getNome(), dentistaEntity.getSobrenome(), dentistaEntity.getMatricula(), dentistaEntity.getId());
//
//        Connection connection = null;
//
//        try {
//            log.info("Alterando os dados do dentista" + dentistaEntity.getId());
//            configuracaoJDBC = new ConfiguracaoJDBC();
//            connection = configuracaoJDBC.getConnection();
//            Statement stmt = connection.createStatement();
//            stmt.execute(SQLUpdate);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//            log.error("Erro ao tentar alterar dados do dentista");
//        }finally {
//            log.info("Fechando a conexão com o banco");
//            connection.close();
//        }
//    }
//
//    @Override
//    public Optional<DentistaEntity> buscarPorId(int id) throws SQLException {
//        log.debug("Abrindo uma conexão no banco");
//        Connection connection = null;
//        Statement stmt = null;
//        String query = String.format("SELECT * FROM dentista where id= %s ", id);
//        DentistaEntity dentistaEntity = null;
//
//        try {
//            configuracaoJDBC = new ConfiguracaoJDBC();
//            connection = configuracaoJDBC.getConnection();
//            log.debug("Buscando dentista por id: " + id);
//            stmt = connection.createStatement();
//            ResultSet resultado = stmt.executeQuery(query);
//
//            while (resultado.next()){
//                dentistaEntity = criarObjetoDentista(resultado);
//            }
//        }catch (SQLException throwables){
//            throwables.printStackTrace();
//        }finally {
//            log.debug("Fechando a conexão com o banco");
//            stmt.close();
//        }
//        return dentistaEntity != null ? Optional.of(dentistaEntity) : Optional.empty();
//    }
//
//    @Override
//    public List<DentistaEntity> buscarTodos() throws SQLException {
//        log.debug("Abrindo uma conexão no banco");
//        Connection connection = null;
//        Statement stmt = null;
//        String query = "SELECT * FROM dentista";
//        List<DentistaEntity> dentistas = new ArrayList<>();
//
//        try {
//            configuracaoJDBC = new ConfiguracaoJDBC();
//            connection = configuracaoJDBC.getConnection();
//            stmt = connection.createStatement();
//            ResultSet resultado = stmt.executeQuery(query);
//            log.debug("Buscando todos os dentista cadastrados no DB");
//
//            while(resultado.next()){
//                dentistas.add(criarObjetoDentista(resultado));
//            }
//
//        }catch (SQLException  throwables){
//            throwables.printStackTrace();
//        }finally {
//            log.debug("Fechando a conexão com o banco");
//            stmt.close();
//        }
//        return dentistas;
//    }
//
//    @Override
//    public void excluir(int id) throws SQLException {
//        log.debug("Abrindo uma conexão no banco");
//        Connection connection = null;
//        Statement stmt = null;
//        String SQLDelete = String.format("DELETE FROM dentista where id= %s ", id);
//
//        try {
//            configuracaoJDBC = new ConfiguracaoJDBC();
//            connection = configuracaoJDBC.getConnection();
//            log.debug("Excluindo dentista com id: " + id);
//            stmt = connection.createStatement();
//            stmt.execute(SQLDelete);
//
//        }catch (SQLException throwables){
//            throwables.printStackTrace();
//        }finally {
//            log.debug("Fechando conexão com o banco");
//            connection.close();
//        }
//    }
//
//    private DentistaEntity criarObjetoDentista(ResultSet resultSet) throws SQLException {
//        Integer id = resultSet.getInt("ID");
//        String nome = resultSet.getString("nome");
//        String sobrenome = resultSet.getString("sobrenome");
//        String matricula = resultSet.getString("matricula");
//
//        return new DentistaEntity(id, nome, sobrenome, matricula);
//    }
//}
