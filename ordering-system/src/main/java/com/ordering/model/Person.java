package com.ordering.model;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter @Setter
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
