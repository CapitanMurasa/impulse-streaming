package com.capitanmurasa.impulse.Playlists;

import java.io.Serializable;
import java.util.Objects;

public class PlaylistTrackId implements Serializable {

    private Integer playlist;
    private Integer track;

    public PlaylistTrackId() {}

    public PlaylistTrackId(Integer playlist, Integer track) {
        this.playlist = playlist;
        this.track = track;
    }

    public Integer getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Integer playlist) {
        this.playlist = playlist;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistTrackId that)) return false;

        return Objects.equals(playlist, that.playlist)
                && Objects.equals(track, that.track);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist, track);
    }
}