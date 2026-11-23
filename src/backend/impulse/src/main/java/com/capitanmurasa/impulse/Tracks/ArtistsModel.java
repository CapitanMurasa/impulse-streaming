package com.capitanmurasa.impulse.Tracks;

import jakarta.persistence.*;

@Entity
@Table(name = "artists")
public class ArtistsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    public ArtistsModel() {
    }

    public ArtistsModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
