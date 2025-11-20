package com.ordering.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Koch extends Person {

    private String spezialgebiet;
}
