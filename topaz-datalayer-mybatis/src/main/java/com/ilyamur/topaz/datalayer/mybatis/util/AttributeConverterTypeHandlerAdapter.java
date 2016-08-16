package com.ilyamur.topaz.datalayer.mybatis.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import javax.persistence.AttributeConverter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AttributeConverterTypeHandlerAdapter<X, Y> extends BaseTypeHandler<X> {

    private volatile AttributeConverter<X, Y> converter;

    private AttributeConverter<X, Y> getConverter() {
        if (converter == null) {
            converter = createConverter();
        }
        return converter;
    }

    protected abstract AttributeConverter<X,Y> createConverter();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, X parameter, JdbcType jdbcType) throws SQLException {
        Y dbValue = getConverter().convertToDatabaseColumn(parameter);
        if (dbValue != null) {
            ps.setObject(i, dbValue);
        } else {
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public X getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getConverter().convertToEntityAttribute((Y) rs.getObject(columnName));
    }

    @Override
    @SuppressWarnings("unchecked")
    public X getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getConverter().convertToEntityAttribute((Y) rs.getObject(columnIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public X getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getConverter().convertToEntityAttribute((Y) cs.getObject(columnIndex));
    }
}
