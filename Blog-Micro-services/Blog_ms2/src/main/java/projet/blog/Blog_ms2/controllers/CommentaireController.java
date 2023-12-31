package projet.blog.Blog_ms2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projet.blog.Blog_ms2.entities.Commentaire;
import projet.blog.Blog_ms2.models.CommentaireResponse;
import projet.blog.Blog_ms2.services.CommentaireService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;

    @GetMapping
    List<CommentaireResponse> getAll(){
        return commentaireService.findAll();
    }
    @GetMapping("/{id}")
    CommentaireResponse getById(@PathVariable Long id) throws Exception {
        return commentaireService.findById(id);
    }
    @PostMapping
    void add(@RequestBody Commentaire commentaire){
        commentaireService.addCommentaire(commentaire);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        commentaireService.deleteCommentaire(id);
    }
    @PutMapping
    CommentaireResponse update(@RequestBody Commentaire commentaire){
        return commentaireService.updateComment(commentaire);
    }
}
