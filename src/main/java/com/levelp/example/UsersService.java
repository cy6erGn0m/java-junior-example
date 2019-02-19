package com.levelp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersDAO users;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();

        if ("root".equals(username)) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new org.springframework.security.core.userdetails.User(
                    username, encoder.encode("123"), roles
            );
        }

        try {
            User user = users.findUser(username);

            if (user instanceof Admin) {
                roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (user instanceof Engineer) {
                roles.add(new SimpleGrantedAuthority("ROLE_ENGINEER"));
            } else if (user instanceof Client) {
                roles.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
            }

            return new org.springframework.security.core.userdetails.User(
                    username, user.getEncryptedPassword(), roles
            );
        } catch (NoResultException notFound) {
            throw new UsernameNotFoundException("User " + username + " not found", notFound);
        }
    }
}
