package projet.blog.blog_ms1.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projet.blog.blog_ms1.repositories.CategorieRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
 class TestCategorie {
@Autowired
    private  CategorieRepository categorieRepository; // Supposons que tu as un CategorieRepository à tester

    @Test
     void testCategorieRepository() {
        // Création d'une nouvelle catégorie
        Categorie categorie = new Categorie();
        categorie.setNom("Nom de la catégorie");
        categorie.setDescription("Description de la catégorie");

        // Sauvegarde de la catégorie dans le repository
        categorieRepository.save(categorie);

        // Récupération de la catégorie à partir du repository en utilisant le nom
        Categorie retrievedCategorie = categorieRepository.findById(18L).orElse(null);

        // Vérification si les attributs récupérés correspondent aux valeurs attendues
        assertEquals("Description de la catégorie", retrievedCategorie.getDescription());
        // Exemple d'assertion pour vérifier si l'id n'est pas null
        assertNotNull(retrievedCategorie.getId(), "L'ID ne doit pas être null");
    }
}
