package com.levelp.example.web;

import com.levelp.example.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class IndexController {
    @Autowired
    private UsersDAO users;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String indexPage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = authentication == null
                || authentication.getPrincipal() instanceof String
                ? null : (User) authentication.getPrincipal();

        String currentUserName = currentUser == null ? "" : currentUser.getUsername();

        IndexPageBean bean = new IndexPageBean(new Date(),
                users.listUsers(),
                currentUserName);

        model.addAttribute("bean", bean);

        return "index";
    }

    public void setUsers(UsersDAO users) {
        this.users = users;
    }
}
