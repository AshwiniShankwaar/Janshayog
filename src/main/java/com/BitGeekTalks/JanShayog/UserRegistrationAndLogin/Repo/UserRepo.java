package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByAccountId(Long accountId);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("INSERT INTO User u (u.accountId, u.name, u.email) SELECT :accountId, :name, :email FROM User u WHERE u.accountId <> :accountId")
//    void addUserIfNotExists(@Param("accountId") String accountId, @Param("name") String name, @Param("email") String email);
}
