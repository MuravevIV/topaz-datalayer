package com.ilyamur.topaz.datalayer.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDate dateTime) {
        if (dateTime != null) {
            return Timestamp.valueOf(dateTime.atStartOfDay());
        } else {
            return null;
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp sqlTimestamp) {
        if (sqlTimestamp != null) {
            return sqlTimestamp.toLocalDateTime().toLocalDate();
        } else {
            return null;
        }
    }
}
