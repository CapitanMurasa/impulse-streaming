package com.capitanmurasa.impulse.Playlists;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistModel, Integer> {

    List<PlaylistModel> findByUser_Id(Integer userId);

    List<PlaylistModel> findByVisibility(boolean visibility);
}