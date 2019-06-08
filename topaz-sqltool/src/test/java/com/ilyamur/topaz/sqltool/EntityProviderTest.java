package com.ilyamur.topaz.sqltool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class EntityProviderTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityProvider entityProvider;

    class EntityDual {

        Integer x;

        public void setX(Integer x) {
            this.x = x;
        }
    }


    @Test
    public void testGetEntities() throws SQLException {

        String sql = "SELECT * FROM dual";
        Mapper<EntityDual> mapper = new Mapper<EntityDual>() {
            @Override
            EntityDual applyMapper(ResultSet resultSet) throws SQLException {
                EntityDual entityDual = new EntityDual();
                entityDual.setX(resultSet.getInt("x"));
                return entityDual;
            }
        };

        List<EntityDual> entityDualList = entityProvider.getEntities(dataSource, sql, mapper);

        assertEquals(1, entityDualList.size());
        assertEquals(1, (int) entityDualList.get(0).x);
    }
}
