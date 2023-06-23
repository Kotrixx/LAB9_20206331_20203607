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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view;
        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");

        PostDao postDao = new PostDao();


        switch (action){
            case "lista":
                req.setAttribute("posts", postDao.listPosts());
                view = req.getRequestDispatcher("home.jsp");
                view.forward(req, resp);
                break;
            case "new":
                view = req.getRequestDispatcher("employees/newPost.jsp");
                view.forward(req, resp);
        }


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
                    req.setAttribute("posts", postDao.searchPost(textoBuscar));
                    RequestDispatcher view = req.getRequestDispatcher("home.jsp");
                    view.forward(req, resp);
                }

                break;
        }
    }
}
