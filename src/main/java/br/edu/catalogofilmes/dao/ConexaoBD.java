package br.edu.catalogofilmes.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConexaoBD {

    private static final Properties prop = new Properties();

    static {
        try (InputStream input = ConexaoBD.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Arquivo db.properties não encontrado em src/main/resources. " +
                                "Copie db.properties.example, renomeie para db.properties e preencha suas credenciais."
                );
            }
            prop.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar db.properties", e);
        }
    }



    public static Connection getConexao() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw  new SQLException("Driver JDBC do MySQL não encontrado.", e);
        }

        String url = prop.getProperty("db.url");
        String usuario = prop.getProperty("db.usuario");
        String senha = prop.getProperty("db.senha");


        return DriverManager.getConnection(url, usuario, senha);
    }

}


