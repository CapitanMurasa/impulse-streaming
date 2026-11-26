package com.capitanmurasa.impulse.Playlists;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaylistTrackRepository
        extends JpaRepository<PlaylistTrackModel, PlaylistTrackId> {

    List<PlaylistTrackModel> findByPlaylist_IdOrderByPositionAsc(Integer playlistId);

    List<PlaylistTrackModel> findByTrack_Id(Integer trackId);

    void deleteByPlaylist_IdAndTrack_Id(Integer playlistId, Integer trackId);
}