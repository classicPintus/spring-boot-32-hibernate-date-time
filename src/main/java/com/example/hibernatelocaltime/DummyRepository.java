package com.example.hibernatelocaltime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DummyRepository extends JpaRepository<DummyEntity, Long> {
    Optional<DummyEntity> findByName(String name);
}
