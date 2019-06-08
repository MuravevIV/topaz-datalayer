package com.ilyamur.topaz.sqltool;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSuiteConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class SqlParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testParse_whenPlainSql() {
        SqlParser parser = new SqlParser();

        ParseResult parseResult = parser.parse("SELECT 1 FROM dual");

        assertEquals("SELECT 1 FROM dual", parseResult.getSql());
    }

    @Test
    public void testParse_whenTailAndParamMissing() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(containsString("Parameter expected but not found: x"));

        SqlParser parser = new SqlParser();

        parser.parse("SELECT 1 FROM dual WHERE dummy = <<x>>");
    }

    @Test
    public void testParse_whenTail() {
        SqlParser parser = new SqlParser();

        ParseResult parseResult = parser.parse("SELECT 1 FROM dual WHERE dummy = <<x>>", Param.of("x", 1));

        assertEquals("SELECT 1 FROM dual WHERE dummy = ?", parseResult.getSql());
        assertEquals(1, parseResult.getParams().size());
        Param param = parseResult.getParams().get(0);
        assertEquals("x", param.getName());
        assertEquals(1, param.getValue());
    }

    @Test
    public void testParse_whenTwoParams() {
        SqlParser parser = new SqlParser();

        ParseResult parseResult = parser.parse("SELECT <<a>>, <<b>> FROM dual", Param.of("b", 2), Param.of("a", 1));

        assertEquals("SELECT ?, ? FROM dual", parseResult.getSql());
        assertEquals(2, parseResult.getParams().size());
        Param paramA = parseResult.getParams().get(0);
        assertEquals("a", paramA.getName());
        assertEquals(1, paramA.getValue());
        Param paramB = parseResult.getParams().get(1);
        assertEquals("b", paramB.getName());
        assertEquals(2, paramB.getValue());
    }
}
