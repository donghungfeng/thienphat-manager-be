package com.example.zalo_manager.repository;


import com.example.zalo_manager.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findAllByUsername(String userName);
}
