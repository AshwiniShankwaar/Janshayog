package com.BitGeekTalks.JanShayog.Request.repo;

import com.BitGeekTalks.JanShayog.Request.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {
}
