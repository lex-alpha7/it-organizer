package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.entity.Reference;

public interface ReferenceRepository extends JpaRepository<Reference, Integer> {
}