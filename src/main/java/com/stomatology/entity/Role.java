package com.stomatology.entity;

import com.stomatology.entity.enums.RoleName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_role")
public class Role {

    @Id
    @SequenceGenerator(name="appRoleIdSeq", sequenceName = "app_role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appRoleIdSeq")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleName name;
}
