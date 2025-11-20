package com.ordering.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BestellPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int menge;
    private double einzelPreis;

    @ManyToOne
    private Gericht gericht;

    @ManyToOne
    private Bestellung bestellung;
}
