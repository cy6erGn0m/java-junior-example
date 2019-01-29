package com.levelp.example.web;

import com.levelp.example.*;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/add-user")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddUserPageBean bean = new AddUserPageBean("admin", "", "");
        req.setAttribute("bean", bean);
        req.getRequestDispatcher("/pages/add-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String kind = req.getParameter("kind");
        String login = req.getParameter("login");

        if (kind == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (login == null || login.isEmpty()) {
            AddUserPageBean bean = new AddUserPageBean(kind, login, "Login empty");
            req.setAttribute("bean", bean);
            req.getRequestDispatcher("/pages/add-user.jsp").forward(req, resp);
            return;
        }

        EntityManager em = (EntityManager) req.getServletContext().getAttribute("em");

        User user;

        switch (kind) {
            case "admin":
                user = new Admin(login);
                break;
            case "client":
                user = new Client(login);
                break;
            case "engineer":
                user = new Engineer(login);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
        }

        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

        resp.sendRedirect("/");
    }
}
