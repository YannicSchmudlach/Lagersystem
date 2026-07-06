package com.schmudlach.lagersystem;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.entity.RezeptLebensmittel;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.model.BestandsPruefungResult;
import com.schmudlach.lagersystem.service.BestandsPruefungService;
import com.schmudlach.lagersystem.service.VerfügbareLebensmittelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BestandsPruefungServiceTest {
    @Mock
    private VerfügbareLebensmittelService verfügbareLebensmittelService;

    @InjectMocks
    private BestandsPruefungService service;

    @Test
    void pruefungIstErfolgreichWennAlleLebensmittelAusreichendVorhandenSind() {
        Lebensmittel reis = Lebensmittel.builder()
                .name("Reis")
                .build();
        reis.setLebensmittelID(1);

        RezeptLebensmittel rezeptLebensmittel = RezeptLebensmittel.builder()
                .lebensmittel(reis)
                .menge(2)
                .build();

        Rezept rezept = Rezept.builder()
                .beschreibung("Reisgericht")
                .rezeptLebensmittel(List.of(rezeptLebensmittel))
                .build();

        VerfügbareLebensmittel bestand = VerfügbareLebensmittel.builder()
                .lebensmittel(reis)
                .anzahl(5)
                .threshold(1)
                .build();

        when(verfügbareLebensmittelService.findByLebensmittel(reis))
                .thenReturn(Optional.of(bestand));

        BestandsPruefungResult result = service.ueberfruefenObAlleLebensmittelVorhandenSind(rezept);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getProbleme()).isEmpty();
    }

    @Test
    void pruefungSchlaegtFehlWennNichtGenugBestandVorhandenIst() {
        Lebensmittel reis = Lebensmittel.builder()
                .name("Reis")
                .build();
        reis.setLebensmittelID(1);

        RezeptLebensmittel rezeptLebensmittel = RezeptLebensmittel.builder()
                .lebensmittel(reis)
                .menge(5)
                .build();

        Rezept rezept = Rezept.builder()
                .beschreibung("Reisgericht")
                .rezeptLebensmittel(List.of(rezeptLebensmittel))
                .build();

        VerfügbareLebensmittel bestand = VerfügbareLebensmittel.builder()
                .lebensmittel(reis)
                .anzahl(2)
                .threshold(1)
                .build();

        when(verfügbareLebensmittelService.findByLebensmittel(reis))
                .thenReturn(Optional.of(bestand));

        BestandsPruefungResult result = service.ueberfruefenObAlleLebensmittelVorhandenSind(rezept);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getProbleme()).hasSize(1);
    }

    @Test
    void pruefungSchlaegtFehlWennKeinBestandseintragExistiert() {
        Lebensmittel tofu = Lebensmittel.builder()
                .name("Tofu")
                .build();
        tofu.setLebensmittelID(2);

        RezeptLebensmittel rezeptLebensmittel = RezeptLebensmittel.builder()
                .lebensmittel(tofu)
                .menge(1)
                .build();

        Rezept rezept = Rezept.builder()
                .beschreibung("Tofugericht")
                .rezeptLebensmittel(List.of(rezeptLebensmittel))
                .build();

        when(verfügbareLebensmittelService.findByLebensmittel(tofu))
                .thenReturn(Optional.empty());

        BestandsPruefungResult result = service.ueberfruefenObAlleLebensmittelVorhandenSind(rezept);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getProbleme()).hasSize(1);
    }
}
