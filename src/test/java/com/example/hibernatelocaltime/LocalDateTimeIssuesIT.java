package com.example.hibernatelocaltime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
public class LocalDateTimeIssuesIT extends BaseSpringIT {

    @Autowired
    private DummyRepository dummyRepository;

    @Test
    void shouldSaveInstantCorrectly() {
        LocalDateTime expected = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        var entity = new DummyEntity();
        entity.setLocalDateTime(expected);
        dummyRepository.save(entity);

        var saved = dummyRepository.findAll().get(0).getLocalDateTime();

        softly.assertThat(saved).isEqualTo(expected);
        softly.assertThat(dummyRepository.findByLocalDateTime(expected)).isNotEmpty();
    }
}
