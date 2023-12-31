package projet.blog.Blog_ms2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.blog.Blog_ms2.entities.Commentaire;
@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
}
