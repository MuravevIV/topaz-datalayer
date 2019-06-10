package com.ilyamur.topaz.sqltool;

import com.ilyamur.topaz.sqltool.entity.BillEntity;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class DatabaseExecutionTest {

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
    public void testExecuteAsMany() {
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills";

        List<BillEntity> bills = database.execute(sql).asMany(BillEntity.class);

        assertEquals(2, bills.size());
    }

    @Test
    public void testExecuteAsSingle() {
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no = '38001014'";

        BillEntity bill = database.execute(sql).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecute_whenSingleExpectedButManyReturned() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(containsString("Single result is requested, but many results are returned"));

        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills";

        BillEntity bill = database.execute(sql).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecute_whenSingleExpectedButNoneReturned() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(containsString("Single result is requested, but none results are returned"));

        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE 0 = 1";

        BillEntity bill = database.execute(sql).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecute_whenOneUselessParam() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(containsString("Parameter(s) not bound: bill_no"));

        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no = '38001014'";

        BillEntity bill = database.execute(sql, Param.of("bill_no", "38001014")).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecute_whenSeveralUselessParam() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(containsString("Parameter(s) not bound: bill_no, id_agree"));

        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no = '38001014'";

        BillEntity bill = database
                .execute(sql, Param.of("bill_no", "38001014"), Param.of("id_agree", 1))
                .asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecuteWithParam() {
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no = <<bill_no>>";

        BillEntity bill = database.execute(sql, Param.of("bill_no", "38001014")).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecuteWithTwoSameParams() {
        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no = <<bill_no>> AND bill_no = <<bill_no>>";

        BillEntity bill = database.execute(sql, Param.of("bill_no", "38001014")).asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecuteWithTwoDifferentParams() {
        String sql = "SELECT <<id_agree>> AS id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no = <<bill_no>> AND bill_no = <<bill_no>>";

        BillEntity bill = database
                .execute(sql, Param.of("bill_no", "38001014"), Param.of("id_agree", 1))
                .asSingle(BillEntity.class);

        assertNotNull(bill);
    }

    @Test
    public void testExecute_whenPrimitiveResult() {
        Long one = database
                .execute("SELECT 1 FROM dual")
                .asSingle(Long.class);

        assertEquals(1, (long) one);
    }

    @Test
    public void testExecute_whenComplexResultAccessedByName() {

        ComplexResult complexResult = database
                .execute("SELECT 1 AS one, 'two' AS two FROM dual")
                .asSingleComplexResult();

        Integer one = complexResult.getInt("one");
        String two = complexResult.getString("two");

        assertEquals(1, (int) one);
        assertEquals("two", two);
    }

    @Test
    public void testExecute_whenComplexResultAccessedByPos() {

        ComplexResult complexResult = database
                .execute("SELECT 1 AS one, 'two' AS two FROM dual")
                .asSingleComplexResult();

        Integer one = complexResult.getInt(1);
        String two = complexResult.getString(2);

        assertEquals(1, (int) one);
        assertEquals("two", two);
    }

    @Test
    public void testExecute_whenMultiparameter() {

        String sql = "SELECT id_agree, id_bill, bill_no, bill_sum FROM bills WHERE bill_no IN (<:<BILL_NO_LIST>:>)";

        List<BillEntity> bills = database
                .execute(sql, Param.of("BILL_NO_LIST", Arrays.asList("38001014", "38001015")))
                .asMany(BillEntity.class);

        assertEquals(2, bills.size());
    }
}
