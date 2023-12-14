package com.example.hibernatelocaltime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.type.descriptor.DateTimeUtils;

import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

@Converter(autoApply = true)
public class LocalTimeCustomConverter implements AttributeConverter<LocalTime, Time> {
    @Override
    public Time convertToDatabaseColumn(LocalTime value) {
        Time res = null;

        if (value != null) {
            res = Time.valueOf(value);
            if (value.getNano() > 0) {
                long millis = DateTimeUtils.roundToPrecision(value.getNano(), 3) / 1_000_000;

                if (millis >= 1_000) {
                    millis = 999;
                }

                res = new Time(res.getTime() + millis);
            }
        }

        return res;
    }

    @Override
    public LocalTime convertToEntityAttribute(Time dbData) {
        LocalTime res = null;

        if (dbData != null) {
            LocalTime localTime = dbData.toLocalTime();
            final long millis = dbData.getTime() % 1_000;
            if (millis == 0) {
                res = localTime;
            } else {
                res = localTime.with(ChronoField.NANO_OF_SECOND, millis * 1_000_000L);
            }
        }

        return res;
    }
}
