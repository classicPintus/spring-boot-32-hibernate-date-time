package com.example.hibernatelocaltime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface DummyRepository extends JpaRepository<DummyEntity, Long>, JpaSpecificationExecutor<DummyEntity> {
    Optional<DummyEntity> findByLocalTime(LocalTime localTime);
    Optional<DummyEntity> findByInstant(Instant instant);
    Optional<DummyEntity> findByLocalDateTime(LocalDateTime localDateTime);
    Optional<DummyEntity> findByZonedDateTime(ZonedDateTime zonedDateTime);

    @Query("from DummyEntity de where 1 = 1 and de.localTime = :localTime")
    Optional<DummyEntity> findByPorcaMadonnaLocalTime(LocalTime localTime);
}
