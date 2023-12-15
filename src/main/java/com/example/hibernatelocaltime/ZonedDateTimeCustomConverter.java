package com.example.hibernatelocaltime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.type.descriptor.DateTimeUtils;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeCustomConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        Timestamp res = null;

        if (attribute != null) {
            res = Timestamp.from(attribute.toInstant());

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
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        ZonedDateTime res = null;

        if (dbData != null) {
            res = dbData.toInstant().atZone(ZoneOffset.UTC);
        }

        return res;
    }
}
