package com.ilyamur.topaz.sqltool;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityProvider {

    public <T> List<T> getEntities(DataSource dataSource, Mapper<T> mapper, String sql, List<Param> paramList) throws SQLException {
        List<T> entities = Lists.newArrayList();
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParams(preparedStatement, paramList);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T entityDual = mapper.applyMapper(resultSet);
                    entities.add(entityDual);
                }
            }
        }
        return entities;
    }

    public int updateEntities(DataSource dataSource, String sql, List<Param> paramList) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                setParams(preparedStatement, paramList);
                return preparedStatement.executeUpdate();
            }
        }
    }

    public List<Integer> updateEntitiesBatch(DataSource dataSource, String sql, Iterator<List<Param>> paramListIterator) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                while (paramListIterator.hasNext()) {
                    List<Param> paramList = paramListIterator.next();
                    setParams(preparedStatement, paramList);
                    preparedStatement.addBatch();
                }
                int[] updateCountArray = preparedStatement.executeBatch();
                return Arrays.stream(updateCountArray)
                        .boxed()
                        .collect(Collectors.toList());
            }
        }
    }

    private void setParams(PreparedStatement preparedStatement, List<Param> paramList) throws SQLException {
        for (int i = 0; i < paramList.size(); i++) {
            Param param = paramList.get(i);
            preparedStatement.setObject(i + 1, param.getValue());
        }
    }
}
