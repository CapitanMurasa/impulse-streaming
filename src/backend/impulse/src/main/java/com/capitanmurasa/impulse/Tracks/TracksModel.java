package com.capitanmurasa.impulse.Tracks;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tracks")
public class TracksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "public_id", nullable = false, unique = true)
    private UUID publicId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "bitrate")
    private Integer bitrate;

    @Column(name = "status", nullable = false, columnDefinition = "TEXT")
    private String status;

    @Column(name = "storage_key", nullable = false, columnDefinition = "TEXT")
    private String storageKey;

    @Column(name = "artist_id")
    private Integer artistId;

    @Column(name = "album_id")
    private Integer albumId;

    @Column(name = "playlist_id")
    private Integer playlistId;

    @Column(name = "format_id", nullable = false)
    private Integer formatId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public TracksModel() {
    }

    public TracksModel(
            String name,
            Integer duration,
            Integer bitrate,
            String status,
            String storageKey,
            Integer artistId,
            Integer albumId,
            Integer playlistId,
            Integer formatId,
            Integer userId
    ) {
        this.publicId = UUID.randomUUID();
        this.name = name;
        this.duration = duration;
        this.bitrate = bitrate;
        this.status = status;
        this.storageKey = storageKey;
        this.artistId = artistId;
        this.albumId = albumId;
        this.playlistId = playlistId;
        this.formatId = formatId;
        this.userId = userId;
    }

    @PrePersist
    public void generatePublicId() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }

    public Integer getFormatId() {
        return formatId;
    }

    public void setFormatId(Integer formatId) {
        this.formatId = formatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}