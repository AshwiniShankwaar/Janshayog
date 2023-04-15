package com.BitGeekTalks.JanShayog.Request.repo;

import com.BitGeekTalks.JanShayog.Request.entity.Request;
import com.BitGeekTalks.JanShayog.Request.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {
    Request findByTaskId(Task taskId);

    List<Request> findByAccountIdAndRequestStatus(long accountId,String requestStatus);
    List<Request> findByRequestStatus(String requestStatus);
}
