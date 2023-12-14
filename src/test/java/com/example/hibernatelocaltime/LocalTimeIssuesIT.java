package com.example.hibernatelocaltime;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

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

        softly.assertThat(savedLocalTime).isEqualTo(LocalTime.of(23,59,59,999_000_000));
    }

    @Test
    void shouldSupportDerivedMethodQuery() {
        LocalTime expectedLocalTime = LocalTime.MAX; // nanoseconds precision

        var entity = new DummyEntity();
        entity.setLocalTime(expectedLocalTime);
        dummyRepository.save(entity);

        softly.assertThat(dummyRepository.findByLocalTime(expectedLocalTime)).isNotEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.of(23, 0))).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MIN)).isEmpty();
    }

    @Test
    void shouldSupportCustomQuery() {
        LocalTime expectedLocalTime = LocalTime.MAX; // nanoseconds precision

        var entity = new DummyEntity();
        entity.setLocalTime(expectedLocalTime);
        dummyRepository.save(entity);

        Assertions.assertThat(dummyRepository.findByPorcaMadonnaLocalTime(expectedLocalTime)).isNotEmpty();
    }

    @Test
    void shouldSupportSpecifications() {
        LocalTime expectedLocalTime = LocalTime.MAX; // nanoseconds precision

        var entity = new DummyEntity();
        entity.setLocalTime(expectedLocalTime);
        dummyRepository.save(entity);

        var specification = new Specification<DummyEntity>() {
            @Override
            public Predicate toPredicate(Root<DummyEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate localTime = criteriaBuilder.equal(root.get("localTime"), LocalTime.MAX);
                return criteriaBuilder.and(localTime);
            }
        };
        Assertions.assertThat(dummyRepository.findAll(specification, Pageable.unpaged())).isNotEmpty();
    }

    @Test
    void shouldSaveLocalTimeMaxWithMicroCorrectly() {
        LocalTime expectedLocalTime = LocalTime.MAX.withNano(999_999_000); // microseconds precision

        var entity = new DummyEntity();
        entity.setLocalTime(expectedLocalTime);
        dummyRepository.save(entity);

        var savedLocalTime = dummyRepository.findAll().get(0).getLocalTime();

        softly.assertThat(savedLocalTime).isEqualTo(LocalTime.of(23,59,59,999_000_000));
        softly.assertThat(dummyRepository.findByLocalTime(expectedLocalTime)).isNotEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.of(23, 0))).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MIN)).isEmpty();
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MAX)).isNotEmpty();
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
        softly.assertThat(dummyRepository.findByLocalTime(LocalTime.MAX)).isNotEmpty();
    }
}
