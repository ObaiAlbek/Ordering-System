```mermaid
classDiagram

    %% ===== ENTITIES =====

    class Kunde {
        +Long id
        +String name
        +String adresse
        +String email
        +boolean stammkunde
    }

    class Gericht {
        +Long id
        +String name
        +double preis
        +int zubereitungsZeit
    }

    class Bestellung {
        +Long id
        +LocalDateTime datum
        +String status
        +addPosition()
    }

    class BestellPosition {
        +Long id
        +int menge
        +double einzelPreis
    }

    class Zahlung {
        +Long id
        +double betrag
        +String methode
        +boolean trinkgeld
    }

    class Lieferung {
        +Long id
        +String status
    }

    class Koch {
        +Long id
        +String name
        +String spezialgebiet
    }

    class Lieferant {
        +Long id
        +String name
        +String fahrzeug
    }


    %% ===== RELATIONSHIPS =====

    Kunde "1" --> "*" Bestellung : gibt auf
    Bestellung "1" --> "*" BestellPosition : enthält
    BestellPosition "*" --> "1" Gericht : Gericht
    Bestellung "1" --> "1" Zahlung : bezahlt
    Bestellung "1" --> "1" Lieferung : Lieferung
    Lieferung "*" --> "1" Lieferant : liefert
