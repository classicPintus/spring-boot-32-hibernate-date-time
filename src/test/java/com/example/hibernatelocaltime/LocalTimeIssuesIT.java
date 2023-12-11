package com.example.hibernatelocaltime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
public class LocalTimeIssuesIT extends BaseSpringIT {

    @Autowired
    private DummyRepository dummyRepository;

    @Test
    void shouldSaveLocalTimeMaxCorrectly() {
        LocalTime expectedLocalTime = LocalTime.MAX; // nanoseconds precision

        var entity = new DummyEntity();
        entity.setLocalTime(expectedLocalTime);
        dummyRepository.save(entity);

        var savedLocalTime = dummyRepository.findAll().get(0).getLocalTime();

        softly.assertThat(savedLocalTime).isEqualTo(expectedLocalTime);
        softly.assertThat(dummyRepository.findByLocalTime(expectedLocalTime)).isNotEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.of(23, 0))).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MIN)).isEmpty();
    }

    @Test
    void shouldSaveLocalTimeMaxWithMicroCorrectly() {
        LocalTime expectedLocalTime = LocalTime.MAX.withNano(999_999_000); // microseconds precision

        var entity = new DummyEntity();
        entity.setLocalTime(expectedLocalTime);
        dummyRepository.save(entity);

        var savedLocalTime = dummyRepository.findAll().get(0).getLocalTime();

        softly.assertThat(savedLocalTime).isEqualTo(expectedLocalTime);
        softly.assertThat(dummyRepository.findByLocalTime(expectedLocalTime)).isNotEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.of(23, 0))).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MIN)).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MAX)).isEmpty();
    }

    @Test
    void shouldSaveLocalTimeMaxWithMilliCorrectly() {
        LocalTime expectedLocalTime = LocalTime.MAX.withNano(999_000_000); // milliseconds precision

        var entity = new DummyEntity();
        entity.setLocalTime(expectedLocalTime);
        dummyRepository.save(entity);

        var savedLocalTime = dummyRepository.findAll().get(0).getLocalTime();

        softly.assertThat(savedLocalTime).isEqualTo(expectedLocalTime);
        softly.assertThat(dummyRepository.findByLocalTime(expectedLocalTime)).isNotEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.of(23, 0))).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MIN)).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MAX)).isEmpty();
    }
}
