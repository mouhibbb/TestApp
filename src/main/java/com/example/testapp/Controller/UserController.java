package com.example.testapp.Controller;

import com.example.testapp.Entity.LoginRequest;
import com.example.testapp.Entity.User;
import com.example.testapp.Repository.UserRepository;
import com.example.testapp.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping()
    public User createUser(@RequestBody User user) {
        System.out.println(user);

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // Affiche les informations de la requête
        System.out.println("Email reçu : " + loginRequest.getEmail());
        System.out.println("Mot de passe reçu : " + loginRequest.getPassword());

        // Récupérer l'utilisateur par email
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user == null) {
            System.out.println("Utilisateur introuvable avec l'email : " + loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Afficher les informations de l'utilisateur trouvé
        System.out.println("Utilisateur trouvé : " + user.getEmail());
        System.out.println("Mot de passe enregistré : " + user.getPassword());
        System.out.println(" activated : " + user.isActive());


        // Comparer le mot de passe
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            System.out.println("Mot de passe incorrect pour l'utilisateur : " + user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        if (!user.isActive()) {
            String errorMessage = "Compte non activé par l'administrateur " ;
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);  // Envoie le message au front-end
        }
        System.out.println("Connexion réussie pour l'utilisateur : " + user.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("role", user.getRole());
        return ResponseEntity.ok(response);
    }


}


