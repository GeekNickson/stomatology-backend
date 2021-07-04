package com.stomatology.repository;

import com.stomatology.entity.user.Account;
import com.stomatology.entity.user.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Set<RefreshToken> findDistinctByAccount(Account account);
}
