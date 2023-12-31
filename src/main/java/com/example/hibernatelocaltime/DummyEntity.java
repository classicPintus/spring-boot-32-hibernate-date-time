package com.example.hibernatelocaltime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class DummyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalTime localTime;
    private Instant instant;
    private LocalDateTime localDateTime;
    private ZonedDateTime zonedDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DummyEntity other)) return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
