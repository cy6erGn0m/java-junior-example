package com.levelp.example.web;

import com.levelp.example.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/add-user")
public class AddUserController {
    private final UsersDAO users;

    @Autowired
    public AddUserController(UsersDAO users) {
        this.users = users;
    }

    @GetMapping
    public String addUserForm(ModelMap modelMap) {
        AddUserPageBean bean = new AddUserPageBean("admin", "", "");
        modelMap.addAttribute("bean", bean);
        return "add-user";
    }

    @PostMapping
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
}
