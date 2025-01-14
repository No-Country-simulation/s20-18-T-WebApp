package com.s20_18_T_WebApp.backend.users.internal.domain.entity;

import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import com.s20_18_T_WebApp.backend.shared.domain.vo.Image;
import jakarta.persistence.Column;

public class User extends BaseEntity {
    //foto, nombre, apellido, fecha de nacimiento, email, password,los niveles del usuario(VO), TODO cuales son los niveles.
    //puntos del usuario para llegar al siguiente nivel.(int?)
    //logros del usuario TODO que logros tiene el usuario

    @Column(name = "first-name", nullable = false, unique = true)
    private String firstname;

    @Column(name = "last-name", nullable = false, unique = true)
    private String lastname;

    @Column(name = "photo, nullable = true")
    private Image photo;
}
