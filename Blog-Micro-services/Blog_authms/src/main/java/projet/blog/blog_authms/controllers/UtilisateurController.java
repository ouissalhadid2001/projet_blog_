package projet.blog.blog_authms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.blog.blog_authms.entities.Utilisateur;
import projet.blog.blog_authms.models.AuthenticationRequest;
import projet.blog.blog_authms.models.AuthenticationResponse;
import projet.blog.blog_authms.models.RegisterRequest;
import projet.blog.blog_authms.models.RegisterResponse;
import projet.blog.blog_authms.services.AuthenticationService;
import projet.blog.blog_authms.services.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/users/auth")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
         return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }
    @GetMapping
    public List<Utilisateur> getAll(){
      return utilisateurService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegisterResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(utilisateurService.findById(id));
    }
@GetMapping("/name")
public ResponseEntity<Utilisateur> getByUsername(@RequestBody String un){
    return ResponseEntity.ok(utilisateurService.findByUsername(un));
}
}

