package projet.blog.blog_authms.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.blog.blog_authms.entities.Utilisateur;
import projet.blog.blog_authms.models.RegisterResponse;
import projet.blog.blog_authms.repositories.UtilisateurRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

   public List<Utilisateur> findAll(){
        return utilisateurRepository.findAll();
    }
    public RegisterResponse findById(Long id){
       Utilisateur u=utilisateurRepository.findById(id).orElse(null);
           RegisterResponse response=new RegisterResponse(u.getId(),u.getUsername(),u.getPassword(),u.getNom(),u.getPrenom(),u.getEmail());
        if(u!=null)
            return response;
        else return null;
    }

    public Utilisateur findByUsername(String un) {
       return utilisateurRepository.findByUsername(un).orElse(null);
    }
}
