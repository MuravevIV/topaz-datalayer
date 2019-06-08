package com.ilyamur.topaz.sqltool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class DSketchTest {

    @Autowired
    private DataSource dataSource;

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

    class EntityDual {

        Integer x;

        public void setX(Integer x) {
            this.x = x;
        }
    }


    @Test
    public void test_() throws SQLException {

        String sql = "SELECT * FROM dual";

        Mapper<EntityDual> mapper = new Mapper<EntityDual>() {

            @Override
            EntityDual applyMapper(ResultSet resultSet) throws SQLException {
                EntityDual entityDual = new EntityDual();
                entityDual.setX(resultSet.getInt("x"));
                return entityDual;
            }
        };

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    EntityDual entityDual = mapper.applyMapper(resultSet);
                    System.out.println(entityDual);
                }
            }
        }
    }
}
