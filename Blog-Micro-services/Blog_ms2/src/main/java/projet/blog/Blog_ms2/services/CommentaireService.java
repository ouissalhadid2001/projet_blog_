package projet.blog.Blog_ms2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import projet.blog.Blog_ms2.entities.Article;
import projet.blog.Blog_ms2.entities.Commentaire;
import projet.blog.Blog_ms2.entities.Utilisateur;
import projet.blog.Blog_ms2.models.CommentaireResponse;
import projet.blog.Blog_ms2.repositories.CommentaireRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentaireService {
    @Autowired
    CommentaireRepository commentaireRepository;
    private final String URL="http://localhost:8083";
    private final String URL2="http://localhost:8081";


    @Autowired
RestTemplate restTemplate;
    public List<CommentaireResponse> findAll(){
       List<Commentaire> comments=commentaireRepository.findAll();
        ResponseEntity<Utilisateur[]> responseUser=restTemplate.getForEntity(this.URL+"/users/auth",
                Utilisateur[].class);
        Utilisateur[] users=responseUser.getBody();

        ResponseEntity<Article[]> responseArticle=restTemplate.getForEntity(this.URL2+"/articles",
                Article[].class);
        Article[] articles=responseArticle.getBody();
        return comments.stream().map((Commentaire a)->mapToCommentResponse(a,users,articles)).toList();
    }
   public CommentaireResponse findById(Long id) throws Exception {
       Commentaire comment = commentaireRepository.findById(id).orElseThrow(() -> new Exception("ID comment not found"));

       ResponseEntity<Utilisateur[]> responseUser=restTemplate.getForEntity(this.URL+"/users/auth",
               Utilisateur[].class);
       Utilisateur[] users=responseUser.getBody();

       ResponseEntity<Article[]> responseArticle=restTemplate.getForEntity(this.URL2+"/articles",
               Article[].class);
       Article article= Arrays.stream(responseArticle.getBody()).toList().get(0);
       Utilisateur user= Arrays.stream(responseUser.getBody()).toList().get(0);

       return CommentaireResponse.builder().id(comment.getId()).contenu(comment.getContenu()).owner(user)
               .article(article)
               .build();
   }
    public CommentaireResponse mapToCommentResponse(Commentaire c, Utilisateur[] users, Article[] articles) {
        Utilisateur founduser= Arrays.stream(users).filter(user->user.getId().equals(c.getIdUser()))
                .findFirst().orElse(null);
        Article foundarticle= Arrays.stream(articles).filter(article->article.getId().equals(c.getIdArticle()))
                .findFirst().orElse(null);
        return CommentaireResponse.builder().id(c.getId()).contenu(c.getContenu()).owner(founduser)
                .article(foundarticle)
              .build();

    }
    public void addCommentaire(Commentaire commentaire){
        commentaireRepository.save(commentaire);
    }
    public CommentaireResponse updateComment(Commentaire UpdatedComment){
        Commentaire oldComment=commentaireRepository.findById(UpdatedComment.getId()).orElse(null);

        if(oldComment!=null){
            oldComment.setContenu(UpdatedComment.getContenu());
            oldComment.setIdUser(UpdatedComment.getIdUser());
            oldComment.setIdArticle(UpdatedComment.getIdArticle());
            commentaireRepository.save(oldComment);
        }
        Utilisateur auteur = restTemplate.getForObject(this.URL + "/users/auth/" + oldComment.getIdUser(), Utilisateur.class);
        Article article = restTemplate.getForObject(this.URL2 + "/articles/" + oldComment.getIdArticle(), Article.class);

        return CommentaireResponse.builder().id(oldComment.getId()).contenu(oldComment.getContenu()).owner(auteur)
                .article(article)
               .build();
    }
    public void deleteCommentaire(Long id){
         Commentaire deletedComment=commentaireRepository.findById(id).orElse(null);
        commentaireRepository.delete(deletedComment);
   }

}
