package com.app.server.livechat.Repository.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.server.livechat.Entity.User.LivechatUser;

@Repository
public interface UserRepository extends JpaRepository<LivechatUser, Long> {

    public Optional<LivechatUser> findByEmailAndPassword(String email, String password);
    

}
