package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

@Entity
public class BodyMeasurements {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
