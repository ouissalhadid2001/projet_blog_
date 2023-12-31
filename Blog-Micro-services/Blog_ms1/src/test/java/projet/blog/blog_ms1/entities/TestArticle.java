package projet.blog.blog_ms1.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projet.blog.blog_ms1.repositories.ArticleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class TestArticle {
    @Autowired
    private  ArticleRepository articleRepository; // Supposons que tu as un ArticleRepository à tester

    @Test
     void testArticleRepository() {
        // Création d'un nouvel article
        Article article = new Article();
        article.setTitre("Titre de l'article");
        article.setContenu("Contenu de l'article");
        article.setClaps(20); // Ajout du nombre de claps
        article.setUrlphoto("https://exemple.com/photo.jpg"); // Ajout de l'URL de la photo
        article.setAuteur(1L);

        articleRepository.save(article);

        // Récupération de l'article à partir du repository en utilisant le titre
        Article retrievedArticle = articleRepository.findById(108L).orElse(null);

        // Vérification si les attributs récupérés correspondent aux valeurs attendues
        assertEquals("Contenu de l'article", retrievedArticle.getContenu());
        assertEquals(20, retrievedArticle.getClaps());
        assertEquals("https://exemple.com/photo.jpg", retrievedArticle.getUrlphoto());
        assertNotNull(retrievedArticle.getAuteur(), "L'auteur ne doit pas être null");
    }
}
