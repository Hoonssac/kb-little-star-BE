package com.kbstar.littlestar.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
