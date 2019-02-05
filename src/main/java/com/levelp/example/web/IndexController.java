package com.levelp.example.web;

import com.levelp.example.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class IndexController {
    @Autowired
    private UsersDAO users;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String indexPage(ModelMap model) {
        IndexPageBean bean = new IndexPageBean(new Date(), users.listUsers());

        model.addAttribute("bean", bean);

        return "index";
    }

    public void setUsers(UsersDAO users) {
        this.users = users;
    }
}
