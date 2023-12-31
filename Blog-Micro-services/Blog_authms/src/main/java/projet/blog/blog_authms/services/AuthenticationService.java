package projet.blog.blog_authms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projet.blog.blog_authms.entities.Role;
import projet.blog.blog_authms.entities.Utilisateur;
import projet.blog.blog_authms.models.AuthenticationRequest;
import projet.blog.blog_authms.models.AuthenticationResponse;
import projet.blog.blog_authms.models.RegisterRequest;
import projet.blog.blog_authms.repositories.UtilisateurRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
public AuthenticationResponse register(RegisterRequest request){
    var user= Utilisateur.builder()
            .nom(request.getNom())
            .prenom(request.getPrenom())
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    utilisateurRepository.save(user);
     user=utilisateurRepository.findByUsername(request.getUsername()).orElseThrow();
    var jwt=jwtService.generateToken(user);
    return AuthenticationResponse
            .builder()
            .token(jwt)
            .id(user.getId())
            .build();
}
public AuthenticationResponse authenticate(AuthenticationRequest request){
    authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername()
                    ,request.getPassword()));
    var user=utilisateurRepository.findByUsername(request.getUsername()).orElseThrow();
    var jwt=jwtService.generateToken(user);
    return AuthenticationResponse
            .builder()
            .token(jwt)
            .id(user.getId())
            .build();
}
}
