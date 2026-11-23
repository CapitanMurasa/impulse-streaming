package com.capitanmurasa.impulse.Tracks;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FormatRepository extends JpaRepository<FormatModel, Integer> {

    Optional<FormatModel> findByName(String name);

    Optional<FormatModel> findByExtension(String extension);
}