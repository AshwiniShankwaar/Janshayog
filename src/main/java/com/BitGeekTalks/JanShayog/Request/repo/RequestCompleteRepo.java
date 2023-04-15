package com.BitGeekTalks.JanShayog.Request.repo;

import com.BitGeekTalks.JanShayog.Request.entity.RequestComplete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RequestCompleteRepo extends JpaRepository<RequestComplete,Long> {
    RequestComplete findByRequestId(long requestId);
}
