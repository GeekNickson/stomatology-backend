package com.stomatology.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_account")
@Getter
@Setter
public class Account {

    @Id
    @SequenceGenerator(name="appAccountIdSeq", sequenceName = "app_account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appAccountIdSeq")
    private Long id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;
}
