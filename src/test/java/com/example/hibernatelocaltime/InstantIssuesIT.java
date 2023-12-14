package com.example.hibernatelocaltime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class InstantIssuesIT extends BaseSpringIT {

    @Autowired
    private DummyRepository dummyRepository;

    @Test
    void shouldSaveInstantCorrectly() {
        Instant expected = LocalDate.now().atTime(LocalTime.MAX).atZone(ZoneId.of("UTC")).toInstant();

        var entity = new DummyEntity();
        entity.setInstant(expected);
        dummyRepository.save(entity);

        var saved = dummyRepository.findAll().get(0).getInstant();

        softly.assertThat(saved).isEqualTo(LocalDate.now().atTime(LocalTime.of(23,59,59,999_000_000)).atZone(ZoneId.of("UTC")).toInstant());
        softly.assertThat(dummyRepository.findByInstant(expected)).isNotEmpty();
    }
}
