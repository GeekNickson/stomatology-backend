package com.stomatology.entity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "app_token")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @SequenceGenerator(name = "tokenSeq", sequenceName = "app_token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokenSeq")
    private Long id;

    @Column(name = "token")
    @NotNull
    @NonNull
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @NotNull
    @NonNull
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshToken that = (RefreshToken) o;
        return Objects.equals(id, that.id) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, refreshToken);
    }
}
