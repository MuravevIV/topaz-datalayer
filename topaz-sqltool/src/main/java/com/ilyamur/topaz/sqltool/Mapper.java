package com.ilyamur.topaz.sqltool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

abstract class Mapper<T> implements Function<ResultSet, T> {

    @Override
    public T apply(ResultSet resultSet) {
        try {
            return applyMapper(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    abstract T applyMapper(ResultSet resultSet) throws SQLException;
}
