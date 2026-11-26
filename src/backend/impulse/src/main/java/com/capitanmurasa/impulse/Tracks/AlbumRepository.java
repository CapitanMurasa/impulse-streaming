package com.capitanmurasa.impulse.Tracks;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumModel, Integer> {

    List<AlbumModel> findByUser_Id(Integer userId);

    List<AlbumModel> findByPrivacy(boolean privacy);

    List<AlbumModel> findByName(String name);
}