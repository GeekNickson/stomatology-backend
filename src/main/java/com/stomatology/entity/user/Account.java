package com.stomatology.entity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_account")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @SequenceGenerator(name = "accountSeq", sequenceName = "app_account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeq")
    private Long id;

    @Column(name = "email")
    @NotNull
    @NonNull
    private String email;

    @Column(name = "password")
    @NotNull
    @NonNull
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @NotNull
    @NonNull
    private Role role;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<RefreshToken> refreshToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && email.equals(account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
