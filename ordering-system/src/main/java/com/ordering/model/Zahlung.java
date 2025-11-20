package com.ordering.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Zahlung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double betrag;
    private String methode; // bar | paypal | karte
    private boolean trinkgeld;

    @OneToOne
    private Bestellung bestellung;
}
