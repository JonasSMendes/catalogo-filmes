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
import java.util.Collections;
import java.util.List;

@WebServlet("/buscar")
public class BuscarFilmesServlet extends HttpServlet {

    private final FilmeDAO filmeDAO = new FilmeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String termo = request.getParameter("termo");

        try {
            List<Filme> filmes;
            if (termo == null || termo.trim().isEmpty()) {
                filmes = filmeDAO.listarTodos();
            } else {
                filmes = filmeDAO.buscarPorTituloOuDiretor(termo.trim());
            }

            request.setAttribute("filmes", filmes);
            request.setAttribute("termoBuscado", termo);
            request.getRequestDispatcher("/listar.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar filmes", e);
        }
    }
}
