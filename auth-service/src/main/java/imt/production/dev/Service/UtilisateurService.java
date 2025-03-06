package imt.production.dev.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import imt.production.dev.Dto.JoueurDto;
import imt.production.dev.Dto.UtilisateurDto;
import imt.production.dev.Errors.HTTP_404.UtilisateurInexsitantException;
import imt.production.dev.Errors.HTTP_409.UtilisateurDejaExistantException;
import imt.production.dev.Errors.HTTP_401.TokenExpireException;
import imt.production.dev.Errors.HTTP_404.TokenInexsitantException;
import imt.production.dev.Errors.HTTP_404.UtilisateurIdInexistantException;
import imt.production.dev.Model.Utilisateur;
import imt.production.dev.Repository.JoueurRemoteRepository;
import imt.production.dev.Repository.UtilisateurRepository;
import imt.production.dev.Utils.TokenUtils;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private JoueurRemoteRepository joueurRemoteRepository;

    public Map<String, String> login(UtilisateurDto dto) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        if (utilisateur.isEmpty()) { // return 404
            throw new UtilisateurInexsitantException("Un utilisateur avec ce username et ce mot de passe n'existe pas !");
        }

        // Genere un Token à chaque connexion reussie et enregistre la date de connexion
        LocalDateTime dateTime = LocalDateTime.now();
        String token = TokenUtils.generateToken(utilisateur.get().getUsername(), dateTime);

        utilisateur.get().setDate(dateTime);
        utilisateur.get().setToken(token);
        
        utilisateurRepository.save(utilisateur.get());
        return Map.of("token", token);
    }

    public Map<String, String> validate(String token) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByToken(token);

        if (utilisateur.isEmpty()) { // return 404
            throw new TokenInexsitantException("Le token n'existe pas !");
        }

        if (TokenUtils.isTokenExpired(utilisateur.get().getDate())) { // return 401
            throw new TokenExpireException("Le token a expiré !");
        }

        // met à jour la date de connexion
        utilisateur.get().setDate(LocalDateTime.now().plus(1, ChronoUnit.HOURS));
        utilisateurRepository.save(utilisateur.get());
        return Map.of("username", utilisateur.get().getUsername());
    }

    public Map<String, String> createUtilisateur(UtilisateurDto dto) {
        if (utilisateurRepository.existsByUsername(dto.getUsername())) { // return 409
            throw new UtilisateurDejaExistantException("Un utilisateur avec ce username existe déjà !");
        }

        Utilisateur utilisateur = new Utilisateur(dto.getUsername(), dto.getPassword());
        LocalDateTime dateTime = LocalDateTime.now();
        String token = TokenUtils.generateToken(utilisateur.getUsername(), dateTime);

        utilisateur.setDate(dateTime);
        utilisateur.setToken(token);
        
        utilisateurRepository.save(utilisateur);
        joueurRemoteRepository.createJoueur(new JoueurDto(dto.getUsername()), token);

        return Map.of("id", utilisateurRepository.save(utilisateur).getId());
    }

    public Optional<Utilisateur> getUtilisateurById(String id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public void deleteUtilisateurById(String id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new UtilisateurIdInexistantException("Id inexistant.");
        }
        
        utilisateurRepository.deleteById(id);
    }
    
}
