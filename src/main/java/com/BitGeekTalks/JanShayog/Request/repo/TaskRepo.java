package com.BitGeekTalks.JanShayog.Request.repo;

import com.BitGeekTalks.JanShayog.Request.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> findBySkillsContaining(String skill);
}
