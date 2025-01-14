package com.s20_18_T_WebApp.backend.habits.internal.domain.entity;

import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "habits")
public class Habit extends BaseEntity {
    /*
      nombre, fecha, dias que se va a repetir(duracion del habito, puede ser para siempre o por un valor predefinido de dias. Si se termina de utilizar o no se usa por determinado tiempo se archiva.)
    , dias de la semana. tipo de habito.
    porcentaje de seguimiento,
    dias de racha.
    archivado: true o false.
     */

    private String name;//TODO default name segun tipo de habito.

    private String day;//days of the week
}
