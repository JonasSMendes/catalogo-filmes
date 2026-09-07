package br.edu.catalogofilmes.dao;

import br.edu.catalogofilmes.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    public void inserir(Filme filme) throws SQLException {

        String sql = "INSERT INTO filmes (titulo, diretor, ano, genero, sinopse) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getDiretor());
            stmt.setInt(3, filme.getAno());
            stmt.setString(4, filme.getGenero());
            stmt.setString(5, filme.getSinopse());

            stmt.executeUpdate();
        }
    }


    public List<Filme> listarTodos() throws SQLException {
        String sql = "SELECT * FROM filmes ORDER BY titulo";
        List<Filme> filmes = new ArrayList<>();

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                filmes.add(montarFilme(rs));
            }
        }
        return filmes;
    }

    public Filme buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM filmes WHERE id = ?";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarFilme(rs);
                }
            }
        }
        return null;
    }


    public List<Filme> buscarPorTituloOuDiretor(String termo) throws SQLException {
        String sql = "SELECT * FROM filmes WHERE titulo LIKE ? OR diretor LIKE ? ORDER BY titulo";
        List<Filme> filmes = new ArrayList<>();
        String like = "%" + termo + "%";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, like);
            stmt.setString(2, like);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    filmes.add(montarFilme(rs));
                }
            }
        }
        return filmes;
    }

    public void atualizar(Filme filme) throws SQLException {
        String sql = "UPDATE filmes SET titulo = ?, diretor = ?, ano = ?, genero = ?, sinopse = ? WHERE id = ?";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getDiretor());
            stmt.setInt(3, filme.getAno());
            stmt.setString(4, filme.getGenero());
            stmt.setString(5, filme.getSinopse());
            stmt.setInt(6, filme.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM filmes WHERE id = ?";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Filme montarFilme(ResultSet rs) throws SQLException {
        Filme f = new Filme();
        f.setId(rs.getInt("id"));
        f.setTitulo(rs.getString("titulo"));
        f.setDiretor(rs.getString("diretor"));
        f.setAno(rs.getInt("ano"));
        f.setGenero(rs.getString("genero"));
        f.setSinopse(rs.getString("sinopse"));
        return f;
    }

}
