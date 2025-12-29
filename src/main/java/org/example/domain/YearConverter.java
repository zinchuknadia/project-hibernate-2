package org.example.domain;

import jakarta.persistence.AttributeConverter;

import java.time.Year;

public class YearConverter implements AttributeConverter<Year, Short> {

    @Override
    public Short convertToDatabaseColumn(Year year) {
        if (year != null) {
            return (short)year.getValue();
        }
        return null;
    }

    @Override
    public Year convertToEntityAttribute(Short aShort) {
        if (aShort != null) {
            return Year.of(aShort);
        }
        return null;
    }
}
