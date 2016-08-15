package com.ilyamur.topaz.datalayer.core.entity;

import com.google.common.base.Objects;

import java.time.LocalDate;
import java.util.Set;

public class User {

    private Long id;
    private String login;
    private LocalDate birthday;
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) &&
                Objects.equal(login, user.login) &&
                Objects.equal(birthday, user.birthday) &&
                Objects.equal(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, login, birthday, roles);
    }
}
