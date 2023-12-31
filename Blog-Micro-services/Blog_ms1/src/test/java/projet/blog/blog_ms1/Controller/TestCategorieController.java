package projet.blog.blog_ms1.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projet.blog.blog_ms1.entities.Categorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
 class TestCategorieController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
     void testShowAllCategories() {
        ResponseEntity<Categorie[]> response = restTemplate.getForEntity("http://localhost:" + port + "/categories", Categorie[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Categorie[] categories = response.getBody();
        assertNotNull(categories);
        // Ajoutez d'autres assertions en fonction de la réponse attendue
    }

    @Test
    public void testAddCategory() {
        Categorie newCategory = new Categorie();
        newCategory.setNom("Nouvelle catégorie"); // Initialisez avec le nom de la nouvelle catégorie

        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/categories", newCategory, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Ajoutez d'autres assertions si nécessaire après l'ajout d'une catégorie
    }

    @Test
    public void testDeleteCategory() {
        long categoryIdToDelete = 1L; // ID de la catégorie à supprimer
        restTemplate.delete("http://localhost:" + port + "/categories/" + categoryIdToDelete);

        // Vérifiez si la catégorie a été supprimée en effectuant une autre requête pour récupérer la catégorie
    }

    @Test
    public void testFindCategoryById() {
        long categoryIdToFind = 1L; // ID de la catégorie à rechercher
        ResponseEntity<Categorie> response = restTemplate.getForEntity("http://localhost:" + port + "/categories/" + categoryIdToFind, Categorie.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Categorie category = response.getBody();
        assertNotNull(category);
        // Ajoutez d'autres assertions si nécessaire après avoir trouvé la catégorie
    }

    @Test
    public void testUpdateCategory() {
        Categorie updatedCategory = new Categorie();
        updatedCategory.setId(1L); // ID de la catégorie existante à mettre à jour
        updatedCategory.setNom("Nouveau nom de catégorie"); // Mettez à jour le champ 'nom' avec le nouveau nom

        ResponseEntity<Categorie> response = restTemplate.exchange("http://localhost:" + port + "/categories",
                HttpMethod.PUT, new HttpEntity<>(updatedCategory), Categorie.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Categorie updated = response.getBody();
        assertNotNull(updated);
        assertEquals("Nouveau nom de catégorie", updated.getNom()); // Vérifiez si le nom a été correctement mis à jour
    }
}
