package com.ilyamur.topaz.sqltool;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class DatabaseUpdateTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private DataSource dataSource;

    private Database database;

    @Before
    public void before() {
        database = new Database(dataSource);
    }

    @Test
    public void testUpdate_thenSelect() {

        UpdateResult updateResult = database.update("INSERT INTO test_1 (dummy) VALUES ('example_1')");

        String text = database.execute("SELECT dummy FROM test_1").asSingle(String.class);

        assertEquals(1, updateResult.getCount());
        assertEquals("example_1", text);
    }

    @Test
    public void testUpdate_whenParamDefined() {

        UpdateResult updateResult = database
                .update("INSERT INTO test_2 (dummy) VALUES (<<text>>)", Param.of("text", "example_2"));

        String text = database.execute("SELECT dummy FROM test_2").asSingle(String.class);

        assertEquals(1, updateResult.getCount());
        assertEquals("example_2", text);
    }
}
