package ml.pic.tech.security.token.resource;

import ml.pic.tech.security.token.domaine.Article;
import ml.pic.tech.security.token.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleResource {
    @Autowired
    private ArticleService service;

    @GetMapping(value = {"/", "/liste"} ,produces = "application/json")
    public List<Article> articleList() {
        return service.all();
    }

    @PostMapping("/")
    public Article article(@RequestBody Article article) {
        return service.ajouter(article);
    }

    @GetMapping("/{designation}")
    public List<Article> search(@PathVariable String  designation) {
        return service.rechercher(designation);
    }
}
