package com.BitGeekTalks.JanShayog.Request.repo;

import com.BitGeekTalks.JanShayog.Request.entity.HelperAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperAssignRepo extends JpaRepository<HelperAssign,Long> {
}
