package com.ordering.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Bestellung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datum = LocalDateTime.now();
    private String status; // z.B. "NEU", "IN_ZUBEREITUNG", "FERTIG", "UNTERWEGS"

    @ManyToOne
    private Kunde kunde;

    @OneToMany(mappedBy = "bestellung", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BestellPosition> positionen = new ArrayList<>();

    public void addPosition(BestellPosition pos) {
        pos.setBestellung(this);
        positionen.add(pos);
    }
}
