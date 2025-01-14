package com.s20_18_T_WebApp.backend.users.internal.domain.entity;

import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import com.s20_18_T_WebApp.backend.shared.domain.vo.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    //fecha de nacimiento, email, password,los niveles del usuario(VO)
    //puntos del usuario para llegar al siguiente nivel.(int?)
    //logros del usuario TODO que logros tiene el usuario
    //password va a ir en la entidad dentro de la carpeta auth.

    @Column(name = "first-name", nullable = false, unique = true)
    private String firstname;

    @Column(name = "last-name", nullable = false, unique = true)
    private String lastname;

    @Embedded
    @Column(name = "profile-photo", nullable = true)
    private Image profilePhoto;//esto va a ser un avatar predefinido. TODO definir los avatares de los usuarios.

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "points", nullable = false)
    private int points = 0;

    private List<String> habits;//TODO definir los hábitos de los usuarios. Definir entidad.

    private List<String> level;//TODO definir los niveles de los usuarios. Definir Value Object.

    private List<String> logros;//TODO definir los logros de los usuarios. Definir Value Object.

    private boolean active;//? TODO lo incorporamos para el MVP active?
}
