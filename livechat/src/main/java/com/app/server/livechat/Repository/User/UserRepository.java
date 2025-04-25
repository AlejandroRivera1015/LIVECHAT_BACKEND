package com.app.server.livechat.Repository.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.server.livechat.Entity.User.LivechatUser;


@Repository
public interface UserRepository extends JpaRepository<LivechatUser, Long> {

    public Optional<LivechatUser> findByEmailAndPassword(String email, String password);
    
    @Modifying 
    @Transactional
    @Query("UPDATE LivechatUser u SET u.wsId = :wsId WHERE u.id = :userId")
    public int setWsId(Long userId, String wsId);

    public Optional<LivechatUser> findById(Long id);

    @Query("SELECT u.wsId FROM LivechatUser u WHERE u.id = :id")
    public String findWsIdById(Long id);

    public Optional<LivechatUser> findByEmail(String email);
}
