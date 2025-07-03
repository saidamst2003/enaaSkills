package delivery.example.enaaskill.service;

import delivery.example.enaaskill.model.Competence;
import delivery.example.enaaskill.model.SousCompetence;
import delivery.example.enaaskill.repository.CompetenceRepository;
import delivery.example.enaaskill.repository.SousCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompetenceService {

        @Autowired
        private CompetenceRepository competenceRepository;
           private SousCompetenceRepository sousCompetenceRepository;
        // Créer une nouvelle compétence
        public Competence create(Competence competence) {
            competence.setDateCreation(LocalDateTime.now());
            competence.setDateModification(LocalDateTime.now());
            return competenceRepository.save(competence);
        }

        // Lire toutes les compétences
        public List<Competence> getAll() {
            return competenceRepository.findAll();
        }

        // Lire une compétence par ID
        public Competence getById(Long id) {
            return competenceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Compétence non trouvée avec id " + id));
        }

        // Mettre à jour une compétence
        public Competence update(Long id, Competence newData) {
            Competence existing = competenceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Compétence non trouvée avec id " + id));

            existing.setNom(newData.getNom());
            existing.setDescription(newData.getDescription());
            existing.setSeuilValidation(newData.getSeuilValidation());
            existing.setDateModification(LocalDateTime.now());

            return competenceRepository.save(existing);
        }

        // Supprimer une compétence
        public void delete(Long id) {
            competenceRepository.deleteById(id);
        }
    public void mettreAJourValidationCompetence(Long competenceId) {
        Competence competence = competenceRepository.findById(competenceId)
                .orElseThrow(() -> new RuntimeException("Compétence introuvable"));

        List<SousCompetence> sousCompetences = sousCompetenceRepository.findByCompetenceId(competenceId);
        long nbValidees = sousCompetences.stream().filter(SousCompetence::isValidee).count();

        if (nbValidees >= competence.getSeuilValidation()) {
            competence.setValidee(true);
        } else {
            competence.setValidee(false);
        }

        competenceRepository.save(competence);
    }

}



