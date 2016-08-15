package com.ilyamur.topaz.datalayer.spring.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setTimestamp(i, toTimestamp(parameter));
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Timestamp sqlTimestamp = rs.getTimestamp(columnName);
        if (sqlTimestamp != null) {
            return toLocalDate(sqlTimestamp);
        }
        return null;
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Timestamp sqlTimestamp = rs.getTimestamp(columnIndex);
        if (sqlTimestamp != null) {
            return toLocalDate(sqlTimestamp);
        }
        return null;
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
        if (sqlTimestamp != null) {
            return toLocalDate(sqlTimestamp);
        }
        return null;
    }

    private Timestamp toTimestamp(LocalDate dateTime) {
        return Timestamp.valueOf(dateTime.atStartOfDay());
    }

    private LocalDate toLocalDate(Timestamp sqlTimestamp) {
        return sqlTimestamp.toLocalDateTime().toLocalDate();
    }
}
