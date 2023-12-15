package com.example.hibernatelocaltime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.type.descriptor.DateTimeUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeCustomConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        Timestamp res = null;

        if (attribute != null) {
            res = Timestamp.valueOf(attribute);

            if (res.getNanos() > 0) {
                long millis = DateTimeUtils.roundToPrecision(res.getNanos(), 3) / 1_000_000;

                if (millis >= 1_000) {
                    res.setNanos(999 * 1_000_000);
                } else {
                    res.setNanos((int) (millis * 1_000_000));
                }
            }
        }

        return res;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        LocalDateTime res = null;

        if (dbData != null) {
            res = dbData.toLocalDateTime();
        }

        return res;
    }
}
