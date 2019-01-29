package com.levelp.example.web;

import com.levelp.example.UsersDAO;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = (EntityManager) req.getServletContext().getAttribute("em");
        UsersDAO dao = new UsersDAO(em);

        IndexPageBean bean = new IndexPageBean(new Date(), dao.listUsers());

        req.setAttribute("bean", bean);
        req.getRequestDispatcher("/pages/index.jsp").forward(req, resp);
    }
}
