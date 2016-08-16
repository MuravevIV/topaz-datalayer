package com.ilyamur.topaz.datalayer.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDate dateTime) {
        return Timestamp.valueOf(dateTime.atStartOfDay());
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp sqlTimestamp) {
        return sqlTimestamp.toLocalDateTime().toLocalDate();
    }
}
