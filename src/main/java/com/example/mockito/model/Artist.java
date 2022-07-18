package com.example.mockito.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String spotifyAddress;
}
