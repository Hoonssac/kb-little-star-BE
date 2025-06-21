package com.kbstar.littlestar.pokemon.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Pokemon {

    private Long id;

    private String name;

    private String imageUrl;

    private Double height;

    private Double weight;

    private String types;

    @Builder
    public Pokemon(Long id, String name, String imageUrl, Double height, Double weight, String types) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.height = height;
        this.weight = weight;
        this.types = types;
    }
}
