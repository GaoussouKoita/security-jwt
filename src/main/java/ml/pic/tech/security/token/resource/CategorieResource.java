package ml.pic.tech.security.token.resource;

import io.swagger.annotations.ApiOperation;
import ml.pic.tech.security.token.domaine.Categorie;
import ml.pic.tech.security.token.service.CategorieService;
import ml.pic.tech.security.token.utilis.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.CATEGORIE_ENDPOINT)
public class CategorieResource {
    @Autowired
    private CategorieService service;

    @ApiOperation("Liste des Categories")
    @GetMapping
    public List<Categorie> CategorieList() {
        return service.all();
    }

    @PostMapping
    public Categorie Categorie(@RequestBody Categorie categorie) {
        return service.ajouter(categorie);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.supprimer(id);
    }
}
