package com.ordering.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Lieferant extends Person {

    private String fahrzeug;
}
