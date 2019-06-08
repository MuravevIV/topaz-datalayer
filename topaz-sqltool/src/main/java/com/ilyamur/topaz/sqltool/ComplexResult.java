package com.ilyamur.topaz.sqltool;

import com.google.common.collect.Lists;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class ComplexResult {

    private final List<Item> content = Lists.newArrayList();

    static class Item {

        private String columnName;
        private int pos;
        private Object content;

        public Item(String columnName, int pos, Object content) {
            this.columnName = columnName;
            this.pos = pos;
            this.content = content;
        }

        public String getColumnName() {
            return columnName;
        }

        public int getPos() {
            return pos;
        }

        public Object getContent() {
            return content;
        }
    }

    private void addItem(Item item) {
        content.add(item);
    }

    private Object getObject(int pos) {
        return content.stream()
                .filter(it -> it.getPos() == pos)
                .findFirst()
                .map(Item::getContent).orElse(null);
    }

    private Object getObject(String columnName) {
        return content.stream()
                .filter(it -> it.getColumnName().equalsIgnoreCase(columnName))
                .findFirst()
                .map(Item::getContent).orElse(null);
    }

    private <T> T get(Class<T> c, int pos) {
        Object object = getObject(pos);
        if (c.isInstance(object)) {
            return (T) object;
        } else {
            throw new RuntimeException(); // todo specify
        }
    }

    private <T> T get(Class<T> c, String columnName) {
        Object object = getObject(columnName);
        if (c.isInstance(object)) {
            return (T) object;
        } else {
            throw new RuntimeException(); // todo specify
        }
    }

    public Integer getInt(int pos) {
        return get(Integer.class, pos);
    }

    public Integer getInt(String columnName) {
        return get(Integer.class, columnName);
    }

    public String getString(int pos) {
        return get(String.class, pos);
    }

    public String getString(String columnName) {
        return get(String.class, columnName);
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
                    complexResult.addItem(new Item(columnName, pos, content));
                }
                return complexResult;
            }
        };
    }
}
