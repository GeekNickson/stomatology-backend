package com.stomatology.repository;

import com.stomatology.entity.user.Account;
import com.stomatology.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccount(Account account);
}
