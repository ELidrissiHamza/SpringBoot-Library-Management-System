package ma.fstm.ilisi.project.biblio.biblioappspring.service;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Emprunt;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Exemplaire;
import ma.fstm.ilisi.project.biblio.biblioappspring.dao.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class EmpruntService {
    @Autowired
    private EmpruntRepository empruntRepository;

    public void createEmprunt(Emprunt emprunt){
        empruntRepository.save(emprunt);
    }

    public void updateEmprunt(Emprunt emprunt){
        empruntRepository.save(emprunt);
    }
    public void deleteEmprunt(Emprunt emprunt){
        empruntRepository.delete(emprunt);
    }
    public Emprunt retrieveEmprunt(Emprunt emprunt){
        return empruntRepository.findById(emprunt.getId()).get();
    }
    public List<Emprunt> retrieveAllEmprunts(){
        return (List<Emprunt>) empruntRepository.findAll();
    }
    public boolean isEmpruntExist(Emprunt emprunt){
        return empruntRepository.existsById(emprunt.getId());
    }
    public List<Exemplaire> getExemplairesEmpruntes() {
        List<Emprunt> emprunts = (List<Emprunt>) empruntRepository.findAll();
        List<Exemplaire> exemplairesEmpruntes = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            exemplairesEmpruntes.add(emprunt.getExemplaire());
        }
        return exemplairesEmpruntes;
    }
    public int getNombreEmprunts(Exemplaire exemplaire) {
        int i=0;
        List<Emprunt> emprunts = (List<Emprunt>) empruntRepository.findAll(); // Récupérer tous les emprunts de l'exemplaire
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getExemplaire().equals(exemplaire)) {
                i++;
            }
        }
        return emprunts.size();
    }
    public int countEmpruntbyISBN(String isbn) {
        int i = 0;
        for (Emprunt emprunt : empruntRepository.findAll()) {
            if (emprunt.getExemplaire().getLivre().getIsbn().equals(isbn)) {
                i++;
            }
        }
        return i;
    }
public List<Emprunt> rettreaveEmpruntNonRendu() {
        List<Emprunt> emprunts = (List<Emprunt>) empruntRepository.findAll();
        List<Emprunt> empruntsNonRendu = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getRetourne() == 0) {
                empruntsNonRendu.add(emprunt);
            }
        }
        return empruntsNonRendu;
    }
}
