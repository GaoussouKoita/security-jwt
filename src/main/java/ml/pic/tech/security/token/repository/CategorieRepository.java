package ml.pic.tech.security.token.repository;

import ml.pic.tech.security.token.domaine.Article;
import ml.pic.tech.security.token.domaine.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
