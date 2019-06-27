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
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class DatabaseBatchUpdateTest {

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
    public void testUpdate() {
        BillEntity billA = new BillEntity();
        billA.setIdBill(10);
        billA.setIdAgree(1);
        billA.setBillNo("38001014");
        billA.setBillSum(new BigDecimal("100"));

        BillEntity billB = new BillEntity();
        billB.setIdBill(20);
        billB.setIdAgree(2);
        billB.setBillNo("38001015");
        billB.setBillSum(new BigDecimal("200"));

        List<BillEntity> bills = Arrays.asList(billA, billB);
        String sql = "INSERT INTO test_3 (id_agree, id_bill, bill_no, bill_sum) " +
                "VALUES (<<id_agree>>, <<id_bill>>, <<bill_no>>, <<bill_sum>>)";

        BatchUpdateResult batchUpdateResult = database.batchUpdate(sql).on(bills, bill -> Arrays.asList(
                Param.of("id_agree", bill.getIdAgree()),
                Param.of("id_bill", bill.getIdBill()),
                Param.of("bill_no", bill.getBillNo()),
                Param.of("bill_sum", bill.getBillSum())
        ));

        List<BillEntity> billsActual = database.execute("SELECT id_agree, id_bill, bill_no, bill_sum FROM test_3 ORDER BY id_agree, id_bill")
                .asMany(BillEntity.class);

        assertEquals(Arrays.asList(1, 1), batchUpdateResult.getUpdateCounts());
        assertEquals(bills, billsActual);
    }
}
