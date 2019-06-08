package com.ilyamur.topaz.sqltool;

class Param {

    private String name;
    private Object value;

    public Param(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public static Param of(String name, Object value) {
        return new Param(name, value);
    }
}
