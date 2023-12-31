package projet.blog.blog_ms1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;

}
