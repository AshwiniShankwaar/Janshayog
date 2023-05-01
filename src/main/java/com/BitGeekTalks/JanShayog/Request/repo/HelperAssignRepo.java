package com.BitGeekTalks.JanShayog.Request.repo;

import com.BitGeekTalks.JanShayog.Request.entity.HelperAssign;
import com.BitGeekTalks.JanShayog.Request.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelperAssignRepo extends JpaRepository<HelperAssign,Long> {
    List<HelperAssign> findByAccountId(long accountId);
    @Query("SELECT r FROM Request r JOIN r.helperAssignments ha WHERE ha.accountId = :helperId")
    List<Request> findRequestsByHelperId(@Param("helperId") long helperId);
}
