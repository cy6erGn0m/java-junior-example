package com.levelp.example.web;

import com.levelp.example.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UsersDAO users;

    @Autowired
    public UserController(UsersDAO users) {
        this.users = users;
    }

    @GetMapping(path = "/admin/add-user")
    public String addUserForm(ModelMap modelMap) {
        AddUserPageBean bean = new AddUserPageBean("admin", "", "");
        modelMap.addAttribute("bean", bean);
        return "add-user";
    }

    @PostMapping(path = "/admin/add-user")
    @Transactional
    public String postAddUserForm(@RequestParam String kind,
                                  @RequestParam String login,
                                  ModelMap model) {
        if (kind == null) {
            return "add-user";
        }

        if (login == null || login.isEmpty()) {
            AddUserPageBean bean = new AddUserPageBean(kind, login, "Login empty");
            model.addAttribute("bean", bean);
            return "add-user";
        }

        if (users.createUser(kind, login) == null) {
            // TODO error message
            return "add-user";
        }

        return "redirect:/";
    }

    @PostMapping(path = "/admin/delete-user")
    public String delete(@RequestParam long id) {
        users.delete(id);
        return "redirect:/";
    }
}
