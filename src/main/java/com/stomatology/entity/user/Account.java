package com.stomatology.entity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @NotNull
    @NonNull
    private Role role;
}
