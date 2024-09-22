package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mdarmanansari
 */

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    @Override
    Region save(Region region);

    @Override
    Optional<Region> findById(Long regionId);
}
