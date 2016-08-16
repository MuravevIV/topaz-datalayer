package com.ilyamur.topaz.datalayer.mybatis.typehandler;

import com.ilyamur.topaz.datalayer.core.converter.LocalDateConverter;
import com.ilyamur.topaz.datalayer.mybatis.util.AttributeConverterTypeHandlerAdapter;

import javax.persistence.AttributeConverter;

import java.sql.Timestamp;
import java.time.LocalDate;

public class LocalDateTypeHandler extends AttributeConverterTypeHandlerAdapter<LocalDate, Timestamp> {

    @Override
    protected AttributeConverter<LocalDate, Timestamp> createConverter() {
        return new LocalDateConverter();
    }
}
