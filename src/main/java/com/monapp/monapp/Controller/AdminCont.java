package com.monapp.monapp.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.monapp.monapp.Model.User;
import com.monapp.monapp.Service.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class AdminCont {

    @Autowired
    private chef_crud chef_crud;
    @Autowired
    private AuthService authService;


    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
            return ResponseEntity.ok("Login successful");

    }
    @GetMapping("/afficherchefs")
    public List<User> affichertouschefs() {
       return  chef_crud.afficherchefs();
    }
    @PostMapping("/ajouterchef")
    public User ajouterchef(@RequestBody User user) {
      user  =chef_crud.ajouterpersonnel(user);
      return user;
    }
    @DeleteMapping ("/supprimerchef/{id}")
    public void supprimerchef(@PathVariable int id) {
        chef_crud.supprimerpersonnel(id);
    }


    @GetMapping("/afficherchef/{id}")
    public Optional<User> afficherchef(@PathVariable int id) {
        return chef_crud.afficherpersonnel(id);
    }

    @PutMapping("/modifierchef/{id}")
    public ResponseEntity<User> modifierpersonnel(@RequestBody User user, @PathVariable int id) {
        User updateuser= chef_crud.modifierpersonnel(user,id);
        return ResponseEntity.ok(updateuser);
    }
}
