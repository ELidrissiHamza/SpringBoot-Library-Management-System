package ma.fstm.ilisi.project.biblio.biblioappspring.service;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Livre;
import ma.fstm.ilisi.project.biblio.biblioappspring.dao.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public void createLivre(Livre livre){
        livreRepository.save(livre);
    }

    public void updateLivre(Livre livre){
        livreRepository.save(livre);
    }
    public void deleteLivre(String isbn){
        livreRepository.deleteById(isbn);
    }
    public Livre retrieveLivre(String isbn){
        return livreRepository.findById(isbn).get();
    }
    public List<Livre> retrieveAllLivres(){
        return (List<Livre>) livreRepository.findAll();
    }
    public boolean isLivreExist(String isbn){
        return livreRepository.existsById(isbn);
    }

}
