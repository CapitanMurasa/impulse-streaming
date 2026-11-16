package com.capitanmurasa.impulse.Tracks;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TracksRepository
        extends JpaRepository<TracksModel, Integer> {

    Optional<TracksModel> findByPublicId(UUID publicId);

    List<TracksModel> findByUserId(Integer userId);

    List<TracksModel> findByAlbumId(Integer albumId);

    List<TracksModel> findByArtistId(Integer artistId);
}