package com.ilyamur.topaz.datalayer.core.entity;

import com.google.common.base.Objects;

public class Role {

    public static final Role REGISTERED_USER;
    public static final Role ADMIN;

    static {

        REGISTERED_USER = new Role();
        REGISTERED_USER.setId(0L);
        REGISTERED_USER.setName("REGISTERED_USER");

        ADMIN = new Role();
        ADMIN.setId(1L);
        ADMIN.setName("ADMIN");
    }

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equal(id, role.id) &&
                Objects.equal(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}
