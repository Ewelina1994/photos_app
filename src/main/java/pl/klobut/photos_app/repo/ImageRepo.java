package pl.klobut.photos_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klobut.photos_app.model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}
