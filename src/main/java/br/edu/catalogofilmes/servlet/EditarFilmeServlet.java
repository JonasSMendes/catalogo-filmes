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

@WebServlet("editar")
public class EditarFilmeServlet extends HttpServlet {

    private final FilmeDAO filmeDAO = new FilmeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Filme filme = filmeDAO.buscarPorId(id);

            if (filme == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Filme não encontrado.");
                return;
            }

            request.setAttribute("filme", filme);
            request.getRequestDispatcher("/form.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar filme", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String diretor = request.getParameter("diretor");
        String anoStr = request.getParameter("ano");
        String genero = request.getParameter("genero");
        String sinopse = request.getParameter("sinopse");

        try {
            int id = Integer.parseInt(idStr);
            int ano = Integer.parseInt(anoStr);

            Filme filme = new Filme();
            filme.setId(id);
            filme.setTitulo(titulo.trim());
            filme.setDiretor(diretor.trim());
            filme.setAno(ano);
            filme.setGenero(genero != null ? genero.trim() : "");
            filme.setSinopse(sinopse != null ? sinopse.trim() : "");

            filmeDAO.atualizar(filme);
            response.sendRedirect(request.getContextPath() + "/filmes");

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Dados inválidos.");
            request.getRequestDispatcher("/form.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar filme", e);
        }
    }
}
