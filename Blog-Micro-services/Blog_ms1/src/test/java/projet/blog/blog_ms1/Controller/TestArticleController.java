package projet.blog.blog_ms1.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projet.blog.blog_ms1.entities.Article;
import projet.blog.blog_ms1.entities.Utilisateur;
import projet.blog.blog_ms1.models.ArticleResponse;
import projet.blog.blog_ms1.services.ArticleService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
 class TestArticleController {


    private int port=8081;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    ArticleService articleService;

    @BeforeEach
    public void setUp() {
        // Créez un article pour les tests
        Article article = new Article();
        article.setTitre("Titre de test");
        article.setContenu("Contenu de test");
        article.setClaps(0);
        article.setUrlphoto("url-de-test");
        article.setAuteur(1L);

        // Enregistrez l'article dans la base de données

        articleService.addArticle(article);


    }

    @Test
    public void testShowAllArticles() {
        ResponseEntity<ArticleResponse[]> response = restTemplate.getForEntity("http://localhost:" + port + "/articles", ArticleResponse[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ArticleResponse[] articles = response.getBody();
        assertNotNull(articles);
        // Ajoutez d'autres assertions en fonction de la réponse attendue
    }

    @Test
    public void testAddArticle() {
        Article newArticle = new Article();
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/articles", newArticle, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Ajoutez d'autres assertions si nécessaire après l'ajout d'un article
    }

    @Test
    public void testDeleteArticle() {
        long articleIdToDelete = 114L; // ID de l'article à supprimer
        restTemplate.delete("http://localhost:" + port + "/articles/" + articleIdToDelete);

        // Vérifiez si l'article a été supprimé en effectuant une autre requête pour récupérer l'article
    }

    @Test
    public void testFindArticleById() {


        // Effectuez la recherche de l'article par ID
        long articleIdToFind = 5L; // ID de l'article à rechercher
        ResponseEntity<ArticleResponse> response = restTemplate.getForEntity("http://localhost:" + port + "/articles/" + articleIdToFind, ArticleResponse.class);

        // Vérifiez la réponse de la requête
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ArticleResponse foundArticle = response.getBody();
        assertNotNull(foundArticle);

    }

    @Test
    public void testUpdateArticle() throws Exception {
        // Supposons que vous avez un article existant avec l'ID 1 à mettre à jour
        ArticleResponse articleIdToUpdate =  articleService.findById(8L);

        // Créez un objet Article avec des données de mise à jour
        Article updatedArticle = new Article();
        updatedArticle.setId(8L);
        updatedArticle.setContenu("Nouveau contenu pour l'article");
        updatedArticle.setTitre("Nouveau titre pour l'article");
        updatedArticle.setClaps(10);
        updatedArticle.setUrlphoto("nouvelle-url-de-photo");
        articleService.updateArticle(updatedArticle);

        // Effectuez la requête PUT pour mettre à jour l'article
        ResponseEntity<ArticleResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/articles",
                HttpMethod.PUT, new HttpEntity<>(updatedArticle), ArticleResponse.class);

        // Vérifiez si la requête a abouti avec succès (réponse HTTP 200 OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Récupérez la réponse contenant l'article mis à jour
        ArticleResponse updated = response.getBody();
        assertNotNull(updated);

        // Vérifiez si les champs mis à jour correspondent aux valeurs envoyées dans la requête
        assertEquals("Nouveau contenu pour l'article", updated.getContenu());

    }


    @Test
    public void testGetArticleAuteur() {
        long auteurId = 4L; // ID de l'auteur
        ResponseEntity<Utilisateur> response = restTemplate.getForEntity("http://localhost:" + port + "/articles/auteur/" + auteurId, Utilisateur.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Utilisateur auteur = response.getBody();
        assertNotNull(auteur);
    }


}
