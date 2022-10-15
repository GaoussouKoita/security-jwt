package ml.pic.tech.security.token.service;

import ml.pic.tech.security.token.domaine.Produit;
import ml.pic.tech.security.token.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository repository;

    public Produit ajouter(Produit Prduit) {
        return repository.save(Prduit);
    }

    public void supprimer(long id) {
        repository.deleteById(id);
    }

    public List<Produit> all() {
        return repository.findAll();
    }
}
