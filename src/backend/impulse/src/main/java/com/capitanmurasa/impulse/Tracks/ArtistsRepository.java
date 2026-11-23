package com.capitanmurasa.impulse.Tracks;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArtistsRepository extends JpaRepository<ArtistsModel, Integer> {

    List<ArtistsModel> findByName(String name);
}