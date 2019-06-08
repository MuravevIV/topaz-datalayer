package com.ilyamur.topaz.sqltool;

import com.ilyamur.topaz.sqltool.entity.BillEntity;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class DatabaseExecutionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testExecuteAsMany() throws SQLException {
        Database mainDatabase = new Database(dataSource);
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills";

        List<BillEntity> bills = mainDatabase.execute(sql).asMany(BillEntity.class);

        assertEquals(1, bills.size());
    }

    @Test
    public void testExecuteAsSingle() throws SQLException {
        Database mainDatabase = new Database(dataSource);
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills";

        BillEntity bill = mainDatabase.execute(sql).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecuteWithUselessParam() throws SQLException {
        Database mainDatabase = new Database(dataSource);
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills";

        BillEntity bill = mainDatabase.execute(sql, Param.of("bill_no", "38001014")).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Ignore // todo unignore
    @Test
    public void testExecuteWithParam() throws SQLException {
        Database mainDatabase = new Database(dataSource);
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no = <<bill_no>>";

        BillEntity bill = mainDatabase.execute(sql, Param.of("bill_no", "38001014")).asSingle(BillEntity.class);

        assertNotNull(bill);
    }
}
