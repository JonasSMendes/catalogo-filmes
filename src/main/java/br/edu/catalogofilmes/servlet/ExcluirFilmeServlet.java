package br.edu.catalogofilmes.servlet;

import br.edu.catalogofilmes.dao.FilmeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/excluir")
public class ExcluirFilmeServlet extends HttpServlet {

    private final FilmeDAO filmeDAO = new FilmeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            filmeDAO.excluir(id);
            response.sendRedirect(request.getContextPath() + "/filmes");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir filme", e);
        }
    }
}
