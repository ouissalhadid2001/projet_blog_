package projet.blog.blog_ms1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projet.blog.blog_ms1.entities.Categorie;
import projet.blog.blog_ms1.services.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;
    @GetMapping
    List<Categorie> showAll(){
        return categorieService.findAll();
    }
    @PostMapping
    void add(@RequestBody Categorie categorie){
        categorieService.addCategorie(categorie);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        categorieService.deleteCategorie(id);
    }
    @GetMapping("/{id}")
    Categorie find(@PathVariable Long id) throws Exception {
        return categorieService.findById(id);
    }
    @PutMapping
    Categorie update(@RequestBody Categorie categorie){
        return categorieService.UpdateCategorie(categorie);
    }
}
