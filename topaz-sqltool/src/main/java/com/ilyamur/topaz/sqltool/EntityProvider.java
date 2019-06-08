package com.ilyamur.topaz.sqltool;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EntityProvider {

    public <T> List<T> getEntities(DataSource dataSource, String sql, Mapper<T> mapper) throws SQLException {
        List<T> entities = Lists.newArrayList();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        T entityDual = mapper.applyMapper(resultSet);
                        entities.add(entityDual);
                    }
                }
            }
        }
        return entities;
    }
}
