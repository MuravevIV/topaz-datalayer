package com.ilyamur.topaz.sqltool;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlParser {

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
