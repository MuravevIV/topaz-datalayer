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
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class DatabaseTransactionTest {

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
    public void testTransactionExecute() throws SQLException {

        Transaction transaction = database.startTransaction();

        Long one = transaction.execute("SELECT 1 FROM dual").asSingle(Long.class);

        assertEquals(1L, (long) one);
    }
}
