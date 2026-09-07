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
    import java.util.List;


@WebServlet("/filmes")
public class ListarFilmesServlet extends HttpServlet {

    private final FilmeDAO filmeDAO = new FilmeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<Filme> filmes = filmeDAO.listarTodos();
            req.setAttribute("filmes", filmes);
            req.getRequestDispatcher("/listar.jsp").forward(req, resp);
        }catch (SQLException e) {
            throw new ServletException("Erro ao tentar listar os filmes", e);
        }
    }
}
