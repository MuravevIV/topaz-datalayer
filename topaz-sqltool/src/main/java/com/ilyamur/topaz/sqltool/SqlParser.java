package com.ilyamur.topaz.sqltool;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class SqlParser {

    private static final Pattern SIMPLE_PATTERN = Pattern.compile("<<([^>]+)>>");
    private static final Pattern LIST_PATTERN = Pattern.compile("<:<([^>]+)>:>");

    public ParseResult parse(String sql, Param... params) {
        List<Param> inputParams = Arrays.asList(params);
        List<Param> parserParams = Lists.newArrayList();
        String sqlPhase1 = replaceSimpleParams(sql, inputParams, parserParams);
        String sqlPhase2 = replaceCollectionParams(sqlPhase1, inputParams, parserParams);

        StringJoiner notBoundParamNames = new StringJoiner(", ");
        Set<String> parserParamNames = parserParams.stream().map(Param::getName).collect(Collectors.toSet());
        for (Param inputParam : inputParams) {
            if (!parserParamNames.contains(inputParam.getName())) {
                notBoundParamNames.add(inputParam.getName());
            }
        }
        if (!notBoundParamNames.toString().isEmpty()) {
            throw new RuntimeException("Parameter(s) not bound: " + notBoundParamNames.toString());
        }

        return new ParseResult(sqlPhase2, parserParams);
    }

    private String replaceSimpleParams(String sql, List<Param> inputParams, List<Param> parserParams) {
        Matcher m = SIMPLE_PATTERN.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String paramName = m.group(1);
            Optional<Param> optParam = inputParams.stream().filter(p -> p.getName().equals(paramName)).findFirst();
            if (!optParam.isPresent()) {
                throw new RuntimeException("Parameter expected but not found: " + paramName);
            }
            parserParams.add(optParam.get());
            m.appendReplacement(sb, "?");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private String replaceCollectionParams(String sql, List<Param> inputParams, List<Param> parserParams) {
        Matcher m = LIST_PATTERN.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String paramName = m.group(1);
            Optional<Param> optParam = inputParams.stream().filter(p -> p.getName().equals(paramName)).findFirst();
            if (!optParam.isPresent()) {
                throw new RuntimeException("Parameter expected but not found: " + paramName);
            }
            Param param = optParam.get();
            Object paramValue = param.getValue();
            if (!(paramValue instanceof Collection<?>)) {
                throw new RuntimeException(); // todo specify
            }
            List<?> paramList = (List<?>) paramValue;
            StringJoiner stringJoiner = new StringJoiner(", ");
            for (Object paramPart : paramList) {
                stringJoiner.add("?");
                parserParams.add(Param.of(paramName, paramPart));
            }
            m.appendReplacement(sb, stringJoiner.toString());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
