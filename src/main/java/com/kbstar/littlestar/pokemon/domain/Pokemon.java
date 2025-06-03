package com.kbstar.littlestar.pokemon.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
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
}
