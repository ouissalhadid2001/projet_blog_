package projet.blog.Blog_ms2.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    private Long id;
    private String nom;
    private String description;
}