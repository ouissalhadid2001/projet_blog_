package projet.blog.blog_ms1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.blog.blog_ms1.entities.Categorie;
import projet.blog.blog_ms1.repositories.CategorieRepository;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    CategorieRepository categorieRepository;
    public List<Categorie> findAll(){
        return categorieRepository.findAll();
    }
    public Categorie findById(Long id) throws Exception {
        return categorieRepository.findById(id).orElseThrow(()->new Exception("Categorie introuvable"));
    }
    public void addCategorie(Categorie categorie){
        categorieRepository.save(categorie);
    }
    public void deleteCategorie(Long id){
        Categorie deletedCategorie=categorieRepository.findById(id).orElse(null);
        if (deletedCategorie!=null)

            categorieRepository.delete(deletedCategorie);
    }
    public Categorie UpdateCategorie(Categorie Updatedcategorie){
        Categorie oldCategorie=categorieRepository.findById(Updatedcategorie.getId()).orElse(null);

        if (oldCategorie!=null){
            oldCategorie.setNom(Updatedcategorie.getNom());
            oldCategorie.setDescription(Updatedcategorie.getDescription());
            categorieRepository.save(oldCategorie);
        }
        return oldCategorie;
    }
}
