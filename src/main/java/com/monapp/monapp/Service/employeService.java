package com.monapp.monapp.Service;

import com.monapp.monapp.Model.Conge;
import com.monapp.monapp.Repository.CongeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
@Service
public class employeService {
    @Autowired
    private CongeRepo congeRepo;
    @Autowired
    private chef_crud chef_crud;


    public Conge ajouterconge(Conge conge) {
        return this.congeRepo.save(conge);}
    public List<Conge> afficherconges(){
        return congeRepo.findAll();
    }

    public Optional<Conge> afficherconge(int id){ return congeRepo.findById(id);}

}
