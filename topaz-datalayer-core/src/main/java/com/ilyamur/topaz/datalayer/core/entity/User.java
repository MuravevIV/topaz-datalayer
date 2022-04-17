package com.ilyamur.topaz.datalayer.core.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.ilyamur.topaz.datalayer.core.converter.LocalDateConverter;
import com.ilyamur.topaz.datalayer.core.service.UserMailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

/**
 * todo:
 * Resolve:
 * ajc: this affected type is not exposed to the weaver: com.ilyamur.topaz.datalayer.core.entity.User [Xlint:typeNotExposedToWeaver]
 */
@Entity
@Table(name = "user")
@Configurable
public class User {

    @Transient
    @Autowired(required = false)
    private UserMailingService userMailingService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 80)
    private String login;

    @NotNull
    @Size(min = 5, max = 80)
    private String email;

    @NotNull
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birthday;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_role", nullable = false, updatable = false))
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void sendEmail(String text) {
        userMailingService.sendEmail(getEmail(), text);
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("login", login)
                .add("birthday", birthday)
                .toString();
    }
}
