package com.ilyamur.topaz.sqltool;

import com.google.common.collect.Lists;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static class SqlParser {

        Pattern PATTERN = Pattern.compile("<<([^>]+)>>");

        public ParseResult parse(String sql, Param ... params) {
            List<Param> parserParams = Lists.newArrayList();

            Matcher m = PATTERN.matcher(sql);

            StringBuffer sb = new StringBuffer();
            while(m.find()) {
                String paramName = m.group(1);

                Optional<Param> optParam = Arrays.stream(params).filter(p -> p.getName().equals(paramName)).findFirst();

                if (!optParam.isPresent()) {
                    throw new RuntimeException("Parameter expected but not found: " + paramName);
                }
                parserParams.add(optParam.get());
                m.appendReplacement(sb, "?");
            }
            m.appendTail(sb);

            return new ParseResult(sb.toString(), parserParams);
        }

        //
    }

    public static class ParseResult {

        private String sql;
        private List<Param> params;

        public ParseResult(String sql, List<Param> params) {
            this.sql = sql;
            this.params = params;
        }

        public String getSql() {
            return sql;
        }

        public List<Param> getParams() {
            return params;
        }
    }
}
