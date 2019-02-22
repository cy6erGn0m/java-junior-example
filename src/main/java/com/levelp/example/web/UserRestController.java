package com.levelp.example.web;

import com.levelp.example.User;
import com.levelp.example.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

@RestController
// = @Controller + @ResponseBody
public class UserRestController {
    private final UsersDAO users;

    @Autowired
    public UserRestController(UsersDAO users) {
        this.users = users;
    }

    @GetMapping("/api/users")
    public User userByLogin(@RequestParam String login) {
        return users.findUser(login);
    }

    @DeleteMapping("/api/users")
    public boolean deleteUser(@RequestParam String login) {
        try {
            User user = users.findUser(login);
            users.delete(user.getId());
            return true;
        } catch (NoResultException notFound) {
            return false;
        }
    }
}
