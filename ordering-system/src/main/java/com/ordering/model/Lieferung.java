package com.ordering.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Lieferung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // vorbereitet | unterwegs | übergeben

    @OneToOne
    private Bestellung bestellung;

    @ManyToOne
    private Lieferant lieferant;
}
