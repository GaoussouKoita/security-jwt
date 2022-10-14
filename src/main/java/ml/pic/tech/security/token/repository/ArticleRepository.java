package ml.pic.tech.security.token.repository;

import ml.pic.tech.security.token.domaine.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByDesignationContaining(String designation);
}
