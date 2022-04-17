package com.ilyamur.topaz.datalayer.typehandler;

import com.ilyamur.topaz.datalayer.core.converter.LocalDateConverter;
import com.ilyamur.topaz.datalayer.typehandler.adapter.AttributeConverterToTypeHandlerAdapter;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * See: src/main/resources/mybatis/config.xml.
 */
public class CustomLocalDateTypeHandler extends AttributeConverterToTypeHandlerAdapter<LocalDate, Timestamp> {

    @Override
    protected AttributeConverter<LocalDate, Timestamp> createAttributeConverter() {
        return new LocalDateConverter();
    }
}
