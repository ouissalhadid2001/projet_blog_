package projet.blog.Blog_ms2.Entities;

import org.junit.jupiter.api.Test;
import projet.blog.Blog_ms2.entities.Commentaire;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class commentaireTest {

    @Test
    public void testCommentaireProperties() {
        Commentaire commenttest=new Commentaire(null,"fascinating article about IA",5L,23l);
        assertEquals("fascinating article about IA",commenttest.getContenu());
        assertEquals(23L,commenttest.getIdArticle());
        assertEquals(5L,commenttest.getIdUser());

    }
}
