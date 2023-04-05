package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.IdProve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdProveRepo extends JpaRepository<IdProve,Long> {
}
