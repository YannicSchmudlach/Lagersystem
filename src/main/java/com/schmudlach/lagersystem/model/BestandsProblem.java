package com.schmudlach.lagersystem.model;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BestandsProblem {
    private final Lebensmittel lebensmittel;
    private final double benoetigt;
    private final double vorhanden;
}
