package ml.pic.tech.security.token.service;

import ml.pic.tech.security.token.domaine.Article;
import ml.pic.tech.security.token.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;

    public Article ajouter(Article article){
        return repository.save(article);
    }

    public List<Article> rechercher(String designation){
        return repository.findByDesignationContaining(designation);
    }

    public void supprimer(long id){
        repository.deleteById(id);
    }

    public List<Article> all(){
        return repository.findAll();
    }
}
