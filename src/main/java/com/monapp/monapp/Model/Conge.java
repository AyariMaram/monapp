package com.monapp.monapp.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="conge")
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "date_dem")
    private Timestamp date_demande;

    @Column(name = "date_deb")
    private Date date_debut;

    @Column(name = "date_fin")
    private Date date_fin;

    @Column(name = "durée")
    private int duree;

    @Column(name = "statut")  //en_attente /acceptee/refusee
    private String statut;

    @Column(name = "solde")
    private int solde;

    @Column(name = "motif")
    private String motif;

    @Column(name = "file")
    private String file;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}