package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
}
