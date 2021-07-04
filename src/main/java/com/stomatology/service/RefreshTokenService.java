package com.stomatology.service;

import com.stomatology.entity.user.Account;
import com.stomatology.entity.user.RefreshToken;
import com.stomatology.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String token, Account account) {
        refreshTokenRepository.save(new RefreshToken(token, account));
    }

    public void saveRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    public void deleteRefreshToken(String token, Account account) {
        Map<String, Long> tokensFromDatabase = refreshTokenRepository.findDistinctByAccount(account).stream()
                .collect(Collectors.toMap(RefreshToken::getRefreshToken, RefreshToken::getId));
        Optional<Long> maybeId = Optional.ofNullable(tokensFromDatabase.get(token));
        maybeId.ifPresent(refreshTokenRepository::deleteById);
    }

    public Set<RefreshToken> findByAccount(Account account) {
        return refreshTokenRepository.findDistinctByAccount(account);
    }
}
