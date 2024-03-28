package com.monapp.monapp.Model;

import jakarta.persistence.*;
import lombok.*;

import java.beans.ConstructorProperties;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "service")
    private String service;
    @Column(name = "statut")
    private String statut;
    @Column(name = "nbr_enfant")
    private String nbr_enfant;
    @Column(name = "tel")
    private String tel;
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "mail")
    private String mail;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "role")
    private int role;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    public User(int id) {
        this.id = id;
    }
}

