package ml.pic.tech.security.token.resource;

import io.swagger.annotations.ApiOperation;
import ml.pic.tech.security.token.domaine.Produit;
import ml.pic.tech.security.token.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
public class ProduitResource {
    @Autowired
    private ProduitService service;

    @ApiOperation("Liste des Produits")
    @GetMapping
    public List<Produit> ProduitList() {
        return service.all();
    }

    @PostMapping
    public Produit Produit(@RequestBody Produit produit) {
        return service.ajouter(produit);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.supprimer(id);
    }
}
