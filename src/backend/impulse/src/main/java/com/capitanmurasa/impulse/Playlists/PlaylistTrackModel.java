package com.capitanmurasa.impulse.Playlists;

import com.capitanmurasa.impulse.Tracks.TracksModel;
import jakarta.persistence.*;

@Entity
@Table(name = "playlist_tracks")
@IdClass(PlaylistTrackId.class)
public class PlaylistTrackModel {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playlist_id", nullable = false)
    private PlaylistModel playlist;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_id", nullable = false)
    private TracksModel track;

    @Column(name = "position", nullable = false)
    private Integer position;

    public PlaylistTrackModel() {}

    public PlaylistTrackModel(PlaylistModel playlist, TracksModel track, Integer position) {
        this.playlist = playlist;
        this.track = track;
        this.position = position;
    }

    public PlaylistModel getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    public TracksModel getTrack() {
        return track;
    }

    public void setTrack(TracksModel track) {
        this.track = track;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}