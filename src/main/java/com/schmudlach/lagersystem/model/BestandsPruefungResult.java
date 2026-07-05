package com.schmudlach.lagersystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class BestandsPruefungResult {
    private boolean success;
    private String errorMessage;
    private List<BestandsProblem> probleme;

    private BestandsPruefungResult(boolean success, String errorMessage, List<BestandsProblem> probleme) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.probleme = probleme;
    }
    public static BestandsPruefungResult success() {
        return new BestandsPruefungResult(true, null, Collections.emptyList());
    }

    public static BestandsPruefungResult failed(String errorMessage, List<BestandsProblem> probleme) {
        return new BestandsPruefungResult(false, errorMessage, probleme);
    }
}
