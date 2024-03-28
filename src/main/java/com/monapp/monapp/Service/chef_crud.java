package com.monapp.monapp.Service;
import com.monapp.monapp.Model.User;
import com.monapp.monapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class chef_crud {
    @Autowired
    private UserRepo userRepository;
    private String mapRole(int role) {
        switch (role) {
            case 0:
                return "ADMIN";
            case 1:
                return "CHEF";
            case 2:
                return "EMPLOYEE";
            default:
                return "UNKNOWN";
        }
    }
    public List<User> afficherchefs(){
        return userRepository.findAllByRole(1);
    }
    public List<User> afficheremployes(){
        return userRepository.findAllByRole(2);
    }

    public Optional<User> afficherpersonnel(int id){ return userRepository.findById(id);}


    public User ajouterpersonnel(User user) {
        User savedUser = userRepository.save(user);
        savedUser.setMatricule(generateMatricule(savedUser.getId()));
        return userRepository.save(savedUser);
    }
    public void supprimerpersonnel(int iduser) {
        userRepository.deleteById(iduser);}
    @Transactional
    public User modifierpersonnel(User updateUser, int id) {
        User user = userRepository.findById(id).orElse(null);
            user.setNom(updateUser.getNom());
            user.setPrenom(updateUser.getPrenom());
            user.setService(updateUser.getService());
            user.setStatut(updateUser.getStatut());
            user.setNbr_enfant(updateUser.getNbr_enfant());
            user.setMail(updateUser.getMail());
            user.setMatricule(updateUser.getMatricule());
            user.setSexe(updateUser.getSexe());
            user.setRole(updateUser.getRole());
            user.setLogin(updateUser.getLogin());
            user.setPassword(updateUser.getPassword());

            return userRepository.save(user);
        }
    private String generateMatricule(int userId) {
        int matriculeValue = userId % 10000;
        return String.format("%04d", matriculeValue);
    }

    }

