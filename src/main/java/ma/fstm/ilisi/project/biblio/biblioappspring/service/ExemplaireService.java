package ma.fstm.ilisi.project.biblio.biblioappspring.service;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Exemplaire;
import ma.fstm.ilisi.project.biblio.biblioappspring.dao.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ExemplaireService {
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public void createExemplaire(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }

    public void updateExemplaire(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }

    public void deleteExemplaire(int id) {
        exemplaireRepository.deleteById(id);
    }

    public Exemplaire retrieveExemplaire(int id) {
        return exemplaireRepository.findById(id).get();
    }

    public boolean isExemplaireExist(int id) {
        return exemplaireRepository.existsById(id);
    }

    public List<Exemplaire> retrieveAllExemplaires() {
        return (List<Exemplaire>) exemplaireRepository.findAll();
    }

    public int countExemplaire() {
        return (int) exemplaireRepository.count();
    }

    public int countExemplaireForLivre(String isbn) {
        int i = 0;
        for (Exemplaire exemplaire : exemplaireRepository.findAll()) {
            if (exemplaire.getLivre().getIsbn().equals(isbn)) {
                i++;
            }
        }

        return i;

    }
    public Exemplaire getExemplaireByISBN(String isbn) {
        for (Exemplaire exemplaire : exemplaireRepository.findAll()) {
            if (exemplaire.getLivre().getIsbn().equals(isbn)) {
                return exemplaire;
            }
        }
        return null;
    }
}