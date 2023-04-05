package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.PasswordChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordChangeLogRepo extends JpaRepository<PasswordChangeLog,Long> {
}
