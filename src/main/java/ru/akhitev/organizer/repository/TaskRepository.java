package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
