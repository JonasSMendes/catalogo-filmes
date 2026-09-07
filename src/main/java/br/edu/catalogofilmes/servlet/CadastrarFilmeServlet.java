package br.edu.catalogofilmes.servlet;

import br.edu.catalogofilmes.dao.FilmeDAO;
import br.edu.catalogofilmes.model.Filme;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cadastrar")
public class CadastrarFilmeServlet extends HttpServlet {

    private final FilmeDAO filmeDAO = new FilmeDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String titulo = request.getParameter("titulo");
        String diretor = request.getParameter("diretor");
        String anoStr = request.getParameter("ano");
        String genero = request.getParameter("genero");
        String sinopse = request.getParameter("sinopse");


        if (titulo == null || titulo.trim().isEmpty() ||
                diretor == null || diretor.trim().isEmpty() ||
                anoStr == null || anoStr.trim().isEmpty()) {

            request.setAttribute("erro", "Título, diretor e ano são obrigatórios.");
            request.getRequestDispatcher("/form.jsp").forward(request, response);
            return;
        }

        try {
            int ano = Integer.parseInt(anoStr);

            Filme filme = new Filme();
            filme.setTitulo(titulo.trim());
            filme.setDiretor(diretor.trim());
            filme.setAno(ano);
            filme.setGenero(genero != null ? genero.trim() : "");
            filme.setSinopse(sinopse != null ? sinopse.trim() : "");

            filmeDAO.inserir(filme);

            response.sendRedirect(request.getContextPath() + "/filmes");

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "O ano deve ser um número válido.");
            request.getRequestDispatcher("/form.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erro ao cadastrar filme", e);
        }
    }
}
