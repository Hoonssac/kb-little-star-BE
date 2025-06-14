package com.kbstar.littlestar.pokemon.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "pokemon")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Pokemon {
    @Id
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
