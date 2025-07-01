package delivery.example.enaaskill.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SousCompetence> sousCompetences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<SousCompetence> getSousCompetences() {
        return sousCompetences;
    }

    public void setSousCompetences(List<SousCompetence> sousCompetences) {
        this.sousCompetences = sousCompetences;
    }
}
