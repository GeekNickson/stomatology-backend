package com.stomatology.entity.user;

import com.stomatology.entity.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_role")
@Getter
@Setter
public class Role {

    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "app_role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleName name;
}
