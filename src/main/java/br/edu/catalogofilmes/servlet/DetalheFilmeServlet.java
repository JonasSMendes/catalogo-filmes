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

@WebServlet("detalhe")
public class DetalheFilmeServlet extends HttpServlet {

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
            request.getRequestDispatcher("/detalhe.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar filme", e);
        }
    }
}
