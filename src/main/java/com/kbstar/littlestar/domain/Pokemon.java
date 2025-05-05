package com.kbstar.littlestar.domain;

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
public class Pokemon {
    @Id
    private Long id;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    private Double height;
    private Double weight;


    @Column(columnDefinition = "TEXT")
    private String types;
}
