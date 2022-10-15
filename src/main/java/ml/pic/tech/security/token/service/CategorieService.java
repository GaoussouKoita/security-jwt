package ml.pic.tech.security.token.service;

import ml.pic.tech.security.token.domaine.Categorie;
import ml.pic.tech.security.token.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository repository;

    public Categorie ajouter(Categorie categorie) {
        return repository.save(categorie);
    }

    public void supprimer(long id) {
        repository.deleteById(id);
    }

    public List<Categorie> all() {
        return repository.findAll();
    }
}
