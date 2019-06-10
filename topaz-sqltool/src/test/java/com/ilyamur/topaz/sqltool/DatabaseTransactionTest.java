package com.ilyamur.topaz.sqltool;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class DatabaseTransactionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private DataSource dataSource;

    @Mock
    private DataSource mockDataSource;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransactionExecute_whenNotClosed() throws SQLException {

        Connection connection = dataSource.getConnection();
        when(mockDataSource.getConnection()).thenReturn(connection);
        Database database = new Database(mockDataSource);

        Transaction transaction = database.startTransaction();
        Long one = transaction.execute("SELECT 1 FROM dual").asSingle(Long.class);

        assertEquals(1L, (long) one);
        assertFalse(connection.isClosed());
    }

    @Test
    public void testTransactionExecute_whenClosed() throws SQLException {

        Connection connection = dataSource.getConnection();
        when(mockDataSource.getConnection()).thenReturn(connection);
        Database database = new Database(mockDataSource);

        try (Transaction transaction = database.startTransaction()) {
            Long one = transaction.execute("SELECT 1 FROM dual").asSingle(Long.class);

            assertEquals(1L, (long) one);
        }
        assertTrue(connection.isClosed());
    }
}
