package pe.edu.pucp.tel131lab9.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.pucp.tel131lab9.dao.PostDao;

import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet",""})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;

        PostDao postDao = new PostDao();

        request.setAttribute("posts", postDao.listPosts());
        view = request.getRequestDispatcher("home.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        PostDao postDao = new PostDao();
        switch (action){
            case "search":
                String textoBuscar = req.getParameter("textoBuscar");
                if (textoBuscar == null) {
                    resp.sendRedirect("");
                } else {
                    req.setAttribute("textoBusqueda", textoBuscar);
                    req.setAttribute("listaPost", postDao.searchPost(textoBuscar));
                    RequestDispatcher view = req.getRequestDispatcher("home.jsp");
                    view.forward(req, resp);
                }

                break;
        }
    }
}
