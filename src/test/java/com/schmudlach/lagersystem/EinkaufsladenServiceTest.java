package com.schmudlach.lagersystem;

import com.schmudlach.lagersystem.entity.Einkaufsladen;
import com.schmudlach.lagersystem.error.ConflictException;
import com.schmudlach.lagersystem.repository.EinkaufsladenRepository;
import com.schmudlach.lagersystem.service.EinkaufsladenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EinkaufsladenServiceTest {
    @Mock
    private EinkaufsladenRepository repository;

    @InjectMocks
    private EinkaufsladenService service;

    @Test
    void insertEinkaufsladen_speichertWennNameNochNichtExistiert() {
        Einkaufsladen input = Einkaufsladen.builder()
                .name("EDEKA")
                .build();

        Einkaufsladen saved = Einkaufsladen.builder()
                .name("EDEKA")
                .build();
        saved.setEinkaufsladenId(1);

        when(repository.findByName("EDEKA")).thenReturn(Optional.empty());
        when(repository.save(input)).thenReturn(saved);

        Einkaufsladen result = service.insertEinkaufsladen(input);

        assertThat(result.getEinkaufsladenId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("EDEKA");

        verify(repository).findByName("EDEKA");
        verify(repository).save(input);
    }

    @Test
    void insertEinkaufsladen_wirftConflictWennNameBereitsExistiert() {
        Einkaufsladen input = Einkaufsladen.builder()
                .name("EDEKA")
                .build();

        Einkaufsladen existing = Einkaufsladen.builder()
                .name("EDEKA")
                .build();

        when(repository.findByName("EDEKA")).thenReturn(Optional.of(existing));

        assertThatThrownBy(() -> service.insertEinkaufsladen(input))
                .isInstanceOf(ConflictException.class);

        verify(repository).findByName("EDEKA");
        verify(repository, never()).save(any());
    }
}

