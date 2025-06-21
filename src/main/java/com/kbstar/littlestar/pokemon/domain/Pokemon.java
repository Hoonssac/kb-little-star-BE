package com.kbstar.littlestar.pokemon.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

}
