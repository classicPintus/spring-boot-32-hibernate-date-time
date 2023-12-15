package com.example.hibernatelocaltime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.*;

public class ZonedDateTimeIssuesIT extends BaseSpringIT {

    @Autowired
    private DummyRepository dummyRepository;

    @Test
    void shouldSaveInstantCorrectly() {
        var expected = ZonedDateTime.of(LocalDateTime.of(LocalDate.of(1984, Month.OCTOBER, 2), LocalTime.MAX), ZoneId.of("UTC"));

        var entity = new DummyEntity();
        entity.setZonedDateTime(expected);
        dummyRepository.save(entity);

        var saved = dummyRepository.findAll().get(0).getZonedDateTime();

        softly.assertThat(saved.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()).isEqualTo(465609599999L); // 02/10/1984 23:59:59.999
        softly.assertThat(dummyRepository.findByZonedDateTime(expected)).isNotEmpty();
    }
}
