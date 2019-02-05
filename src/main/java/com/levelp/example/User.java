package com.levelp.example;

import javax.persistence.*;

import static com.levelp.example.User.FIND_BY_LOGIN_QUERY;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "KIND", length = 2, discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
@NamedQueries(
        @NamedQuery(name = FIND_BY_LOGIN_QUERY, query = "from User where login = :lll")
)
public class User {
    public static final String FIND_BY_LOGIN_QUERY = "findByLogin";

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String login;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
