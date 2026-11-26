package com.capitanmurasa.impulse.Playlists;

import com.capitanmurasa.impulse.Users.UsersModel;
import jakarta.persistence.*;

@Entity
@Table(name = "playlists")
public class PlaylistModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "visibility", nullable = false)
    private boolean visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersModel user;

    public PlaylistModel() {}

    public PlaylistModel(String name, boolean visibility, UsersModel user) {
        this.name = name;
        this.visibility = visibility;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }
}