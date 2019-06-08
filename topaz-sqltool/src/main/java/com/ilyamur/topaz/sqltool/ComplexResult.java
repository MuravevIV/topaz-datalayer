package com.ilyamur.topaz.sqltool;

import com.google.common.collect.Lists;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ComplexResult {

    private final List<Item> content = Lists.newArrayList();

    static class Item {

        private String columnName;
        private Object content;

        public Item(String columnName, Object content) {
            this.columnName = columnName;
            this.content = content;
        }

        public String getColumnName() {
            return columnName;
        }

        public Object getContent() {
            return content;
        }
    }

    private void addItem(Item item) {
        content.add(item);
    }

    public static Mapper<ComplexResult> getMapper() {
        return new Mapper<ComplexResult>() {
            @Override
            public ComplexResult applyMapper(ResultSet resultSet) throws SQLException {
                ComplexResult complexResult = new ComplexResult();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int pos = 1; pos <= columnCount; pos++) {
                    String columnName = metaData.getColumnName(pos);
                    Object content = resultSet.getObject(pos);
                    complexResult.addItem(new Item(columnName, content));
                }
                return complexResult;
            }
        };
    }

    private Object getObject(String columnName) {
        return content.stream()
                .filter(it -> it.getColumnName().equalsIgnoreCase(columnName))
                .findFirst()
                .map(Item::getContent).orElse(null);
    }

    public Integer getInt(String columnName) {
        return (Integer) getObject(columnName);
    }

    public String getString(String columnName) {
        return (String) getObject(columnName);
    }
}
