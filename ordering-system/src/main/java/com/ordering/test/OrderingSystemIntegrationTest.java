package com.ordering.test;

import com.ordering.model.*;
import com.ordering.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@Transactional   // Alles wird nach dem Test zurückgerollt
public class OrderingSystemIntegrationTest {

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private GerichtRepository gerichtRepository;

    @Autowired
    private BestellungRepository bestellungRepository;

    @Autowired
    private ZahlungRepository zahlungRepository;

    @Autowired
    private LieferungRepository lieferungRepository;

    @Autowired
    private LieferantRepository lieferantRepository;

    @Test
    void fullOrderProcessTest() {

        // ===========================
        // 1) Kunde anlegen
        // ===========================
        Kunde kunde = new Kunde();
        kunde.setName("Obai");
        kunde.setEmail("obai@mail.de");
        kunde.setAdresse("Mannheim");
        kunde.setStammkunde(true);

        kunde = kundeRepository.save(kunde);
        assertThat(kunde.getId()).isNotNull();


        // ===========================
        // 2) Gericht anlegen
        // ===========================
        Gericht pizza = new Gericht();
        pizza.setName("Pizza Margherita");
        pizza.setPreis(8.50);
        pizza.setZubereitungsZeit(15);

        pizza = gerichtRepository.save(pizza);
        assertThat(pizza.getId()).isNotNull();


        // ===========================
        // 3) Bestellung anlegen
        // ===========================
        Bestellung bestellung = new Bestellung();
        bestellung.setKunde(kunde);
        bestellung.setStatus("NEU");

        // Position hinzufügen
        BestellPosition pos = new BestellPosition();
        pos.setGericht(pizza);
        pos.setMenge(2);
        pos.setEinzelPreis(pizza.getPreis());

        bestellung.addPosition(pos);

        bestellung = bestellungRepository.save(bestellung);
        assertThat(bestellung.getId()).isNotNull();
        assertThat(bestellung.getPositionen()).hasSize(1);


        // ===========================
        // 4) Zahlung durchführen
        // ===========================
        Zahlung zahlung = new Zahlung();
        zahlung.setBetrag(17.00);
        zahlung.setMethode("bar");
        zahlung.setTrinkgeld(true);
        zahlung.setBestellung(bestellung);

        zahlung = zahlungRepository.save(zahlung);
        assertThat(zahlung.getId()).isNotNull();


        // ===========================
        // 5) Lieferung zuordnen
        // ===========================
        Lieferant lieferant = new Lieferant();
        lieferant.setName("Max Mustermann");
        lieferant.setFahrzeug("Fahrrad");

        lieferant = lieferantRepository.save(lieferant);

        Lieferung lieferung = new Lieferung();
        lieferung.setLieferant(lieferant);
        lieferung.setBestellung(bestellung);
        lieferung.setStatus("unterwegs");

        lieferung = lieferungRepository.save(lieferung);

        assertThat(lieferung.getId()).isNotNull();
        assertThat(lieferung.getLieferant().getName()).isEqualTo("Max Mustermann");


        // ===========================
        // 6) Alles aus DB lesen & prüfen
        // ===========================
        List<Bestellung> alleBestellungen = bestellungRepository.findAll();
        assertThat(alleBestellungen).hasSize(1);

        Bestellung geladene = alleBestellungen.get(0);

        assertThat(geladene.getKunde().getName()).isEqualTo("Obai");
        assertThat(geladene.getPositionen()).hasSize(1);
        assertThat(geladene.getPositionen().get(0).getGericht().getName())
                .isEqualTo("Pizza Margherita");

        System.out.println("✔ Vollständiger Bestellprozess erfolgreich getestet!");
    }
}
