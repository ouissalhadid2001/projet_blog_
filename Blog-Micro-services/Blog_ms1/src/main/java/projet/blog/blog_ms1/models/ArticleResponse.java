package projet.blog.blog_ms1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.blog.blog_ms1.entities.Categorie;
import projet.blog.blog_ms1.entities.Utilisateur;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    private Long id;
    private String contenu;
    private String titre;
    private int claps;
    private String urlphoto;
    private Categorie categorie;
    private Utilisateur auteur;
}
