package com.schmudlach.lagersystem;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.entity.RezeptLebensmittel;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.model.BestandsPruefungResult;
import com.schmudlach.lagersystem.service.BestandsPruefungService;
import com.schmudlach.lagersystem.service.KochenService;
import com.schmudlach.lagersystem.service.RezeptService;
import com.schmudlach.lagersystem.service.VerfügbareLebensmittelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KochServiceTest {
    @Mock
    private VerfügbareLebensmittelService verfügbareLebensmittelService;

    @Mock
    private RezeptService rezeptService;

    @Mock
    private BestandsPruefungService bestandsPruefungService;

    @InjectMocks
    private KochenService service;

    @Test
    void decrementVerfügbareLebensmittel_reduziertBestandWennRezeptKochbarIst() {
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
        rezept.setRezeptId(10);

        VerfügbareLebensmittel bestand = VerfügbareLebensmittel.builder()
                .lebensmittel(reis)
                .anzahl(5)
                .threshold(1)
                .build();

        when(rezeptService.getRezept(10)).thenReturn(rezept);
        when(bestandsPruefungService.ueberfruefenObAlleLebensmittelVorhandenSind(rezept))
                .thenReturn(BestandsPruefungResult.success());
        when(verfügbareLebensmittelService.getVerfügbareLebensmittelByLebensmittel(reis))
                .thenReturn(bestand);

        BestandsPruefungResult result = service.decrementVerfügbareLebensmittel(10);

        assertThat(result.isSuccess()).isTrue();
        assertThat(bestand.getAnzahl()).isEqualTo(3);

        verify(verfügbareLebensmittelService).getVerfügbareLebensmittelByLebensmittel(reis);
    }

    @Test
    void decrementVerfügbareLebensmittel_reduziertNichtsWennBestandsPruefungFehlschlaegt() {
        Lebensmittel reis = Lebensmittel.builder()
                .name("Reis")
                .build();
        reis.setLebensmittelID(1);

        Rezept rezept = Rezept.builder()
                .beschreibung("Reisgericht")
                .rezeptLebensmittel(List.of())
                .build();
        rezept.setRezeptId(10);

        BestandsPruefungResult failedResult =
                BestandsPruefungResult.failed("Nicht genug Bestand", List.of());

        when(rezeptService.getRezept(10)).thenReturn(rezept);
        when(bestandsPruefungService.ueberfruefenObAlleLebensmittelVorhandenSind(rezept))
                .thenReturn(failedResult);

        BestandsPruefungResult result = service.decrementVerfügbareLebensmittel(10);

        assertThat(result.isSuccess()).isFalse();

        verify(verfügbareLebensmittelService, never())
                .getVerfügbareLebensmittelByLebensmittel(any());
    }
}
