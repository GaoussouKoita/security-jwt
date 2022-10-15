package ml.pic.tech.security.token.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ml.pic.tech.security.token.domaine.Article;
import ml.pic.tech.security.token.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("Article Ressource")
@RequestMapping("/article")
public class ArticleResource {
    @Autowired
    private ArticleService service;

    @ApiOperation("Liste des articles")
    @GetMapping
    public List<Article> articleList() {
        return service.all();
    }

    @PostMapping
    public Article article(@RequestBody Article article) {
        return service.ajouter(article);
    }

    @GetMapping("/{designation}")
    public List<Article> search(@PathVariable String designation) {
        return service.rechercher(designation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.supprimer(id);
    }
}
