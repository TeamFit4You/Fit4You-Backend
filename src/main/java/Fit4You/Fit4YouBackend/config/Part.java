package Fit4You.Fit4YouBackend.config;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public enum Part {
    전체("전체"),
    목("목"),
    어깨("어깨"),
    허리("허리"),
    팔꿈치("팔꿈치"),
    손목("손목"),
    무릎("무릎"),
    ;
    private final String label;

    Part(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
