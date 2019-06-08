package com.ilyamur.topaz.sqltool;

import java.util.List;

public class ParseResult {

    private String sql;
    private List<Param> params;

    public ParseResult(String sql, List<Param> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public List<Param> getParams() {
        return params;
    }
}
