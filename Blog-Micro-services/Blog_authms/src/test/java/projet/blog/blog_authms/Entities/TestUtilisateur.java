package projet.blog.blog_authms.Entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projet.blog.blog_authms.entities.Utilisateur;
import projet.blog.blog_authms.repositories.UtilisateurRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static projet.blog.blog_authms.entities.Role.USER;
@SpringBootTest
 class TestUtilisateur {
    @Autowired
    private  UtilisateurRepository utilisateurRepository;
    @Test
     void testUserProperties() {

       // Création d'un nouvel utilisateur
       Utilisateur utilisateur = new Utilisateur();
       utilisateur.setUsername("nomUtilisateur");
       utilisateur.setPassword("motDePasse");
       utilisateur.setNom("Nom de l'utilisateur");
       utilisateur.setPrenom("Prénom de l'utilisateur");
       utilisateur.setEmail("utilisateur@example.com");
       utilisateur.setRole(USER); // Supposons que ROLE_USER soit défini dans l'énumération Role

       // Sauvegarde de l'utilisateur dans le repository
       utilisateurRepository.save(utilisateur);

       // Récupération de l'utilisateur à partir du repository en utilisant le nom d'utilisateur
       Utilisateur retrievedUtilisateur = utilisateurRepository.findByUsername("nomUtilisateur").orElse(null);

       // Vérification si les attributs récupérés correspondent aux valeurs attendues
       assertEquals("Nom de l'utilisateur", retrievedUtilisateur.getNom());
       assertEquals("utilisateur@example.com", retrievedUtilisateur.getEmail());

       assertNotNull(retrievedUtilisateur.getId(), "L'ID ne doit pas être null");
    }
}
